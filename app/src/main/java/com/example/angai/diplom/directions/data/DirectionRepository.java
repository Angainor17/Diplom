package com.example.angai.diplom.directions.data;

import android.location.Location;

import com.example.angai.diplom.directions.api.DirectionApi;
import com.example.angai.diplom.directions.models.DirectionsApiModel;
import com.example.angai.diplom.directions.models.DirectionsBodyModel;
import com.example.angai.diplom.home.data.BusStopApiModel;
import com.example.angai.diplom.utils.CustomRetrofit;
import com.example.angai.diplom.utils.CustomTextUtils;
import com.example.angai.diplom.utils.Debug;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class DirectionRepository implements IDirectionRepository {

    private static final String API_KEY = "AIzaSyCAexqgROeImxSGstpAFnwRh4103WPN00Y";

    private Retrofit retrofit;
    private DirectionApi directionApi;

    public DirectionRepository() {
        retrofit = CustomRetrofit.get();
        directionApi = retrofit.create(DirectionApi.class);

//        startCalculation();
    }

    @Override
    public Single<ArrayList<LatLng>> getDirection(String routeId) {
        return null;
    }
//
//    private void startCalculation() {
//        Disposable disposable = directionApi.getDirections()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(this::calculateDirections,
//                        throwable -> {
//                            Debug.d("");
//                        }
//                );
//    }

    private void calculateDirections(ArrayList<DirectionsApiModel> directionsApiModels) {
        for (DirectionsApiModel directionsApiModel : directionsApiModels) {

            final String routeId = "" + directionsApiModel.getRoute().getId();
            ArrayList<BusStopApiModel> busStops = directionsApiModel.getBusStopApiModels();

            int circleTimes = busStops.size() / 20;
            Single<ArrayList<LatLng>>[] singles = new Single[circleTimes];
            for (int i = 0; i < circleTimes; i++) {

                int firstItemIndex = Math.max(0, i * 20);
                int lastItemIndex = Math.min((i + 1) * 20, busStops.size() - 1);
                BusStopApiModel startBusStop = busStops.get(
                        firstItemIndex
                );

                BusStopApiModel endBusStop = busStops.get(
                        lastItemIndex
                );

                ArrayList<LatLng> wayPoints = new ArrayList<>(
                        directionsApiModel.getLatLngs().subList(
                                Math.min(firstItemIndex + 1, lastItemIndex),
                                Math.max(lastItemIndex - 1, firstItemIndex)
                        )
                );

                singles[i] = getDirection(
                        startBusStop.getLocation(),
                        endBusStop.getLocation(),
                        wayPoints);
            }

            Disposable disposable = Single.zipArray(objects -> {
                ArrayList<LatLng> latLngs = new ArrayList<>();
                for (Object o : objects) {
                    ArrayList<LatLng> lngsItem = (ArrayList<LatLng>) o;
                    latLngs.addAll(lngsItem);
                }
                return latLngs;
            }, singles).observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(latLngs -> sendDirectionsToApi(routeId, latLngs)
                            , throwable -> Debug.d(""));

        }
    }

    private Disposable sendDirectionsToApi(String routeId, ArrayList<LatLng> latLngs) {
        DirectionsBodyModel bodyModel = new DirectionsBodyModel();
        bodyModel.setRouteId(routeId);
        bodyModel.setPoints(latLngs);

        return directionApi.sendCalculatedDirection(bodyModel)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(() -> {
                    Debug.d("");
                }, throwable -> {
                    Debug.d("");
                });
    }

    private Single<ArrayList<LatLng>> getDirection(Location locationStart, Location locationStop, ArrayList<LatLng> latLngs) {
        GeoApiContext context = new GeoApiContext.Builder()
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .apiKey(API_KEY)
                .build();


        com.google.maps.model.LatLng[] apiLatLngs = new com.google.maps.model.LatLng[latLngs.size()];

        for (int i = 0; i < latLngs.size(); i++) {
            com.google.maps.model.LatLng newItem = new com.google.maps.model.LatLng(
                    latLngs.get(i).latitude,
                    latLngs.get(i).longitude
            );
            apiLatLngs[i] = newItem;
        }

        DirectionsApiRequest request = DirectionsApi.getDirections(context,
                locationToString(locationStart),
                locationToString(locationStop)
        ).waypoints(apiLatLngs);

        try {
            return Single.fromCallable(() -> getDirectionCoordinates(request))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io());
        } catch (Exception ex) {
            return Single.just(new ArrayList<LatLng>())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io());
        }
    }


    private Single<ArrayList<LatLng>> getDirection(Location locationStart, Location locationStop) {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(API_KEY)
                .build();

        DirectionsApiRequest request = DirectionsApi.getDirections(context,
                locationToString(locationStart),
                locationToString(locationStop)
        );

        try {
            return Single.fromCallable(() -> getDirectionCoordinates(request))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io());
        } catch (Exception ex) {
            return Single.just(new ArrayList<LatLng>())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io());
        }
    }

    private ArrayList<LatLng> getDirectionCoordinates(DirectionsApiRequest req) throws
            ApiException, InterruptedException, IOException {
        ArrayList<LatLng> path = new ArrayList<>();
        DirectionsResult res = req.await();
        try {
            //Loop through legs and steps to get encoded polylines of each step
            if (res.routes != null && res.routes.length > 0) {
                DirectionsRoute route = res.routes[0];

                if (route.legs != null) {
                    for (int i = 0; i < route.legs.length; i++) {
                        DirectionsLeg leg = route.legs[i];
                        if (leg.steps != null) {
                            for (int j = 0; j < leg.steps.length; j++) {
                                DirectionsStep step = leg.steps[j];
                                if (step.steps != null && step.steps.length > 0) {
                                    for (int k = 0; k < step.steps.length; k++) {
                                        DirectionsStep step1 = step.steps[k];
                                        EncodedPolyline points1 = step1.polyline;
                                        if (points1 != null) {
                                            //Decode polyline and add points to list of route coordinates
                                            List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                            for (com.google.maps.model.LatLng coord1 : coords1) {
                                                path.add(new LatLng(coord1.lat, coord1.lng));
                                            }
                                        }
                                    }
                                } else {
                                    EncodedPolyline points = step.polyline;
                                    if (points != null) {
                                        //Decode polyline and add points to list of route coordinates
                                        List<com.google.maps.model.LatLng> coords = points.decodePath();
                                        for (com.google.maps.model.LatLng coord : coords) {
                                            path.add(new LatLng(coord.lat, coord.lng));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return path;
    }

    private String locationToString(Location location) {
        String latitudeString = CustomTextUtils.format(location.getLatitude());
        String longitudeString = CustomTextUtils.format(location.getLongitude());

        return latitudeString + "," + longitudeString;
    }
}

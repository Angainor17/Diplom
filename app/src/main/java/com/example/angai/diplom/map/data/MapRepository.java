package com.example.angai.diplom.map.data;

import android.content.Context;
import android.location.Location;

import com.example.angai.diplom.app.App;
import com.example.angai.diplom.utils.CustomTextUtils;
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

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MapRepository implements IMapRepository {

    public static final String API_KEY = "AIzaSyCAexqgROeImxSGstpAFnwRh4103WPN00Y";
    @Inject
    Context context;

    public MapRepository() {
        App.getInjector().getMapComponent().inject(this);
    }

    @Override
    public Single<ArrayList<LatLng>> getDirection(Location locationStart, Location locationStop) {
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

    private ArrayList<LatLng> getDirectionCoordinates(DirectionsApiRequest req) throws ApiException, InterruptedException, IOException {
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

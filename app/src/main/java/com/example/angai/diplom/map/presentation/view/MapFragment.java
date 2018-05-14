package com.example.angai.diplom.map.presentation.view;

import android.graphics.Color;
import android.graphics.Point;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.example.angai.diplom.R;
import com.example.angai.diplom.app.App;
import com.example.angai.diplom.home.business.BusStop;
import com.example.angai.diplom.location.service.SendLocationService;
import com.example.angai.diplom.map.business.MapTransport;
import com.example.angai.diplom.map.business.RouteDirection;
import com.example.angai.diplom.map.presentation.presenter.IMapPresenter;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import javax.inject.Inject;

@EFragment(R.layout.fragment_map)
public class MapFragment extends MapViewMvpFragment<IMapView, IMapPresenter> implements IMapView, OnMapReadyCallback {

    @Inject
    IMapPresenter presenter;

    @ViewById(R.id.floating_action_menu)
    FloatingActionMenu floatingActionMenu;

    @ViewById(R.id.user_location_fab)
    FloatingActionButton userLocationFab;

    @ViewById(R.id.route_fab)
    FloatingActionButton routeFab;

    @ViewById(R.id.transport_fab)
    FloatingActionButton transportFab;

    private ArrayList<Marker> routeMarkers = new ArrayList<>();
    private ArrayList<MapTransport> transportPoints = new ArrayList<>();
    private Marker userLocationMarker;
    private Polyline routePolyline;

    private GoogleMap googleMap;

    public MapFragment() {
        App.getInjector().getMapComponent().inject(this);
    }

    @NonNull
    @Override
    public IMapPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void setRouteVisibility(boolean isVisible) {
        if (isVisible) {
            routeFab.setImageDrawable(
                    ContextCompat.getDrawable(getContext(), R.drawable.ic_icon_route_green)
            );
        } else {
            routeFab.setImageDrawable(
                    ContextCompat.getDrawable(getContext(), R.drawable.ic_icon_route_white)
            );
        }

        for (Marker marker : routeMarkers) {
            marker.setVisible(isVisible);
        }
        if (routePolyline != null) {
            routePolyline.setVisible(isVisible);
        }
    }

    @Override
    public void moveCamera(LatLng location) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 14));
    }

    @AfterViews
    public void afterViews() {
        mapView.onCreate(null);
        initArguments();
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        initGoogleMap(googleMap);
        presenter.onMapReady();
    }

    @Override
    public void hideUserLocation() {
        userLocationFab.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_near_me_white));
        if (userLocationMarker != null) {
            userLocationMarker.remove();
        }
    }

    @Click(R.id.other_fab)
    public void onOtherFabClick() {
        presenter.onSettingsFabClick();
    }

    @Click(R.id.transport_fab)
    public void onTransportFabClick() {
        presenter.onTransportFabClick();
    }

    @Click(R.id.route_fab)
    public void onRouteFabClick() {
        presenter.onRouteFabClick();
    }

    @Click(R.id.user_location_fab)
    public void onUserLocationFabClick() {
        presenter.onUserLocationFabClick();
    }

    @Override
    public void setUserLocation(Location initLocation) {
        userLocationFab.setImageDrawable(
                ContextCompat.getDrawable(getContext(), R.drawable.ic_near_me_green)
        );
        LatLng userLocation = new LatLng(initLocation.getLatitude(), initLocation.getLongitude());

        setUserLocation(
                googleMap.addMarker(new MarkerOptions().position(userLocation).title("ВЫ"))
        );

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 16));
    }

    @Override
    public void drawDirection(RouteDirection routeDirection) {
        routeFab.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_icon_route_green));
        removeRoute();
        routeMarkers.add(
                addBusStopMarker(routeDirection.getBusStopStart(), BitmapDescriptorFactory.HUE_GREEN)
        );
        routeMarkers.add(
                addBusStopMarker(routeDirection.getBusStopStop(), BitmapDescriptorFactory.HUE_RED)
        );

        if (routeDirection.hasDirections()) {
            drawPolyLine(routeDirection.getDirections());
        }

        googleMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(routeDirection.getBusStopStart().getLatLng(), 14)
        );
    }

    @Override
    public void showTransportMap(ArrayList<MapTransport> newMapPoints) {
        transportFab.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_topic_green));
        updateOldTransportMarkers(newMapPoints);
        addNewTransportMarkers(newMapPoints);
    }

    @Override
    public void hideTransportMap() {
        transportFab.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_topic_white));
        for (MapTransport mapTransport : transportPoints) {
            if (mapTransport.getMarker() != null) {
                mapTransport.getMarker().remove();
            }
        }
        transportPoints.clear();
    }

    private void removeRoute() {
        if (!routeMarkers.isEmpty()) {
            for (Marker marker : routeMarkers) {
                marker.remove();
            }
            routeMarkers.clear();
        }
        if (routePolyline != null) {
            routePolyline.remove();
            routePolyline = null;
        }
    }

    private void drawPolyLine(ArrayList<LatLng> routeDirection) {
        PolylineOptions opts = new PolylineOptions()
                .addAll(routeDirection)
                .color(Color.RED)
                .width(4);

        routePolyline = googleMap.addPolyline(opts);
    }

    private void addNewTransportMarkers(ArrayList<MapTransport> newMapPoints) {
        for (MapTransport newMarker : newMapPoints) {
            boolean isExist = false;

            for (MapTransport oldMarker : transportPoints) {
                if (oldMarker.equals(newMarker)) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                addNewTransportMarker(newMarker);
            }
        }
    }

    private void addNewTransportMarker(MapTransport newTransport) {
        Marker newMarker = (googleMap.addMarker(
                new MarkerOptions()
                        .position(newTransport.getLatLng())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                        .title(newTransport.getLabel())

        ));

        if (!newTransport.getLabel().isEmpty()) {
            newMarker.showInfoWindow();
        }

        newTransport.setMarker(newMarker);
        transportPoints.add(newTransport);
    }

    private void updateOldTransportMarkers(ArrayList<MapTransport> newMapPoints) {
        for (MapTransport oldMarker : transportPoints) {
            boolean isPointActual = false;
            for (MapTransport newPoint : newMapPoints) {
                if (oldMarker.equals(newPoint)) {
                    isPointActual = true;
                    animateMarker(oldMarker.getMarker(), newPoint.getLatLng());
                    oldMarker.updatePoint(newPoint);
                    break;
                }
            }

            if (!isPointActual) {
                oldMarker.getMarker().remove();
                oldMarker.setMarker(null);
            }
        }

        for (int i = 0; i < transportPoints.size(); i++) {
            if (transportPoints.get(i).getMarker() == null) {
                transportPoints.remove(i);
                i--;
            }
        }
    }

    private void initArguments() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            MapScreenParams mapScreenParams = new Gson().fromJson(
                    bundle.getString("param"),
                    MapScreenParams.class
            );
            presenter.setParams(mapScreenParams);
        }
    }

    private Marker addBusStopMarker(BusStop busStop, float icon) {
        return googleMap.addMarker(
                new MarkerOptions()
                        .position(busStop.getLatLng())
                        .icon(BitmapDescriptorFactory.defaultMarker(icon))
                        .title(busStop.getName())
        );
    }

    private void setUserLocation(Marker newMarker) {
        if (userLocationMarker != null) {
            userLocationMarker.remove();
        }
        userLocationMarker = newMarker;
    }

    private void initGoogleMap(GoogleMap newInstanceGoogleMap) {
        googleMap = newInstanceGoogleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.getUiSettings().setZoomControlsEnabled(false);
        googleMap.getUiSettings().setMapToolbarEnabled(false);
    }

    public void animateMarker(final Marker marker, final LatLng toPosition) {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        Projection projection = googleMap.getProjection();
        Point startPoint = projection.toScreenLocation(marker.getPosition());
        final LatLng startLatLng = projection.fromScreenLocation(startPoint);
        final long duration = SendLocationService.LOCATION_REQUEST_INTERVAL;

        final Interpolator interpolator = new LinearInterpolator();

        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed / duration);
                double lng = t * toPosition.longitude + (1 - t) * startLatLng.longitude;
                double lat = t * toPosition.latitude + (1 - t) * startLatLng.latitude;
                marker.setPosition(new LatLng(lat, lng));

                if (t < 1.0) {
                    // Post again 16ms later.
                    handler.postDelayed(this, 16);
                }
            }
        });
    }
}

package com.example.angai.diplom.map.presentation.view;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.angai.diplom.R;
import com.example.angai.diplom.app.App;
import com.example.angai.diplom.home.business.BusStop;
import com.example.angai.diplom.map.business.RouteDirection;
import com.example.angai.diplom.map.presentation.presenter.IMapPresenter;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
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

    private ArrayList<Marker> routeMarkers = new ArrayList<>();
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
    public void setInitLocation(Location initLocation) {
        LatLng userLocation = new LatLng(initLocation.getLatitude(), initLocation.getLongitude());

        setUserLocation(
                googleMap.addMarker(new MarkerOptions().position(userLocation).title("ВЫ"))
        );

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 16));
    }

    @Override
    public void drawDirection(RouteDirection routeDirection) {
        removeRoute();
        routeMarkers.add(
                addBusStopMarker(routeDirection.getBusStopStart(), BitmapDescriptorFactory.HUE_GREEN)
        );
        routeMarkers.add(
                addBusStopMarker(routeDirection.getBusStopStop(), BitmapDescriptorFactory.HUE_RED)
        );

        if (routeDirection.hasDirections()) {
            PolylineOptions opts = new PolylineOptions()
                    .addAll(routeDirection.getDirections())
                    .color(Color.RED)
                    .width(4);

            routePolyline = googleMap.addPolyline(opts);
        }
        googleMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(routeDirection.getBusStopStart().getLatLng(), 14)
        );
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

    private void initGoogleMap(GoogleMap newInstnceGoogleMap) {
        googleMap = newInstnceGoogleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.getUiSettings().setZoomControlsEnabled(false);
        googleMap.getUiSettings().setMapToolbarEnabled(false);
    }
}

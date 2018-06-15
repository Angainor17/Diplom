package com.diplom.angai.diplom.map.presentation.view;

import android.content.Context;
import android.location.Location;

import com.diplom.angai.diplom.map.business.MapTransport;
import com.diplom.angai.diplom.map.business.RouteDirection;
import com.google.android.gms.maps.model.LatLng;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.ArrayList;

public interface IMapView extends MvpView {

    void setUserLocation(Location initLocation);

    void hideUserLocation();

    void drawDirection(RouteDirection routeDirection);

    void setRouteVisibility(boolean isVisible);

    void moveCamera(LatLng location);

    void showTransportMap(ArrayList<MapTransport> mapTransports);

    void hideTransportMap();

    Context getContext();

}

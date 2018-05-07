package com.example.angai.diplom.map.presentation.view;

import android.location.Location;

import com.example.angai.diplom.map.business.RouteDirection;
import com.google.android.gms.maps.model.LatLng;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface IMapView extends MvpView {

    void setInitLocation(Location initLocation);

    void drawDirection(RouteDirection routeDirection);

    void setRouteVisibility(boolean isVisible);

    void moveCamera(LatLng location);

}

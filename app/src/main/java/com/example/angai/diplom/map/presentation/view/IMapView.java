package com.example.angai.diplom.map.presentation.view;

import android.location.Location;

import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface IMapView extends MvpView {

    void setInitLocation(Location initLocation);

    void onUserLocationFabClick();

    void onSettingsFabClick();

    void onRouteFabClick();

    void onTransportFabClick();

}

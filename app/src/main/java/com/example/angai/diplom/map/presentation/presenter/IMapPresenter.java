package com.example.angai.diplom.map.presentation.presenter;

import com.example.angai.diplom.map.presentation.view.IMapView;
import com.example.angai.diplom.map.presentation.view.MapScreenParams;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

public interface IMapPresenter extends MvpPresenter<IMapView> {

    void onMapReady();

    void onUserLocationFabClick();

    void onSettingsFabClick();

    void onRouteFabClick();

    void onTransportFabClick();

    void setParams(MapScreenParams mapScreenParams);
}

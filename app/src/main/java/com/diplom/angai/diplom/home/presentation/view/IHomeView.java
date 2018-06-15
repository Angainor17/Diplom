package com.diplom.angai.diplom.home.presentation.view;

import com.diplom.angai.diplom.home.business.BusStop;
import com.diplom.angai.diplom.router.presentation.view.IRouter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface IHomeView extends MvpView {

    void initBusStopViews(BusStop[] busStops);

    void onGetLocationBtnClick();

    void onBuildRouteBtnClick();

    void setBusStopNameFrom(String busStopName);

    void showIncorrectRouteMessage();

    IRouter getRouter();
}

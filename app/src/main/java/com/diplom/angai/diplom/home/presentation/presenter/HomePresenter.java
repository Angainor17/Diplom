package com.diplom.angai.diplom.home.presentation.presenter;

import android.support.annotation.Nullable;
import android.util.Log;

import com.diplom.angai.diplom.app.App;
import com.diplom.angai.diplom.home.business.BusStop;
import com.diplom.angai.diplom.home.business.IHomeInteractor;
import com.diplom.angai.diplom.home.presentation.view.IHomeView;
import com.diplom.angai.diplom.map.business.RouteDirection;
import com.diplom.angai.diplom.map.presentation.view.MapScreenParams;
import com.diplom.angai.diplom.router.business.screens.MapScreen;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends MvpBasePresenter<IHomeView> implements IHomePresenter {

    @Inject
    IHomeInteractor homeInteractor;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private BusStop[] busStops;

    public HomePresenter() {
        App.getInjector().getHomeComponent().inject(this);
        Log.d("", "");
    }

    @Override
    public void onUiInit() {
        initBusStopViews();
    }

    private void initBusStopViews() {
        compositeDisposable.add(homeInteractor.getBusStops()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        busStops -> {
                            if (busStops != null && busStops.length != 0) {
                                this.busStops = busStops;
                                if (isViewAttached()) {
                                    getView().initBusStopViews(busStops);
                                }
                            }
                        },
                        throwable -> {

                        })
        );
    }

    @Override
    public void onGetLocationBtnClick() {
        compositeDisposable.add(homeInteractor.getClosestBusStop()
                .subscribe(busStop -> {
                    if (isViewAttached()) {
                        getView().setBusStopNameFrom(busStop.getName());
                    }
                }, throwable -> {
                    throwable.printStackTrace();
                    if (isViewAttached()) {
                        getView().setBusStopNameFrom("");
                    }
                })
        );
    }

    @Override
    public void onBuildRouteBtnClick(String busStopStartText, String busStopStopText) {
        BusStop busStopStart = getBusStopFromString(busStopStartText);
        BusStop busStopStop = getBusStopFromString(busStopStopText);

        if (busStopStart != null && busStopStop != null) {
            if (isViewAttached()) {
                MapScreenParams mapScreenParams = new MapScreenParams(busStopStart, busStopStop);
                mapScreenParams.setNeedToShowRoute(true);

                getView().getRouter().showScreen(
                        new MapScreen(),
                        MapScreen.getBundle(mapScreenParams)
                );
            }

            homeInteractor.setRouteDirection(
                    new RouteDirection(busStopStart, busStopStop)
            );
        } else {
            if (isViewAttached()) {
                getView().showIncorrectRouteMessage();
            }
        }
    }

    @Override
    public void destroy() {
        compositeDisposable.clear();
        super.destroy();
    }

    @Nullable
    private BusStop getBusStopFromString(String text) {
        for (BusStop busStop : busStops) {
            if (busStop.getName().equals(text)) {
                return busStop;
            }
        }
        return null;
    }
}

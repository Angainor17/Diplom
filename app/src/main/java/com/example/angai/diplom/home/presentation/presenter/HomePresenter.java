package com.example.angai.diplom.home.presentation.presenter;

import android.util.Log;

import com.example.angai.diplom.app.App;
import com.example.angai.diplom.home.business.BusStop;
import com.example.angai.diplom.home.business.IHomeInteractor;
import com.example.angai.diplom.home.presentation.view.IHomeView;
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
    public void destroy() {
        compositeDisposable.clear();
        super.destroy();
    }
}

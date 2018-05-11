package com.example.angai.diplom.map.presentation.presenter;

import com.example.angai.diplom.app.App;
import com.example.angai.diplom.map.business.IMapInteractor;
import com.example.angai.diplom.map.business.RouteDirection;
import com.example.angai.diplom.map.data.MapPointsRequestParams;
import com.example.angai.diplom.map.presentation.view.IMapView;
import com.example.angai.diplom.map.presentation.view.MapScreenParams;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MapPresenter extends MvpBasePresenter<IMapView> implements IMapPresenter {

    @Inject
    IMapInteractor mapInteractor;

    private MapScreenParams mapScreenParams;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private CompositeDisposable mapPointsCompositeDisposable = new CompositeDisposable();
    private boolean isRouteVisible = false;
    private RouteDirection routeDirection = null;
    private boolean isTransportMapVisible = false;
    private boolean isUserLocationVisible = true;

    public MapPresenter() {
        App.getInjector().getMapComponent().inject(this);
    }

    @Override
    public void onMapReady() {
        if (this.mapScreenParams != null) {
            initMapScreenParams(mapScreenParams);
            return;
        }
        showUserLocation();
    }

    @Override
    public void setParams(MapScreenParams mapScreenParams) {
        this.mapScreenParams = mapScreenParams;
    }

    @Override
    public void onUserLocationFabClick() {
        if (isUserLocationVisible) {
            isUserLocationVisible = false;
            if (isViewAttached()) {
                getView().hideUserLocation();
            }
            return;
        }
        isUserLocationVisible = true;

        compositeDisposable.add(
                mapInteractor.getUserLocation().subscribe(location -> {
                    if (isViewAttached()) {
                        getView().setUserLocation(location);
                    }
                }, throwable -> {

                })
        );
    }

    @Override
    public void onSettingsFabClick() {

    }

    @Override
    public void onRouteFabClick() {
        if (!isViewAttached()) {
            return;
        }

        if (isRouteVisible) {
            isRouteVisible = false;
            getView().setRouteVisibility(false);
            return;
        }

        isRouteVisible = true;
        if (routeDirection != null) {
            if (mapScreenParams.isNeedToShowRoute()) {
                getView().setRouteVisibility(true);
                getView().moveCamera(routeDirection.getBusStopStart().getLatLng());
                return;
            }
            showDirection(routeDirection);
        }
        if (this.mapScreenParams != null) {
            initMapScreenParams(mapScreenParams);
        }

    }

    @Override
    public void onTransportFabClick() {
        if (isTransportMapVisible) {
            isTransportMapVisible = false;
            hideTransportMap();
        } else {
            isTransportMapVisible = true;
            showTransportMap();
        }
    }

    @Override
    public void onViewHide() {
        hideTransportMap();
        mapPointsCompositeDisposable.clear();
    }

    @Override
    public void onViewShow() {
        if (isTransportMapVisible) {
            showTransportMap();
        }
    }

    @Override
    public void detachView() {
        compositeDisposable.clear();
        super.detachView();
    }

    private void showTransportMap() {
        mapPointsCompositeDisposable.add(mapInteractor.getTransportMap(getPointsRequestParams())
                .subscribe((points) -> {
                    if (isViewAttached()) {
                        getView().showTransportMap(points);
                    }
                }, throwable -> {
                    if (isViewAttached()) {
                        getView().hideTransportMap();
                    }
                })
        );
    }

    private void hideTransportMap() {
        mapPointsCompositeDisposable.clear();
        mapInteractor.unSubscribeFromMapUpdates();
        if (isViewAttached()) {
            getView().hideTransportMap();
        }
    }

    private void showUserLocation() {
        isUserLocationVisible = true;
        compositeDisposable.add(mapInteractor.getUserLocation()
                .subscribe(location -> {
                            if (isViewAttached()) {
                                getView().setUserLocation(location);
                            }
                        }, throwable -> {
                            if (isViewAttached()) {
                                getView().setUserLocation(mapInteractor.getDefaultLocation());
                            }
                        }
                ));
    }

    private void initMapScreenParams(MapScreenParams mapScreenParams) {
        RouteDirection routeDirection = mapScreenParams.getRouteDirection();
        if (mapScreenParams.isNeedToShowRoute()) {
            showDirection(routeDirection);
        }
    }

    private void showDirection(RouteDirection routeDirection) {
        if (routeDirection != null) {
            compositeDisposable.add(mapInteractor.getDirection(routeDirection)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                            latLngs -> {
                                routeDirection.setDirections(latLngs);
                                MapPresenter.this.routeDirection = routeDirection;
                                if (isViewAttached()) {
                                    isRouteVisible = true;
                                    getView().drawDirection(routeDirection);
                                }
                            }, throwable -> isRouteVisible = false)
            );
        }
    }

    private MapPointsRequestParams getPointsRequestParams() {
        if (isViewAttached()) {
            MapPointsRequestParams params = new MapPointsRequestParams(getView().getContext());
            params.setRoutes(new ArrayList<>());// FIXME: 10.05.2018
            return params;
        }
        return new MapPointsRequestParams();
    }
}

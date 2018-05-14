package com.example.angai.diplom.transport.presentation.presenter;

import android.os.Bundle;

import com.example.angai.diplom.app.App;
import com.example.angai.diplom.map.business.RouteDirection;
import com.example.angai.diplom.map.presentation.view.MapScreenParams;
import com.example.angai.diplom.router.business.screens.MapScreen;
import com.example.angai.diplom.transport.business.ITransportInteractor;
import com.example.angai.diplom.transport.business.Route;
import com.example.angai.diplom.transport.presentation.view.ITransportView;
import com.google.gson.Gson;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

public class TransportPresenter extends MvpBasePresenter<ITransportView> implements ITransportPresenter {

    @Inject
    ITransportInteractor interactor;

    private Route[] routes;
    private int selectedPosition = 2;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public TransportPresenter() {
        App.getInjector().getTransportComponent().inject(this);
    }

    @Override
    public void viewInitAction() {
        if (routes == null || routes.length == 0) {
            initDiscreteScrollView();
        } else {
            refreshRoutesList();
        }
    }

    @Override
    public void showRouteOnClick() {
        if (!isViewAttached()) {
            return;
        }

        Route selectedRoute = getView().getSelectedRoute();

        if (selectedRoute != null) {
            compositeDisposable.add(Single.zip(
                    interactor.getRouteBusStops(selectedRoute),
                    interactor.getRoutePoints(selectedRoute),
                    (busStops, latLngs) -> {
                        RouteDirection routeDirection = new RouteDirection(
                                busStops.get(0),
                                busStops.get(busStops.size() - 1)
                        );
                        routeDirection.setDirections(latLngs);

                        return routeDirection;
                    })
                    .subscribe(direction -> {
                                if (isViewAttached()) {
                                    MapScreenParams mapScreenParams = new MapScreenParams();
                                    mapScreenParams.setNeedToShowRoute(true);
                                    mapScreenParams.setRouteDirection(direction);

                                    Bundle bundle = new Bundle();
                                    bundle.putString("param", new Gson().toJson(mapScreenParams));

                                    getView().getRouter().showScreen(new MapScreen(), bundle);
                                }
                            }
                    )
            );
        }
    }

    @Override
    public void destroy() {
        compositeDisposable.clear();
        super.destroy();
    }

    private void refreshRoutesList() {
        getView().setDiscreteScrollViewItems(routes);
        getView().setRouteListSelection(selectedPosition);
    }

    private void initDiscreteScrollView() {
        compositeDisposable.add(interactor.getAllRoutes().subscribe(
                routes -> {
                    this.routes = routes;
                    if (isViewAttached()) {
                        getView().setDiscreteScrollViewItems(routes);
                        getView().setRouteListSelection(selectedPosition);
                    }
                },
                throwable -> {
                }
                )
        );
    }
}

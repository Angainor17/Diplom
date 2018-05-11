package com.example.angai.diplom.transport.presentation.presenter;

import com.example.angai.diplom.app.App;
import com.example.angai.diplom.transport.business.ITransportInteractor;
import com.example.angai.diplom.transport.business.Route;
import com.example.angai.diplom.transport.presentation.view.ITransportView;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import javax.inject.Inject;

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

package com.example.angai.diplom.map.presentation.presenter;

import com.example.angai.diplom.app.App;
import com.example.angai.diplom.map.business.IMapInteractor;
import com.example.angai.diplom.map.presentation.view.IMapView;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class MapPresenter extends MvpBasePresenter<IMapView> implements IMapPresenter {

    @Inject
    IMapInteractor mapInteractor;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MapPresenter() {
        App.getInjector().getMapComponent().inject(this);
    }

    @Override
    public void onMapReady() {
        compositeDisposable.add(mapInteractor.getUserLocation()
                .subscribe(location -> {
                            if (isViewAttached()) {
                                getView().setInitLocation(location);
                            }
                        }, throwable -> {
                            if (isViewAttached()) {
                                getView().setInitLocation(mapInteractor.getDefaultLocation());
                            }
                        }
                ));
    }

    @Override
    public void detachView() {
        compositeDisposable.clear();
        super.detachView();
    }
}

package com.example.angai.diplom.transport.presentation.presenter;

import com.example.angai.diplom.app.App;
import com.example.angai.diplom.transport.business.ITransportInteractor;
import com.example.angai.diplom.transport.presentation.view.ITransportView;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class TransportPresenter extends MvpBasePresenter<ITransportView> implements ITransportPresenter {

    @Inject
    ITransportInteractor interactor;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public TransportPresenter() {
        App.getInjector().getTransportComponent().inject(this);
    }

    @Override
    public void viewInitAction() {
        initDiscreteScrollView();
    }

    private void initDiscreteScrollView() {
        compositeDisposable.add(interactor.getAllRoutes().subscribe(
                routes -> {
                    if (isViewAttached()) {
                        getView().setDiscreteScrollViewItems(routes);
                    }
                },
                throwable -> {
                }
                )
        );
    }

    @Override
    public void destroy() {
        compositeDisposable.clear();
        super.destroy();
    }
}

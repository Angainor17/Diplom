package com.diplom.angai.diplom.transport.presentation.presenter;

import com.diplom.angai.diplom.transport.presentation.view.ITransportView;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

public interface ITransportPresenter extends MvpPresenter<ITransportView> {

    void viewInitAction();

    void showRouteOnClick();

}

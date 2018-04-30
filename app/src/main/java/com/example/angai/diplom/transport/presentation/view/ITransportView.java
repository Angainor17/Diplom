package com.example.angai.diplom.transport.presentation.view;

import com.example.angai.diplom.transport.business.Route;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface ITransportView extends MvpView {

    void setDiscreteScrollViewItems(Route[] routes);

}

package com.diplom.angai.diplom.transport.presentation.view;

import com.diplom.angai.diplom.router.presentation.view.IRouter;
import com.diplom.angai.diplom.transport.business.Route;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface ITransportView extends MvpView {

    void setDiscreteScrollViewItems(Route[] routes);

    void setRouteListSelection(int position);

    Route getSelectedRoute();

    IRouter getRouter();

}

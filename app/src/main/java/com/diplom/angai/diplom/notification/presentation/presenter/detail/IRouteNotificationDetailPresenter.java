package com.diplom.angai.diplom.notification.presentation.presenter.detail;

import com.diplom.angai.diplom.notification.presentation.view.detail.IRouteNotificationDetailView;
import com.diplom.angai.diplom.notification.presentation.view.detail.RouteNotificationDetailParams;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

public interface IRouteNotificationDetailPresenter extends MvpPresenter<IRouteNotificationDetailView> {

    void backBtnClick();

    void setParams(RouteNotificationDetailParams params);

    boolean notificationCheckedBtnClick();

    boolean notificationRemovedBtnClick();

    void onViewDestroyed();

    void applyClick();

}

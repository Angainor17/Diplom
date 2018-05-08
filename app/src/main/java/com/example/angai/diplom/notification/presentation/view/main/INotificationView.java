package com.example.angai.diplom.notification.presentation.view.main;

import com.example.angai.diplom.notification.business.RouteNotification;
import com.example.angai.diplom.router.presentation.view.IRouter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.ArrayList;

public interface INotificationView extends MvpView {

    void setNotificationItems(ArrayList<RouteNotification> notifications);

    IRouter getRouter();

}

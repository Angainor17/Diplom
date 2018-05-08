package com.example.angai.diplom.notification.presentation.presenter.main;

import com.example.angai.diplom.notification.business.RouteNotification;
import com.example.angai.diplom.notification.presentation.view.main.INotificationView;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

public interface INotificationPresenter extends MvpPresenter<INotificationView> {

    void afterUiInit();

    void addNotificationClick();

    void onItemSelected(RouteNotification item);
}

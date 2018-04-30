package com.example.angai.diplom.notification.presentation.presenter;

import com.example.angai.diplom.app.App;
import com.example.angai.diplom.notification.presentation.view.INotificationView;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

public class NotificationPresenter extends MvpBasePresenter<INotificationView> implements INotificationPresenter {

    public NotificationPresenter() {
        App.getInjector().getNotificationComponent().inject(this);
    }
}

package com.diplom.angai.diplom.router.business.screens;

import android.support.v4.app.Fragment;

import com.diplom.angai.diplom.R;
import com.diplom.angai.diplom.notification.presentation.view.main.NotificationFragment_;
import com.diplom.angai.diplom.router.business.AppScreen;

public class NotificationsScreen extends AppScreen {

    @Override
    public int getId() {
        return R.id.navigation_notifications;
    }

    @Override
    public Fragment getFragment() {
        return new NotificationFragment_();
    }

    @Override
    public String toString() {
        return "NotificationsScreen";
    }
}

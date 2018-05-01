package com.example.angai.diplom.router.business.screens;

import android.support.v4.app.Fragment;

import com.example.angai.diplom.R;
import com.example.angai.diplom.notification.presentation.view.NotificationFragment_;
import com.example.angai.diplom.router.business.AppScreen;

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

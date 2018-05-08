package com.example.angai.diplom.router.business.detailScreen;

import android.support.v7.app.AppCompatActivity;

import com.example.angai.diplom.notification.presentation.view.detail.RouteNotificationDetailView_;

public class RouteNotificationDetailScreen extends DetailScreen {

    @Override
    public Class<? extends AppCompatActivity> getActivityClass() {
        return RouteNotificationDetailView_.class;
    }
}

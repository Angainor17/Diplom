package com.example.angai.diplom.notification.presentation.view.detail;

import android.support.annotation.NonNull;

import com.example.angai.diplom.R;
import com.example.angai.diplom.app.App;
import com.example.angai.diplom.notification.presentation.presenter.detail.IRouteNotificationDetailPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import javax.inject.Inject;

@EActivity(R.layout.activity_route_notification)
public class RouteNotificationDetailView extends MvpActivity<IRouteNotificationDetailView, IRouteNotificationDetailPresenter> implements IRouteNotificationDetailView {

    @Inject
    IRouteNotificationDetailPresenter presenter;

    @AfterViews
    public void initViews() {
        setTitle("");
    }

    @NonNull
    @Override
    public IRouteNotificationDetailPresenter createPresenter() {
        App.getInjector().getNotificationComponent().inject(this);
        return presenter;
    }
}

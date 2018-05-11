package com.example.angai.diplom.notification.presentation.view.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatSpinner;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TimePicker;

import com.example.angai.diplom.R;
import com.example.angai.diplom.app.App;
import com.example.angai.diplom.home.business.BusStop;
import com.example.angai.diplom.notification.business.RouteNotification;
import com.example.angai.diplom.notification.presentation.presenter.detail.IRouteNotificationDetailPresenter;
import com.example.angai.diplom.transport.business.Route;
import com.google.gson.Gson;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

@EActivity(R.layout.activity_route_notification)
public class RouteNotificationDetailView extends MvpActivity<IRouteNotificationDetailView, IRouteNotificationDetailPresenter> implements IRouteNotificationDetailView {

    @Inject
    IRouteNotificationDetailPresenter presenter;

    @ViewById(R.id.home)
    ImageView homeBtn;

    @ViewById(R.id.remove_img_btn)
    ImageButton removeImgBtn;

    @ViewById(R.id.activate_img_btn)
    ImageButton activateImgBtn;

    @ViewById(R.id.when_time_picker)
    TimePicker whenTimePicker;

    @ViewById(R.id.time_before_time_picker)
    TimePicker beforeTimePicker;

    @ViewById(R.id.bus_stop_spinner)
    AppCompatSpinner busStopSpinner;

    @ViewById(R.id.route_spinner)
    AppCompatSpinner routeSpinner;

    @AfterViews
    public void initViews() {
        initActionBar();
        initTimePickers();
        initRouteNotification();
    }

    @Click(R.id.home)
    public void backBtnClick() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        presenter.backBtnClick();
        super.onBackPressed();
    }

    @Override
    public RouteNotification.Time getTimeWhen() {
        return new RouteNotification.Time(
                whenTimePicker.getCurrentHour(),
                whenTimePicker.getCurrentMinute()
        );
    }

    @Override
    public RouteNotification.Time getTimeBefore() {
        return new RouteNotification.Time(
                beforeTimePicker.getCurrentHour(),
                beforeTimePicker.getCurrentMinute()
        );
    }

    @Override
    public void backButtonClick() {
        onBackPressed();
    }

    @Click(R.id.ok_fab)
    public void onApplyFabClick() {
        presenter.applyClick();
    }

    @Override
    public void initView(RouteNotification routeNotification) {
        whenTimePicker.setCurrentHour(routeNotification.getTimeWhenHour());
        whenTimePicker.setCurrentMinute(routeNotification.getTimeWhenMinute());

        beforeTimePicker.setCurrentHour(routeNotification.getTimeBeforeHour());
        beforeTimePicker.setCurrentMinute(routeNotification.getTimeBeforeMinute());
    }

    @Override
    public void initBusStopSpinner(BusStop[] busStops) {
        busStopSpinner.setAdapter(new ArrayAdapter<>(
                this,
                R.layout.spinner_item,
                BusStop.toStringArray(busStops)
        ));
    }

    @Override
    public void initRouteSpinner(Route[] routes) {
        routeSpinner.setAdapter(new ArrayAdapter<>(
                this,
                R.layout.spinner_item,
                Route.toStringArray(routes)
        ));
    }

    @Override
    public String getSelectedRoute() {
        return (String) routeSpinner.getSelectedItem();
    }

    @Override
    public String getSelectedBusStop() {
        return (String) busStopSpinner.getSelectedItem();
    }

    @Override
    public void setActivityResult(RouteNotification result) {
        Intent intent = new Intent();

        Bundle bundle = new Bundle();
        bundle.putString("result", new Gson().toJson(result));
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onViewDestroyed();
    }

    @NonNull
    @Override
    public IRouteNotificationDetailPresenter createPresenter() {
        App.getInjector().getNotificationComponent().inject(this);
        return presenter;
    }

    @Click(R.id.activate_img_btn)
    public void onNotificationActivateClick() {
        boolean status = presenter.notificationCheckedBtnClick();
        if (status) {
            activateImgBtn.setImageDrawable(
                    ContextCompat.getDrawable(this, R.drawable.ic_notifications_active_green)
            );
        } else {
            activateImgBtn.setImageDrawable(
                    ContextCompat.getDrawable(this, R.drawable.ic_notifications_off_black)
            );
        }
    }

    @Click(R.id.remove_img_btn)
    public void onNotificationRemoveClick() {
        boolean status = presenter.notificationRemovedBtnClick();
        setViewsEnable(!status);
    }

    private void setViewsEnable(boolean isEnable) {
        activateImgBtn.setEnabled(isEnable);
        whenTimePicker.setEnabled(isEnable);
        beforeTimePicker.setEnabled(isEnable);
        busStopSpinner.setEnabled(isEnable);
        routeSpinner.setEnabled(isEnable);

        findViewById(R.id.route_container).setEnabled(isEnable);
        findViewById(R.id.bus_stop_container).setEnabled(isEnable);
        findViewById(R.id.time_picker_container).setEnabled(isEnable);
    }

    private void initActionBar() {
        setTitle("");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    private void initRouteNotification() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                RouteNotificationDetailParams params = new Gson().fromJson(
                        bundle.getString("param"),
                        RouteNotificationDetailParams.class
                );
                presenter.setParams(params);
            }
        }
    }

    private void initTimePickers() {
        whenTimePicker.setIs24HourView(true);
        beforeTimePicker.setIs24HourView(true);
    }
}

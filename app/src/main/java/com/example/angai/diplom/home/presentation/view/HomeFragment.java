package com.example.angai.diplom.home.presentation.view;

import android.app.AlertDialog;
import android.support.annotation.NonNull;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.angai.diplom.R;
import com.example.angai.diplom.app.App;
import com.example.angai.diplom.home.business.BusStop;
import com.example.angai.diplom.home.presentation.presenter.IHomePresenter;
import com.example.angai.diplom.utils.customViews.CustomAutoCompleteTextView;
import com.example.angai.diplom.utils.mvp.CustomMvpFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

@EFragment(R.layout.fragment_home)
public class HomeFragment extends CustomMvpFragment<IHomeView, IHomePresenter> implements IHomeView {

    @Inject
    IHomePresenter presenter;

    @ViewById(R.id.auto_complete_tv_from)
    CustomAutoCompleteTextView busStopFromTv;

    @ViewById(R.id.auto_complete_tv_to)
    CustomAutoCompleteTextView busStopToTv;

    @ViewById(R.id.user_location_btn)
    ImageButton userLocationBtn;

    @ViewById(R.id.route_btn)
    Button buildRouteBtn;

    public HomeFragment() {
        App.getInjector().getHomeComponent().inject(this);
    }

    @Override
    public void initBusStopViews(BusStop[] busStops) {
        busStopFromTv.setAdapter(getBusStopsArrayAdapter(busStops));
        busStopToTv.setAdapter(getBusStopsArrayAdapter(busStops));
    }

    @Override
    public void onResume() {
        super.onResume();
        busStopFromTv.clearFocus();
        busStopToTv.clearFocus();
    }

    @AfterViews
    public void initUi() {
        presenter.onUiInit();
    }

    @NonNull
    @Override
    public IHomePresenter createPresenter() {
        return presenter;
    }

    @Override
    @Click(R.id.user_location_btn)
    public void onGetLocationBtnClick() {
        userLocationBtn.startAnimation(
                AnimationUtils.loadAnimation(getContext(), R.anim.my_location_animation)
        );
        presenter.onGetLocationBtnClick();
    }

    @Override
    public void setBusStopNameFrom(String busStopName) {
        busStopFromTv.setText(busStopName);
        busStopFromTv.dismissDropDown();
    }

    @Override
    @Click(R.id.route_btn)
    public void onBuildRouteBtnClick() {
        presenter.onBuildRouteBtnClick(
                busStopFromTv.getText().toString(),
                busStopToTv.getText().toString()
        );
    }

    @Override
    public void showIncorrectRouteMessage() {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setTitle("Выберете начальную и конечную остановку")
                .setPositiveButton("OK", null)
                .create();
        alertDialog.show();
    }

    private ArrayAdapter<String> getBusStopsArrayAdapter(BusStop[] busStops) {
        return new ArrayAdapter<>(
                getContext(), R.layout.bus_stop_list_item, BusStop.getStrings(busStops)
        );
    }
}

package com.example.angai.diplom.home.presentation.view;

import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.example.angai.diplom.R;
import com.example.angai.diplom.app.App;
import com.example.angai.diplom.home.business.BusStop;
import com.example.angai.diplom.home.presentation.presenter.IHomePresenter;
import com.example.angai.diplom.utils.customViews.CustomAutoCompleteTextView;
import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

@EFragment(R.layout.fragment_home)
public class HomeFragment extends MvpFragment<IHomeView, IHomePresenter> implements IHomeView {

    @Inject
    IHomePresenter presenter;

    @ViewById(R.id.auto_complete_tv_from)
    CustomAutoCompleteTextView busStopFromTv;

    @ViewById(R.id.auto_complete_tv_to)
    CustomAutoCompleteTextView busStopToTv;

    public HomeFragment() {
        App.getInjector().getHomeComponent().inject(this);
    }

    @Override
    public void initBusStopViews(BusStop[] busStops) {
        busStopFromTv.setAdapter(getBusStopsArrayAdapter(busStops));
        busStopToTv.setAdapter(getBusStopsArrayAdapter(busStops));
    }

    private ArrayAdapter<String> getBusStopsArrayAdapter(BusStop[] busStops) {
        return new ArrayAdapter<>(
                getContext(), android.R.layout.simple_list_item_1, BusStop.getStrings(busStops)
        );
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
}

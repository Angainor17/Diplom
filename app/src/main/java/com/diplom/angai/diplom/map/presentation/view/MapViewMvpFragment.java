package com.diplom.angai.diplom.map.presentation.view;

import com.diplom.angai.diplom.R;
import com.google.android.gms.maps.MapView;
import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public abstract class MapViewMvpFragment<V extends MvpView, P extends MvpPresenter<V>> extends MvpFragment<V, P> {

    @ViewById(R.id.map_view)
    protected MapView mapView;

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }
}

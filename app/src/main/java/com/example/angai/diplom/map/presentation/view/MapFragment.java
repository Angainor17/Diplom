package com.example.angai.diplom.map.presentation.view;

import android.support.annotation.NonNull;

import com.example.angai.diplom.R;
import com.example.angai.diplom.app.App;
import com.example.angai.diplom.map.presentation.presenter.IMapPresenter;
import com.example.angai.diplom.utils.mvp.CustomMvpFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;


@EFragment(R.layout.fragment_map)
public class MapFragment extends CustomMvpFragment<IMapView, IMapPresenter> implements IMapView, OnMapReadyCallback {

    @Inject
    IMapPresenter presenter;

    @ViewById(R.id.map_view)
    MapView mapView;

    private GoogleMap googleMap;

    public MapFragment() {
        App.getInjector().getMapComponent().inject(this);
    }

    @NonNull
    @Override
    public IMapPresenter createPresenter() {
        return presenter;
    }

    @AfterViews
    public void afterViews() {
        mapView.onCreate(null);
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        initGoogleMap();
    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    private void initGoogleMap() {
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

    }
}

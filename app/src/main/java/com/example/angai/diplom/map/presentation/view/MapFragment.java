package com.example.angai.diplom.map.presentation.view;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.angai.diplom.R;
import com.example.angai.diplom.app.App;
import com.example.angai.diplom.map.presentation.presenter.IMapPresenter;
import com.example.angai.diplom.utils.mvp.CustomMvpFragment;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

@EFragment(R.layout.fragment_map)
public class MapFragment extends CustomMvpFragment<IMapView, IMapPresenter> implements IMapView {

    private final String YANDEX_API_KEY = "de2fbc30-7d44-49d1-9fbd-037accba36a5";

    @Inject
    IMapPresenter presenter;

    @ViewById(R.id.map_view)
    MapView mapView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.getInjector().getMapComponent().inject(this);
        super.onCreate(savedInstanceState);
        MapKitFactory.setApiKey(YANDEX_API_KEY);

    }

    @AfterViews
    public void initViews() {
        MapKitFactory.initialize(getContext());

        mapView.getMap().move(
                new CameraPosition(new Point(33.538249, 44.604661), 11.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 0),
                null
        );
    }

    @NonNull
    @Override
    public IMapPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
        MapKitFactory.getInstance().onStart();
    }

    @AfterViews
    public void afterViews() {

    }
}

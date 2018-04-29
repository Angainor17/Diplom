package com.example.angai.diplom.map.presentation.view;

import com.example.angai.diplom.R;
import com.example.angai.diplom.app.App;
import com.example.angai.diplom.map.presentation.presenter.IMapPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpFragment;
//import com.yandex.mapkit.MapKitFactory;
//import com.yandex.mapkit.mapview.MapView;

import org.androidannotations.annotations.EFragment;

@EFragment(R.layout.fragment_map)
public class MapFragment extends MvpFragment<IMapView, IMapPresenter> implements IMapView {

//    @Inject
//    IMapPresenter presenter;

//    @ViewById(R.id.map_view)
//    MapView mapView;

    public MapFragment() {
//        App.getInjector().getMapComponent().inject(this);
//        MapKitFactory.setApiKey("Ваш API ключ");
//        MapKitFactory.initialize(getContext());
    }

    @Override
    public IMapPresenter createPresenter() {
        return presenter;
    }
}

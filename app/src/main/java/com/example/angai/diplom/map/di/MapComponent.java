package com.example.angai.diplom.map.di;

import com.example.angai.diplom.app.di.AppComponent;
import com.example.angai.diplom.home.data.MyLocationRepository;
import com.example.angai.diplom.map.business.MapInteractor;
import com.example.angai.diplom.map.presentation.presenter.MapPresenter;
import com.example.angai.diplom.map.presentation.view.MapFragment;

import dagger.Component;

@MapScope
@Component(dependencies = AppComponent.class, modules = MapModule.class)
public interface MapComponent {

    void inject(MapFragment mapFragment);

    void inject(MapInteractor mapFragment);

    void inject(MapPresenter mapFragment);

    void inject(MyLocationRepository myLocationRepository);

}

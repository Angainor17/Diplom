package com.diplom.angai.diplom.map.di;

import com.diplom.angai.diplom.app.di.AppComponent;
import com.diplom.angai.diplom.location.LocationRepository;
import com.diplom.angai.diplom.map.business.MapInteractor;
import com.diplom.angai.diplom.map.data.MapRepository;
import com.diplom.angai.diplom.map.presentation.presenter.MapPresenter;
import com.diplom.angai.diplom.map.presentation.view.MapFragment;

import dagger.Component;

@MapScope
@Component(dependencies = AppComponent.class, modules = MapModule.class)
public interface MapComponent {

    void inject(MapFragment mapFragment);

    void inject(MapInteractor mapFragment);

    void inject(MapPresenter mapFragment);

    void inject(LocationRepository myLocationRepository);

    void inject(MapRepository mapRepository);
}

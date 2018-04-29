package com.example.angai.diplom.map.di;

import com.example.angai.diplom.app.di.AppComponent;
import com.example.angai.diplom.map.presentation.view.IMapView;

import dagger.Component;

@MapScope
@Component(dependencies = AppComponent.class, modules = MapModule.class)
public interface MapComponent {

//    void inject(IMapView mapFragment);

}

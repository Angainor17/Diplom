package com.example.angai.diplom.home.di;

import com.example.angai.diplom.app.di.AppComponent;

import dagger.Component;

@HomeScope
@Component(dependencies = AppComponent.class, modules = HomeModule.class)
public interface HomeComponent {
}

package com.example.angai.diplom.app.di;

import android.content.Context;

import com.example.angai.diplom.location.service.SendLocationService;
import com.example.angai.diplom.utils.dataManager.SharedPreferencesHelper;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    Context getContext();

    void inject(SharedPreferencesHelper sharedPreferencesHelper);

    void inject(SendLocationService sendLocationService);

}

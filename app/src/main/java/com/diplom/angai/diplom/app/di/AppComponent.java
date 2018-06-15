package com.diplom.angai.diplom.app.di;

import android.content.Context;

import com.diplom.angai.diplom.location.service.SendLocationService;
import com.diplom.angai.diplom.utils.dataManager.SharedPreferencesHelper;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    Context getContext();

    void inject(SharedPreferencesHelper sharedPreferencesHelper);

    void inject(SendLocationService sendLocationService);

}

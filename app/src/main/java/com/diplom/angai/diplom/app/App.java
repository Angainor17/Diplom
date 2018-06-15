package com.diplom.angai.diplom.app;

import android.app.Application;

import com.diplom.angai.diplom.app.di.Injector;

public class App extends Application {

    private static Injector injector;

    @Override
    public void onCreate() {
        super.onCreate();

        injector = new Injector(getApplicationContext());
//        startService(new Intent(SendLocationService.class));
    }


    public static Injector getInjector() {
        return injector;
    }
}

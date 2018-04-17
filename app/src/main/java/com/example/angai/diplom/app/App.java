package com.example.angai.diplom.app;

import android.app.Application;

import com.example.angai.diplom.app.di.Injector;

public class App extends Application {

    private static Injector injector;

    @Override
    public void onCreate() {
        super.onCreate();

        injector = new Injector(getApplicationContext());
    }

    public static Injector getInjector() {
        return injector;
    }
}

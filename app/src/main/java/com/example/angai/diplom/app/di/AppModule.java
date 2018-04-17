package com.example.angai.diplom.app.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context appContext;

    public AppModule(Context appContext) {
        this.appContext = appContext;
    }

    @Singleton
    @Provides
    Context getAppContext() {
        return appContext;
    }
}

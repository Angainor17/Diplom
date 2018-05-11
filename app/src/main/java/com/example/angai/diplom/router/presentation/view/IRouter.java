package com.example.angai.diplom.router.presentation.view;

import android.os.Bundle;

import com.example.angai.diplom.router.business.AppScreen;
import com.example.angai.diplom.router.business.detailScreen.DetailScreen;
import com.example.angai.diplom.utils.mvp.OnActivityResultListener;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface IRouter extends MvpView {

    void showDetailScreen(DetailScreen detailScreen);

    void showDetailScreen(DetailScreen detailScreen, Bundle bundle);

    void showDetailScreen(DetailScreen detailScreen, Bundle bundle, OnActivityResultListener listener);

    void showScreen(AppScreen appScreen);

    void showScreen(AppScreen appScreen, Bundle bundle);

    AppScreen getCurrentScreen();
}

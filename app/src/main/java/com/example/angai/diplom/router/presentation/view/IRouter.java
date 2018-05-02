package com.example.angai.diplom.router.presentation.view;

import android.os.Bundle;

import com.example.angai.diplom.router.business.AppScreen;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface IRouter extends MvpView {

    void showScreen(AppScreen appScreen);

    void showScreen(AppScreen appScreen, Bundle bundle);

    AppScreen getCurrentScreen();
}

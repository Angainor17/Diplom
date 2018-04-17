package com.example.angai.diplom.router.presentation.view;

import com.example.angai.diplom.router.business.AppScreen;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface IRouter extends MvpView {

    void showScreen(AppScreen appScreen);
}

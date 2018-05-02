package com.example.angai.diplom.home.presentation.presenter;

import com.example.angai.diplom.home.presentation.view.IHomeView;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

public interface IHomePresenter extends MvpPresenter<IHomeView> {

    void onUiInit();

    void onGetLocationBtnClick();
}

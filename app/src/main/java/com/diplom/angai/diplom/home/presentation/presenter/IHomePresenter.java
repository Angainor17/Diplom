package com.diplom.angai.diplom.home.presentation.presenter;

import com.diplom.angai.diplom.home.presentation.view.IHomeView;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

public interface IHomePresenter extends MvpPresenter<IHomeView> {

    void onUiInit();

    void onGetLocationBtnClick();

    void onBuildRouteBtnClick(String busStopStart, String busStopStop);

}

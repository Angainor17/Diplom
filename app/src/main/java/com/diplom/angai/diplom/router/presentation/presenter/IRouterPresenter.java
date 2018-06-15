package com.diplom.angai.diplom.router.presentation.presenter;

import com.diplom.angai.diplom.router.business.AppScreen;
import com.diplom.angai.diplom.router.presentation.view.IRouter;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

public interface IRouterPresenter extends MvpPresenter<IRouter> {

    void screenSelected(AppScreen appScreen);

}

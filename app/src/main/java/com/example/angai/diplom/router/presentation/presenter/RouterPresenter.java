package com.example.angai.diplom.router.presentation.presenter;

import com.example.angai.diplom.router.business.AppScreen;
import com.example.angai.diplom.router.presentation.view.IRouter;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

public class RouterPresenter extends MvpBasePresenter<IRouter> implements IRouterPresenter {

    @Override
    public void screenSelected(AppScreen appScreen) {
        if(isViewAttached()){
            getView().showScreen(appScreen);
        }
    }
}

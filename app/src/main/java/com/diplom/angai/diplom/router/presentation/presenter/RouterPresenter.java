package com.diplom.angai.diplom.router.presentation.presenter;

import com.diplom.angai.diplom.router.business.AppScreen;
import com.diplom.angai.diplom.router.presentation.view.IRouter;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

public class RouterPresenter extends MvpBasePresenter<IRouter> implements IRouterPresenter {

    @Override
    public void screenSelected(AppScreen appScreen) {
        if (isViewAttached()) {
            if (getView().getCurrentScreen().getId() == appScreen.getId()) {
                return;
            }
            getView().showScreen(appScreen);
        }
    }
}

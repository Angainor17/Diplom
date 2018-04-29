package com.example.angai.diplom.router.presentation.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;

import com.example.angai.diplom.R;
import com.example.angai.diplom.app.App;
import com.example.angai.diplom.router.business.AppScreen;
import com.example.angai.diplom.router.business.screens.HomeScreen;
import com.example.angai.diplom.router.presentation.presenter.IRouterPresenter;
import com.example.angai.diplom.router.presentation.presenter.RouterPresenter;
import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateActivity;

import javax.inject.Inject;

public class MainActivity extends MvpViewStateActivity<IRouter, IRouterPresenter, RouterViewState> implements IRouter {

    @Inject
    IRouterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getInjector().getRouterComponent().inject(this);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initViews();
        showScreen(new HomeScreen());
    }

    @Override
    public void showScreen(AppScreen appScreen) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, appScreen.getFragment())
                .commit();
    }

    @NonNull
    @Override
    public IRouterPresenter createPresenter() {
        presenter = new RouterPresenter();
        return presenter;
    }

    @NonNull
    @Override
    public RouterViewState createViewState() {
        return new RouterViewState();
    }

    @Override
    public void onNewViewStateInstance() {

    }

    private void initViews() {
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(item -> {
            presenter.screenSelected(AppScreen.getById(item.getItemId()));
            return true;
        });
    }
}

package com.example.angai.diplom.router.presentation.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.angai.diplom.R;
import com.example.angai.diplom.app.App;
import com.example.angai.diplom.router.business.AppScreen;
import com.example.angai.diplom.router.business.screens.HomeScreen;
import com.example.angai.diplom.router.presentation.presenter.IRouterPresenter;
import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateActivity;

import javax.inject.Inject;

public class MainActivity extends MvpViewStateActivity<IRouter, IRouterPresenter, RouterViewState> implements IRouter {

    @Inject
    IRouterPresenter presenter;

    private AppScreen currentScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getInjector().getRouterComponent().inject(this);
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_main);
        initViews();
        showScreen(new HomeScreen());
    }

    @Override
    public void showScreen(AppScreen appScreen) {
        currentScreen = appScreen;
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_container, getFragment(appScreen), appScreen.toString())
                .commit();
    }

    @Override
    public AppScreen getCurrentScreen() {
        return currentScreen;
    }

    @NonNull
    @Override
    public IRouterPresenter createPresenter() {
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

    private Fragment getFragment(AppScreen appScreen) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(appScreen.toString());
        if (fragment == null) {
            fragment = appScreen.getFragment();
        }
        return fragment;
    }
}

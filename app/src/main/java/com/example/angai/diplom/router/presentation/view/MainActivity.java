package com.example.angai.diplom.router.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.example.angai.diplom.R;
import com.example.angai.diplom.app.App;
import com.example.angai.diplom.router.business.AppScreen;
import com.example.angai.diplom.router.business.screens.HomeScreen;
import com.example.angai.diplom.router.presentation.presenter.IRouterPresenter;
import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateActivity;

import java.util.Calendar;

import javax.inject.Inject;

public class MainActivity extends MvpViewStateActivity<IRouter, IRouterPresenter, RouterViewState> implements IRouter {

    @Inject
    IRouterPresenter presenter;

    private AppScreen currentScreen;
    private long lastBackPressedTime = 0;

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

    @Override
    public void onBackPressed() {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime > lastBackPressedTime + 5000) {
            Toast.makeText(
                    this,
                    "Нажмите кнопку НАЗАД, чтобы выйти",
                    Toast.LENGTH_SHORT
            ).show();
            lastBackPressedTime = currentTime;
        } else {
            closeApp();
        }
    }

    private void closeApp() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);

        finish();
        System.exit(0);
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

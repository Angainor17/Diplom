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
import com.example.angai.diplom.location.service.SendLocationService;
import com.example.angai.diplom.router.business.AppScreen;
import com.example.angai.diplom.router.business.detailScreen.DetailScreen;
import com.example.angai.diplom.router.business.screens.HomeScreen;
import com.example.angai.diplom.router.presentation.presenter.IRouterPresenter;
import com.example.angai.diplom.utils.mvp.OnActivityResultListener;
import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateActivity;

import java.util.Calendar;

import javax.inject.Inject;

public class MainActivity extends MvpViewStateActivity<IRouter, IRouterPresenter, RouterViewState> implements IRouter, OnActivityResultListener {

    @Inject
    IRouterPresenter presenter;

    private AppScreen currentScreen;
    private long lastBackPressedTime = 0;
    private BottomNavigationView bottomNavigationView;
    private OnActivityResultListener onActivityResultListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getInjector().getRouterComponent().inject(this);
        super.onCreate(savedInstanceState);
        startService(new Intent(this, SendLocationService.class));
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_main);
        initViews();
        showScreen(new HomeScreen());
    }

    @Override
    public void showScreen(AppScreen appScreen) {
        setCurrentScreen(appScreen);
        showFragment(getFragment(appScreen), appScreen);
    }

    @Override
    public void showScreen(AppScreen appScreen, Bundle bundle) {
        setCurrentScreen(appScreen);
        Fragment fragment = getFragment(appScreen);
        fragment.setArguments(bundle);
        showFragment(fragment, appScreen);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (onActivityResultListener != null) {
            onActivityResultListener.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void showDetailScreen(DetailScreen detailScreen, Bundle bundle, OnActivityResultListener listener) {
        Intent intent = new Intent(this, detailScreen.getActivityClass());
        intent.putExtras(bundle);
        this.onActivityResultListener = listener;
        startActivityForResult(intent, 1);
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

    @Override
    public void showDetailScreen(DetailScreen detailScreen) {
        Intent intent = new Intent(this, detailScreen.getActivityClass());
        startActivity(intent);
    }

    @Override
    public void showDetailScreen(DetailScreen detailScreen, Bundle bundle) {
        Intent intent = new Intent(this, detailScreen.getActivityClass());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, SendLocationService.class));
        super.onDestroy();
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
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            presenter.screenSelected(AppScreen.getById(item.getItemId()));
            return true;
        });
    }

    private void setCurrentScreen(AppScreen appScreen) {
        this.currentScreen = appScreen;
        bottomNavigationView.setSelectedItemId(appScreen.getId());
    }

    private void showFragment(Fragment fragment, AppScreen appScreen) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_container, fragment, appScreen.toString())
                .commit();
    }

    private Fragment getFragment(AppScreen appScreen) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(appScreen.toString());
        if (fragment == null) {
            fragment = appScreen.getFragment();
        }
        return fragment;
    }
}

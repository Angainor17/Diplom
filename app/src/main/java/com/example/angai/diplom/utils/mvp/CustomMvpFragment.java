package com.example.angai.diplom.utils.mvp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public abstract class CustomMvpFragment<V extends MvpView, P extends MvpPresenter<V>> extends MvpFragment<V, P> {

    protected void showActionBarTitle(String title) {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
//            actionBar.show();
//            actionBar.setTitle(title);
        }
    }

    protected void hideActionBar() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
        }
    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }
}

package com.example.angai.diplom.home.presentation.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.angai.diplom.R;
import com.example.angai.diplom.home.presentation.presenter.IHomePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpFragment;

public class HomeFragment extends MvpFragment<IHomeView, IHomePresenter> {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public IHomePresenter createPresenter() {
        return null;
    }
}

package com.example.angai.diplom.notification.presentation.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.angai.diplom.R;
import com.example.angai.diplom.notification.presentation.presenter.INotificationPresenter;
import com.example.angai.diplom.notification.presentation.presenter.NotificationPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpFragment;

public class NotificationFragment extends MvpFragment<INotificationView, INotificationPresenter> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @NonNull
    @Override
    public INotificationPresenter createPresenter() {
        return new NotificationPresenter();
    }
}

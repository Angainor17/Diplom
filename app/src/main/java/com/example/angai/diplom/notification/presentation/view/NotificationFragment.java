package com.example.angai.diplom.notification.presentation.view;

import android.support.annotation.NonNull;

import com.example.angai.diplom.R;
import com.example.angai.diplom.app.App;
import com.example.angai.diplom.notification.presentation.presenter.INotificationPresenter;
import com.example.angai.diplom.utils.mvp.CustomMvpFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

import javax.inject.Inject;

@EFragment(R.layout.fragment_notification)
public class NotificationFragment extends CustomMvpFragment<INotificationView, INotificationPresenter> {

    @Inject
    INotificationPresenter presenter;

    public NotificationFragment() {
        App.getInjector().getNotificationComponent().inject(this);
    }

    @NonNull
    @Override
    public INotificationPresenter createPresenter() {
        return presenter;
    }

    @AfterViews
    public void afterViews() {

    }
}

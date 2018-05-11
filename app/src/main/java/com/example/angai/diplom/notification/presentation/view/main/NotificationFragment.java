package com.example.angai.diplom.notification.presentation.view.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.angai.diplom.R;
import com.example.angai.diplom.app.App;
import com.example.angai.diplom.notification.business.RouteNotification;
import com.example.angai.diplom.notification.presentation.presenter.main.INotificationPresenter;
import com.example.angai.diplom.utils.mvp.CustomMvpFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import javax.inject.Inject;

@EFragment(R.layout.fragment_notification)
public class NotificationFragment extends CustomMvpFragment<INotificationView, INotificationPresenter> implements INotificationView, NotificationListAdapter.OnClickListener {

    @Inject
    INotificationPresenter presenter;

    @ViewById(R.id.notification_recycle_view)
    RecyclerView recyclerView;

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
        initRecyclerView();
        presenter.afterUiInit();
    }

    @Click(R.id.add_notification_fab)
    public void addNotificationClick() {
        presenter.addNotificationClick();
    }

    @Override
    public void setNotificationItems(ArrayList<RouteNotification> notifications) {
        NotificationListAdapter adapter = new NotificationListAdapter(getContext(), notifications);
        adapter.setItemClickListener(this);

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void refreshItems() {
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onClick(RouteNotification item) {
        presenter.onItemSelected(item);
    }

    private void initRecyclerView() {
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}

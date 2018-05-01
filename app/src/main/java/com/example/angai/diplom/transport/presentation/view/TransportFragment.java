package com.example.angai.diplom.transport.presentation.view;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;

import com.example.angai.diplom.R;
import com.example.angai.diplom.app.App;
import com.example.angai.diplom.transport.business.Route;
import com.example.angai.diplom.transport.business.TransportType;
import com.example.angai.diplom.transport.presentation.presenter.ITransportPresenter;
import com.example.angai.diplom.utils.mvp.CustomMvpFragment;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.Pivot;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

@EFragment(R.layout.fragment_transport)
public class TransportFragment extends CustomMvpFragment<ITransportView, ITransportPresenter> implements ITransportView {

    @Inject
    ITransportPresenter presenter;

    @ViewById(R.id.bus_number_picker)
    DiscreteScrollView discreteScrollView;

    @ViewById(R.id.bus_type)
    ImageView busIcon;

    @ViewById(R.id.topic_type)
    ImageView topicIcon;

    @ViewById(R.id.trolley_type)
    ImageView trolleyIcon;

    public TransportFragment() {
        App.getInjector().getTransportComponent().inject(this);
    }

    @NonNull
    @Override
    public ITransportPresenter createPresenter() {
        return presenter;
    }

    @AfterViews
    public void afterViews() {
        initDiscreteScrollView();
        presenter.viewInitAction();
    }

    @Override
    public void setRouteListSelection(int position) {
        discreteScrollView.scrollToPosition(position);
    }

    @Override
    public void setDiscreteScrollViewItems(Route[] routes) {
        discreteScrollView.setAdapter(new DiscreteScrollViewAdapter(
                getContext(), routes
        ));
    }

    public boolean isNeedToShowTransportType(TransportType[] transportTypeIcons, TransportType transportType) {
        for (TransportType item : transportTypeIcons) {
            if (item.name().equals(transportType.name())) {
                return true;
            }
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void hideImageView(ImageView imageView) {
        imageView.animate().scaleX(1f).scaleY(1f)
                .alpha(0f)
                .setDuration(200)
                .withEndAction(() -> imageView.setVisibility(View.GONE))
                .start();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void showImageView(TransportType[] transportTypes, ImageView imageView, TransportType transportType) {
        boolean shouldShow = isNeedToShowTransportType(transportTypes, transportType);
        if (shouldShow) {
            imageView.animate()
                    .alpha(1f)
                    .setDuration(200)
                    .withEndAction(() -> imageView.setVisibility(View.VISIBLE));
        } else {
            imageView.setVisibility(View.GONE);
        }
    }

    private void initDiscreteScrollView() {
        discreteScrollView.setSlideOnFling(true);
        discreteScrollView.addScrollStateChangeListener(new DiscreteScrollView.ScrollStateChangeListener<DiscreteScrollViewAdapter.RoutesViewHolder>() {
            @Override
            public void onScrollStart(@NonNull DiscreteScrollViewAdapter.RoutesViewHolder currentItemHolder, int adapterPosition) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    hideImageView(busIcon);
                    hideImageView(topicIcon);
                    hideImageView(trolleyIcon);
                }
            }

            @Override
            public void onScrollEnd(@NonNull DiscreteScrollViewAdapter.RoutesViewHolder currentItemHolder,
                                    int adapterPosition) {
                Route route = currentItemHolder.getItem(adapterPosition);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    showImageView(route.getTransportTypes(), busIcon, TransportType.BUS);
                    showImageView(route.getTransportTypes(), topicIcon, TransportType.TOPIC);
                    showImageView(route.getTransportTypes(), trolleyIcon, TransportType.TROLLEY);
                }
            }

            @Override
            public void onScroll(float scrollPosition, int currentPosition, int newPosition,
                                 @Nullable DiscreteScrollViewAdapter.RoutesViewHolder currentHolder,
                                 @Nullable DiscreteScrollViewAdapter.RoutesViewHolder newCurrent) {

            }
        });
        discreteScrollView.setItemTransitionTimeMillis(150);
        discreteScrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMaxScale(1.9f)
                .setMinScale(0.8f)
                .setPivotX(Pivot.X.CENTER)
                .setPivotY(Pivot.Y.CENTER)
                .build()
        );
    }
}

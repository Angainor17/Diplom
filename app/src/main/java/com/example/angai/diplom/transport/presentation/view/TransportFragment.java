package com.example.angai.diplom.transport.presentation.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.example.angai.diplom.R;
import com.example.angai.diplom.app.App;
import com.example.angai.diplom.transport.business.Route;
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
        showActionBarTitle("Ваш транспорт");

        presenter.viewInitAction();
    }

    @Override
    public void setDiscreteScrollViewItems(Route[] routes) {
        discreteScrollView.setAdapter(new DiscreteScrollViewAdapter(
                getContext(), routes
        ));
        discreteScrollView.scrollToPosition(2);
        initDiscreteScrollView();

    }

    private void initDiscreteScrollView() {
        discreteScrollView.setSlideOnFling(true);
        discreteScrollView.addOnItemChangedListener(new DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>() {
            @Override
            public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {

            }
        });
        discreteScrollView.addScrollStateChangeListener(new DiscreteScrollView.ScrollStateChangeListener<RecyclerView.ViewHolder>() {
            @Override
            public void onScrollStart(@NonNull RecyclerView.ViewHolder currentItemHolder, int adapterPosition) {

            }

            @Override
            public void onScrollEnd(@NonNull RecyclerView.ViewHolder currentItemHolder, int adapterPosition) {

            }

            @Override
            public void onScroll(float scrollPosition, int currentPosition, int newPosition, @Nullable RecyclerView.ViewHolder currentHolder, @Nullable RecyclerView.ViewHolder newCurrent) {

            }
        });
//        discreteScrollView.setOverScrollEnabled(false);
        discreteScrollView.setItemTransitionTimeMillis(150);
//        discreteScrollView.setSlideOnFling(true);
        discreteScrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMaxScale(1.9f)
                .setMinScale(0.8f)
                .setPivotX(Pivot.X.CENTER)
                .setPivotY(Pivot.Y.CENTER)
                .build());
//        discreteScrollView.setOffscreenItems(1);
    }
}

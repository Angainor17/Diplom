package com.example.angai.diplom.utils.mvp;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public abstract class CustomMvpFragment<V extends MvpView, P extends MvpPresenter<V>> extends MvpFragment<V, P> {

}

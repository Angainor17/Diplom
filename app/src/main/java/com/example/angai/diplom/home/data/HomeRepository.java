package com.example.angai.diplom.home.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.angai.diplom.app.App;
import com.example.angai.diplom.utils.CustomRetrofit;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class HomeRepository implements IHomeRepository {

    private final String BUS_STOP_SHARED_PREFERENCES = "BusStopSharedPreferences";

    @Inject
    Context context;

    private Retrofit retrofit;
    private BusStopApi busStopApi;
    private SharedPreferences sharedPreferences;

    public HomeRepository() {
        App.getInjector().getHomeComponent().inject(this);
        retrofit = CustomRetrofit.get();
        busStopApi = retrofit.create(BusStopApi.class);
        sharedPreferences = context.getSharedPreferences(BUS_STOP_SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public Single<ArrayList<BusStopApiModel>> getBusStopList() {
        return busStopApi.getBusStopList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}

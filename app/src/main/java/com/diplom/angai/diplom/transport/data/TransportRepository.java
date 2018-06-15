package com.diplom.angai.diplom.transport.data;

import com.diplom.angai.diplom.utils.CustomRetrofit;
import com.diplom.angai.diplom.utils.dataManager.SharedPreferencesHelper;

import java.util.ArrayList;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class TransportRepository implements ITransportRepository {

    private Retrofit retrofit;
    private BusRouteApi busRouteApi;
    private SharedPreferencesHelper sharedPreferences;

    public TransportRepository() {
        sharedPreferences = new SharedPreferencesHelper();
        retrofit = CustomRetrofit.get();
        busRouteApi = retrofit.create(BusRouteApi.class);
    }

    @Override
    public Single<ArrayList<RouteApiModel>> getAllBusRoutes() {
        return busRouteApi.getAllBusStops()
                .onErrorReturn((th) -> sharedPreferences.getRoutesList())
                .doOnSuccess(sharedPreferences::saveRouteList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}

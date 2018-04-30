package com.example.angai.diplom.transport.data;

import com.example.angai.diplom.utils.CustomRetrofit;

import java.util.ArrayList;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class TransportRepository implements ITransportRepository {

    private Retrofit retrofit;
    private BusRouteApi busRouteApi;

    public TransportRepository() {
        retrofit = CustomRetrofit.get();
        busRouteApi = retrofit.create(BusRouteApi.class);
    }

    @Override
    public Single<ArrayList<RouteApiModel>> getAllBusRoutes() {
        return busRouteApi.getAllBusStops()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }


}

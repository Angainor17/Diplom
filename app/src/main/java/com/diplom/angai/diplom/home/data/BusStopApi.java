package com.diplom.angai.diplom.home.data;

import java.util.ArrayList;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface BusStopApi {

    @GET("/getBusStops")
    Single<ArrayList<BusStopApiModel>> getBusStopList();

}

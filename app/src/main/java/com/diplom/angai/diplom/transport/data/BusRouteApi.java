package com.diplom.angai.diplom.transport.data;

import java.util.ArrayList;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface BusRouteApi {

    @GET("/getRoutes")
    Single<ArrayList<RouteApiModel>> getAllBusStops();
}

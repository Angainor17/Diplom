package com.example.angai.diplom.directions.api;

import com.example.angai.diplom.directions.models.DirectionsApiModel;
import com.example.angai.diplom.directions.models.DirectionsBodyModel;
import com.example.angai.diplom.directions.models.LatLngApiModel;
import com.example.angai.diplom.home.data.BusStopApiModel;

import java.util.ArrayList;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DirectionApi {

    @GET("/getRoutesBusStops")
    Single<ArrayList<DirectionsApiModel>> getDirections();

    @POST("/sendDirection")
    Completable sendCalculatedDirection(
            @Body DirectionsBodyModel directionsBodyModel
    );

    @GET("/getRouteDirection/{id}")
    Single<ArrayList<LatLngApiModel>> getRoutePoints(
            @Path("id") String id
    );

    @GET("/getBusStops/{id}")
    Single<ArrayList<BusStopApiModel>> getBusStops(
            @Path("id") int id
    );

}

package com.example.angai.diplom.directions.api;

import com.example.angai.diplom.directions.models.DirectionsApiModel;
import com.example.angai.diplom.directions.models.DirectionsBodyModel;

import java.util.ArrayList;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DirectionApi {

    @GET("/getRoutesBusStops")
    Single<ArrayList<DirectionsApiModel>> getDirections();

    @POST("/sendDirection")
    Completable sendCalculatedDirection(
            @Body DirectionsBodyModel directionsBodyModel
    );
}

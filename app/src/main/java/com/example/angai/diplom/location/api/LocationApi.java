package com.example.angai.diplom.location.api;

import com.example.angai.diplom.location.model.LocationApiModel;
import com.example.angai.diplom.transport.data.RouteApiModel;

import java.util.ArrayList;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LocationApi {

    @POST("/sendLocation")
    Single<ArrayList<RouteApiModel>> sendLocation(
            @Body LocationApiModel locationApiModel
    );
}

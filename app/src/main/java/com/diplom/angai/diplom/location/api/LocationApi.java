package com.diplom.angai.diplom.location.api;

import com.diplom.angai.diplom.location.model.LocationApiModel;
import com.diplom.angai.diplom.transport.data.RouteApiModel;

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

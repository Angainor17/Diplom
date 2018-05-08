package com.example.angai.diplom.location.api;

import com.example.angai.diplom.location.model.LocationApiModel;

import io.reactivex.Completable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LocationApi {

    @POST("/sendLocation")
    Completable sendLocation(
            @Body LocationApiModel locationApiModel
    );
}

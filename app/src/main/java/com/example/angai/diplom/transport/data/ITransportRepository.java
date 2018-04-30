package com.example.angai.diplom.transport.data;

import java.util.ArrayList;

import io.reactivex.Single;

public interface ITransportRepository {

    Single<ArrayList<RouteApiModel>> getAllBusRoutes();

}

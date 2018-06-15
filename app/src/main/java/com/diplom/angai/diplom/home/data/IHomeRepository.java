package com.diplom.angai.diplom.home.data;

import com.diplom.angai.diplom.map.business.RouteDirection;

import java.util.ArrayList;

import io.reactivex.Single;

public interface IHomeRepository {

    Single<ArrayList<BusStopApiModel>> getBusStopList();

    void saveRouteDirection(RouteDirection routeDirection);

}
package com.example.angai.diplom.home.data;

import java.util.ArrayList;

import io.reactivex.Single;

public interface IHomeRepository {

    Single<ArrayList<BusStopApiModel>> getBusStopList();

}

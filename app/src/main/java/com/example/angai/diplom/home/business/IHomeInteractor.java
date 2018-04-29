package com.example.angai.diplom.home.business;

import io.reactivex.Single;

public interface IHomeInteractor {

    Single<BusStop[]> getBusStops();
}

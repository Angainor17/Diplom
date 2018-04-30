package com.example.angai.diplom.transport.business;

import io.reactivex.Single;

public interface ITransportInteractor {

    Single<Route[]> getAllRoutes();

}

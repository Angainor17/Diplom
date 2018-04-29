package com.example.angai.diplom.home.business;

import com.example.angai.diplom.app.App;
import com.example.angai.diplom.home.data.BusStopApiModel;
import com.example.angai.diplom.home.data.IHomeRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class HomeInteractor implements IHomeInteractor {

    @Inject
    IHomeRepository homeRepository;

    public HomeInteractor() {
        App.getInjector().getHomeComponent().inject(this);
    }

    @Override
    public Single<BusStop[]> getBusStops() {
        return homeRepository.getBusStopList().map(busStopApiModels -> {
                    if (busStopApiModels != null) {
                        return BusStop.getArray(busStopApiModels.toArray(
                                new BusStopApiModel[busStopApiModels.size()])
                        );
                    }
                    return new BusStop[0];
                }
        );
    }
}

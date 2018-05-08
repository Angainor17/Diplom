package com.example.angai.diplom.location;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.example.angai.diplom.app.App;
import com.example.angai.diplom.location.api.LocationApi;
import com.example.angai.diplom.location.model.LocationApiModel;
import com.example.angai.diplom.utils.CustomRetrofit;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class LocationRepository implements ILocationRepository {

    @Inject
    Context context;

    private LocationManager locationManager;
    private LocationListener locationListener;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private LocationApi locationApi;

    public LocationRepository() {
        App.getInjector().getMapComponent().inject(this);
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        locationApi = CustomRetrofit.get().create(LocationApi.class);
    }

    @SuppressLint("MissingPermission")
    @Override
    public Observable<Location> getCurrentLocation() {
        if (hasGetLocationPermission()) {
            return Observable.just(new Location(""));
        }
        PublishSubject<Location> publishSubject = PublishSubject.create();
        locationListener = initLocationListener(publishSubject);
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1000,
                0,
                locationListener
        );

        return publishSubject
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable sendLocation(Location location) {
        return locationApi.sendLocation(new LocationApiModel(context, location))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void cancelSendLocation() {
        compositeDisposable.clear();
    }

    private boolean hasGetLocationPermission() {
        boolean accessFineLocationPermission = hasPermission(Manifest.permission.ACCESS_FINE_LOCATION);
        boolean accessCoarseLocationPermission = hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION);

        return accessCoarseLocationPermission && accessFineLocationPermission;
    }

    private int checkPermission(@NonNull String permission) {
        return ActivityCompat.checkSelfPermission(context, permission);
    }

    private boolean hasPermission(String permission) {
        return checkPermission(permission) != PackageManager.PERMISSION_GRANTED;
    }

    private LocationListener initLocationListener(PublishSubject<Location> publishSubject) {
        return new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                publishSubject.onNext(location);
                double longitude = location.getLongitude();
                double latitude = location.getLatitude();

                Log.d("debug", "longitude = " + longitude);
                Log.d("debug", "latitude = " + latitude);
                locationManager.removeUpdates(locationListener);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.d("debug", "onStatusChanged");
            }

            @Override
            public void onProviderEnabled(String provider) {
                Log.d("debug", "onProviderEnabled");
            }

            @Override
            public void onProviderDisabled(String provider) {
                Log.d("debug", "onProviderDisabled");
                publishSubject.onError(new Exception("onProviderDisabled"));
            }
        };
    }
}

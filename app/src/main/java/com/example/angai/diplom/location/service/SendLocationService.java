package com.example.angai.diplom.location.service;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.example.angai.diplom.app.App;
import com.example.angai.diplom.location.ILocationRepository;
import com.example.angai.diplom.location.LocationRepository;
import com.example.angai.diplom.transport.data.RouteApiModel;
import com.example.angai.diplom.utils.Debug;

import javax.inject.Inject;

public class SendLocationService extends IntentService {

    public static final int LOCATION_REQUEST_INTERVAL = 6 * 1000;
    @Inject
    Context context;

    private ILocationRepository locationRepository;
    private LocationManager locationManager;
    private LocationListener locationListener;

    public SendLocationService() {
        super("SendLocationService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        App.getInjector().getAppComponent().inject(this);
        locationRepository = new LocationRepository();
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        locationListener = initLocationListener();
        if (!hasGetLocationPermission()) {
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    2 * LOCATION_REQUEST_INTERVAL,
                    0,
                    locationListener
            );
        }
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

    private LocationListener initLocationListener() {
        return new LocationListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onLocationChanged(Location location) {
                Debug.d("longitude = " + location.getLongitude() + "| latitude = " + location.getLatitude());

                locationRepository.sendLocation(location).subscribe((routes) -> {
                    String routesString = "";

                    for (RouteApiModel routeApiModel : routes) {
                        routesString += routeApiModel.getName() + ", ";
                    }

                    Debug.d("routes = " + routesString);
                }, (t) -> Debug.d("serverError " + t.toString()));
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

            }
        };
    }

    @Override
    public void onDestroy() {

    }
}

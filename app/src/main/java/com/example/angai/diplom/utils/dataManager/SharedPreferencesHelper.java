package com.example.angai.diplom.utils.dataManager;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.angai.diplom.app.App;
import com.example.angai.diplom.home.data.BusStopApiModel;
import com.example.angai.diplom.map.business.RouteDirection;
import com.example.angai.diplom.notification.business.RouteNotification;
import com.example.angai.diplom.transport.data.RouteApiModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import javax.inject.Inject;

public class SharedPreferencesHelper {

    @Inject
    Context context;

    private final String BUS_STOPS_LIST_KEY = "bus_stop_list";
    private final String ROUTES_LIST_KEY = "routes_list";
    private final String ROUTE_DIRECTION_KEY = "route_direction";
    private final String ROUTE_NOTIFICATIONS_KEY = "route_notifications";

    private Gson gson;
    private SharedPreferences sharedPreferences;

    public SharedPreferencesHelper() {
        App.getInjector().getAppComponent().inject(this);
        sharedPreferences = context.getSharedPreferences(
                "shared_preferences",
                Context.MODE_PRIVATE
        );
        gson = new Gson();
    }

    public void saveBusStopList(ArrayList<BusStopApiModel> busStops) {
        sharedPreferences.edit()
                .putString(BUS_STOPS_LIST_KEY, gson.toJson(busStops))
                .apply();
    }

    public void saveRouteList(ArrayList<RouteApiModel> routes) {
        sharedPreferences.edit()
                .putString(ROUTES_LIST_KEY, gson.toJson(routes))
                .apply();
    }

    public ArrayList<BusStopApiModel> getBusStopsList() {
        String jsonBusStops = sharedPreferences.getString(BUS_STOPS_LIST_KEY, "[]");
        return gson.fromJson(jsonBusStops, new TypeToken<ArrayList<BusStopApiModel>>() {
        }.getType());
    }

    public ArrayList<RouteApiModel> getRoutesList() {
        String jsonRoutes = sharedPreferences.getString(ROUTES_LIST_KEY, "[]");
        return gson.fromJson(jsonRoutes, new TypeToken<ArrayList<RouteApiModel>>() {
        }.getType());
    }

    public RouteDirection getRouteDirection() {
        String jsonBusStops = sharedPreferences.getString(ROUTE_DIRECTION_KEY, "");
        return gson.fromJson(jsonBusStops, RouteDirection.class);
    }

    public void saveRoute(RouteDirection routeDirection) {
        sharedPreferences.edit().putString(
                ROUTE_DIRECTION_KEY,
                gson.toJson(routeDirection)
        ).apply();
    }

    public void saveNotifications(ArrayList<RouteNotification> notifications) {
        sharedPreferences.edit().putString(
                ROUTE_NOTIFICATIONS_KEY,
                gson.toJson(notifications)
        ).apply();
    }

    public ArrayList<RouteNotification> getNotifications() {
        String notifications = sharedPreferences.getString(ROUTE_NOTIFICATIONS_KEY, "[]");
        return gson.fromJson(notifications, new TypeToken<ArrayList<RouteNotification>>() {
        }.getType());
    }
}

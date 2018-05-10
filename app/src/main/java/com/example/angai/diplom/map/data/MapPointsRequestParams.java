package com.example.angai.diplom.map.data;

import android.content.Context;
import android.provider.Settings;

import com.example.angai.diplom.transport.business.Route;

import java.util.ArrayList;

public class MapPointsRequestParams {

    private String userId;
    private ArrayList<Route> routes;

    public MapPointsRequestParams(Context context) {
        userId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public MapPointsRequestParams() {
        userId = "";
        routes = new ArrayList<>();
    }

    public void setRoutes(ArrayList<Route> routes) {
        this.routes = routes;
    }

    public ArrayList<Route> getRoutes() {
        return routes;
    }
}

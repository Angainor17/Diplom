package com.diplom.angai.diplom.map.business;

import com.diplom.angai.diplom.map.data.MapPointApiModel;
import com.diplom.angai.diplom.transport.business.Route;
import com.diplom.angai.diplom.transport.data.RouteApiModel;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

public class MapTransport {

    private String id;
    private MapPoint mapPoint;
    private ArrayList<Route> routes;
    private Marker marker;

    public MapTransport(MapPointApiModel mapPointApiModel) {
        id = mapPointApiModel.getUserId();
        mapPoint = new MapPoint(mapPointApiModel);
        routes = new ArrayList<>();

        for (RouteApiModel routeApiModel : mapPointApiModel.getRoutes()) {
            routes.add(new Route(routeApiModel));
        }
    }

    public String getId() {
        return id;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public int getColorId() {
        return mapPoint.getColor();
    }

    public String getLabel() {
        return mapPoint.getLabel();
    }

    public LatLng getLatLng() {
        return new LatLng(getLatitude(), getLongitude());
    }

    private Double getLongitude() {
        return mapPoint.getLongitude();
    }

    private Double getLatitude() {
        return mapPoint.getLatitude();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MapTransport && id.equals(((MapTransport) obj).id);
    }

    public void updatePoint(MapTransport newPoint) {
        this.mapPoint = newPoint.mapPoint;
        this.routes = newPoint.routes;
    }
}

package com.example.angai.diplom.map.business;

import com.example.angai.diplom.R;
import com.example.angai.diplom.map.data.MapPointApiModel;

public class MapPoint {

    private String userId;
    private int colorId;
    private Double longitude;
    private Double latitude;

    private String label;

    public MapPoint(MapPointApiModel mapPointApiModel) {
        initLabel(mapPointApiModel);
        initColor();
        this.latitude = Double.parseDouble(mapPointApiModel.getLatitude());
        this.longitude = Double.parseDouble(mapPointApiModel.getLongitude());
    }


    public String getUserId() {
        return userId;
    }

    public int getColor() {
        return colorId;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public String getLabel() {
        return label;
    }

    private void initLabel(MapPointApiModel mapPointApiModel) {
        if (mapPointApiModel.getRoutes().size() == 1) {
            label = mapPointApiModel.getRoutes().get(0).getName();
        }
    }

    private void initColor() {
        colorId = R.color.map_point_color_1;
        // TODO: 10.05.2018
    }
}

package com.diplom.angai.diplom.map.data;

import com.diplom.angai.diplom.utils.CustomTextUtils;

public class CoordinatesModel {

    private String longitude;
    private String latitude;

    public CoordinatesModel(double longitude, double latitude) {
        this.longitude = CustomTextUtils.format(longitude);
        this.latitude = CustomTextUtils.format(latitude);
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }
}

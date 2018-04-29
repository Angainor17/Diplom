package com.example.angai.diplom.home.business;

import com.example.angai.diplom.home.data.CoordinateApiModel;

public class Coordinate {

    private String x;
    private String y;

    public Coordinate(CoordinateApiModel apiModel) {
        this.x = apiModel.getX();
        this.y = apiModel.getY();
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }
}


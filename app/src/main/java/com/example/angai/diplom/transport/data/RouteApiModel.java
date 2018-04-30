package com.example.angai.diplom.transport.data;

import com.google.gson.annotations.SerializedName;

public class RouteApiModel {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

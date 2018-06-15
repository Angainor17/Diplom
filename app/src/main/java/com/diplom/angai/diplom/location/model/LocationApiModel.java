package com.diplom.angai.diplom.location.model;

import android.content.Context;
import android.location.Location;
import android.provider.Settings;

import com.diplom.angai.diplom.utils.CustomTextUtils;
import com.google.gson.annotations.SerializedName;

import java.util.Calendar;

public class LocationApiModel {

    @SerializedName("longitude")
    private String longitude;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("userId")
    private String userId;

    @SerializedName("time")
    private String time;

    public LocationApiModel(Context context, Location location) {
        this.userId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        this.latitude = CustomTextUtils.format(location.getLatitude());
        this.longitude = CustomTextUtils.format(location.getLongitude());
        this.time = "" + Calendar.getInstance().getTimeInMillis();
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

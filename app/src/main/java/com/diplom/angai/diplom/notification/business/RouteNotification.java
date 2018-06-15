package com.diplom.angai.diplom.notification.business;

import com.diplom.angai.diplom.home.business.BusStop;
import com.diplom.angai.diplom.transport.business.Route;

import java.util.Calendar;

public class RouteNotification {

    private long id;
    private Time timeWhen;
    private Time timeBefore;
    private boolean isActive;
    private BusStop busStop;
    private Route route;
    private boolean isRemoved = false;

    public RouteNotification() {
        id = Calendar.getInstance().getTimeInMillis();
        timeBefore = new Time(0, 10);
        timeWhen = new Time(7, 10);
        isActive = true;
        busStop = null;
        route = null;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTimeWhen(Time timeWhen) {
        this.timeWhen = timeWhen;
    }

    public void setTimeBefore(Time timeBefore) {
        this.timeBefore = timeBefore;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setBusStop(BusStop busStop) {
        this.busStop = busStop;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public void setRemoved(boolean removed) {
        isRemoved = removed;
    }

    public int getTimeWhenHour() {
        return timeWhen.hour;
    }

    public int getTimeWhenMinute() {
        return timeWhen.minute;
    }

    public int getTimeBeforeHour() {
        return timeBefore.hour;
    }

    public int getTimeBeforeMinute() {
        return timeBefore.minute;
    }

    public long getId() {
        return id;
    }

    public boolean isChecked() {
        return isActive;
    }

    public String getRouteName() {
        return route == null ? "" : route.getName();
    }

    public String getBusStopName() {
        return busStop.getName();
    }

    public String getRemainingTime() {
        return "Осталось 17 мин";// TODO: 08.05.2018
    }

    public void update(RouteNotification updatedNotification) {
        this.timeWhen = updatedNotification.timeWhen;
        this.timeBefore = updatedNotification.timeBefore;
        this.isActive = updatedNotification.isActive;
        this.busStop = updatedNotification.busStop;
        this.route = updatedNotification.route;
    }

    public String getTimeBefore() {
        return timeBefore.toString();
    }

    public String getTimeWhen() {
        return timeWhen.toString();
    }

    public static class Time {
        int hour;
        int minute;

        public Time(int hour, int minute) {
            this.hour = hour;
            this.minute = minute;
        }

        @Override
        public String toString() {
            String hourString = hour > 9 ? "" + hour : "0" + hour;
            String minuteString = minute > 9 ? "" + minute : "0" + minute;

            return hourString + ":" + minuteString;
        }
    }
}

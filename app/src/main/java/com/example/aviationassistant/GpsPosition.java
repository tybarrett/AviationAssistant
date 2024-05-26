package com.example.aviationassistant;

public class GpsPosition {
    private double lat;
    private double lon;
    private double accuracy;

    public GpsPosition(double lat, double lon, double accuracy) {
        this.lat = lat;
        this.lon = lon;
        this.accuracy = accuracy;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public double getAccuracy() {
        return accuracy;
    }
}

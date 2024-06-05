package com.example.aviationassistant;

public class GpsPosition {
    private double lat;
    private double lon;
    private double accuracy;
    private int numSatellites;
    private double mslAltitudeMeters;

    public GpsPosition(double lat, double lon, double accuracy) {
        this.lat = lat;
        this.lon = lon;
        this.accuracy = accuracy;
    }

    public GpsPosition(double lat, double lon, double accuracy, int numSatellites) {
        this.lat = lat;
        this.lon = lon;
        this.accuracy = accuracy;
        this.numSatellites = numSatellites;
    }

    public GpsPosition(double lat, double lon, double accuracy, int numSatellites, double mslAltitudeMeters) {
        this.lat = lat;
        this.lon = lon;
        this.accuracy = accuracy;
        this.numSatellites = numSatellites;
        this.mslAltitudeMeters = mslAltitudeMeters;
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

    public int getNumSatellites() {
        return numSatellites;
    }

    public double getMslAltitudeMeters() {
        return mslAltitudeMeters;
    }
}

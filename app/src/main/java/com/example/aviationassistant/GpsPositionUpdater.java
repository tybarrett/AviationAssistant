package com.example.aviationassistant;

import android.location.Location;
import android.location.LocationListener;

import androidx.annotation.RequiresApi;

import java.util.Random;


public class GpsPositionUpdater extends PeriodicComponent implements LocationListener {

    private double latestLat = 0.0;
    private double latestLon = 0.0;
    private double latestAccuracy = 0;
    private int numSatellites = 0;
    private double latestMslAltitude = 0.0;
    private boolean receivedLocation = false;

    public GpsPositionUpdater() {

    }

    @Override
    public Object update() {
        if (receivedLocation) {
            return new GpsPosition(latestLat, latestLon, latestAccuracy, numSatellites, latestMslAltitude);
        } else {
            return null;
        }
    }

    @RequiresApi(api = 34)
    @Override
    public void onLocationChanged(Location loc) {

        latestLat = loc.getLatitude();
        latestLon = loc.getLongitude();
        latestAccuracy = loc.getAccuracy();
//        numSatellites = loc.getExtras().getInt("satellites");
        latestMslAltitude = loc.getMslAltitudeMeters();

        receivedLocation = true;
    }

}

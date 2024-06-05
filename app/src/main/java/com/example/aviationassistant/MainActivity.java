package com.example.aviationassistant;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.aviationassistant.databinding.ActivityMainBinding;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private GpsPositionUpdater gpsPositionUpdater;

    private ActivityMainBinding binding;

    @SuppressLint("MissingPermission")
    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String[] location_permissions = {android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        requestPermissions(location_permissions, 0);

        gpsPositionUpdater = new GpsPositionUpdater();
        gpsPositionUpdater.registerCallback(new LocationUpdater());
        gpsPositionUpdater.start();

        LocationManager locationmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        while (true) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                continue;
            }

            break;
        }
        locationmanager.requestLocationUpdates(LocationManager.FUSED_PROVIDER, 1000, 10, gpsPositionUpdater);
    }

    class LocationUpdater extends com.example.headunitapplication.CallbackObject<GpsPosition> {

        DecimalFormat decimalFormat = new DecimalFormat("00.000");
        DecimalFormat degreesFormat = new DecimalFormat("000");

        @Override
        public void safe_update(GpsPosition pos) {
            TextView latitudeView = findViewById(R.id.latitudeTextView);
//            final String northSouth;
//            double lat = pos.getLat();
//            if (lat < 0) {
//                northSouth = "S ";
//                lat = Math.abs(lat);
//            } else {
//                northSouth = "N ";
//            }
//            int latDegrees = (int) lat;
//            String latDegreesString = degreesFormat.format(latDegrees);
//            double minutes = (lat % 1.0) * 60.0;
//            String minutesString = decimalFormat.format(minutes);

            String latString = String.format("%.7f", pos.getLat());
            String lonString = String.format("%.7f", pos.getLon());


            TextView longitude = findViewById(R.id.longitudeTextView);
//            final String eastWest;
//            double lon = pos.getLon();
//            if (lon < 0) {
//                eastWest = "W ";
//                lon = Math.abs(lon);
//            } else {
//                eastWest = "E";
//            }
//
//            int lonDegrees = (int) lon;
//            String lonDegreesString = degreesFormat.format(lonDegrees);
//            double lonMinutes = (lon % 1.0) * 60;
//            String lonMinutesString = decimalFormat.format(lonMinutes);

            TextView delusionText = findViewById(R.id.hdopTextView);
            String base = " HDOP";
            double delusion = pos.getAccuracy();
            String delusionString = decimalFormat.format(delusion);

            TextView satelliteTextView = findViewById(R.id.satcountTextView);
            String numSatellites = String.valueOf(pos.getNumSatellites());

            TextView mslAltitudeTextView = findViewById(R.id.mslAltTextView);
            String abbrAltitude = String.format("%.1f", pos.getMslAltitudeMeters());

            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    latitudeView.setText("LAT " + latString);
                    longitude.setText("LON " + lonString);
                    delusionText.setText(delusionString + base);
                    satelliteTextView.setText(numSatellites + " SATCOUNT");
                    mslAltitudeTextView.setText(abbrAltitude + " METERS MSL");
                }
            });
        }
    }
}
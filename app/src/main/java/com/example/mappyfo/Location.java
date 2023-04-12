package com.example.mappyfo;

import androidx.annotation.StringRes;

import com.google.android.gms.maps.model.LatLng;

import java.util.Locale;

public class Location {
    private @StringRes int strIdTitle;
    private @StringRes int strIdDescription;
    private double latitude;
    private double longitude;
    private LatLng position;

    public Location(@StringRes int strIdTitle, @StringRes int strIdDescription, double latitude, double longitude) {
        this.strIdTitle = strIdTitle;
        this.strIdDescription = strIdDescription;
        this.latitude = latitude;
        this.longitude = longitude;
        this.position = new LatLng(latitude, longitude);
    }

    public @StringRes int getStrIdTitle() {
        return strIdTitle;
    }

    public void setStrIdTitle(@StringRes int strIdTitle) {
        this.strIdTitle = strIdTitle;
    }

    public @StringRes int getStrIdDescription() {
        return strIdDescription;
    }

    public void setStrIdDescription(@StringRes int strIdDescription) {
        this.strIdDescription = strIdDescription;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public LatLng getPosition() {
        return position;
    }

    public String getFormattedLatLong() {
        return String.format(Locale.getDefault(), "Lat: %1$.5f, Long: %2$.5f", latitude, longitude);
    }
}

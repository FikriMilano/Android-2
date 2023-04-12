package com.example.mappyfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.mappyfo.databinding.ActivityMapsBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, OnLocationMarkerClickListener, SensorEventListener {

    private FragmentManagerHelper fragmentManagerHelper;
    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private List<Location> locations;

    private SensorManager sensorManager;
    private Sensor sensorAccelerometer;
    private Sensor sensorMagnetometer;
    private float[] floatGravity = new float[3];
    private float[] floatGeoMagnetic = new float[3];
    private float[] floatOrientation = new float[3];
    private float[] floatRotationMatrix = new float[9];

    private ImageView imgCompass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorMagnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        fragmentManagerHelper = new FragmentManagerHelper(getSupportFragmentManager(), R.id.fragment_container);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);
        imgCompass = findViewById(R.id.compass);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (sensorAccelerometer == null) return;
        sensorManager.registerListener(this, sensorAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        if (sensorMagnetometer == null) return;
        sensorManager.registerListener(this, sensorMagnetometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        initMarkers(mMap);
        setOnPoiClick(mMap);
    }

    private void initMarkers(GoogleMap map) {
        locations = DummyData.getDefaultLocations();
        for (Location location : locations) {
            String title = getString(location.getStrIdTitle());
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(location.getPosition())
                    .title(title)
                    .snippet(location.getFormattedLatLong());
            map.addMarker(markerOptions);
        }
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(locations.get(0).getPosition(), 0));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.map_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.language:
                Intent languageIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(languageIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setOnPoiClick(final GoogleMap map) {
        map.setOnMarkerClickListener(marker -> {
            Location currentLocation = findLocationFrom(marker);
            onLocationMarkerClick(currentLocation);
            return true;
        });
    }

    private Location findLocationFrom(Marker marker) {
        return locations.stream()
                .filter(location -> isPositionEquals(marker, location))
                .findFirst()
                .get();
    }

    private boolean isPositionEquals(Marker marker, Location location) {
        return Objects.equals(marker.getPosition().latitude, location.getPosition().latitude) && Objects.equals(marker.getPosition().longitude, location.getPosition().longitude);
    }

    @Override
    public void onLocationMarkerClick(Location location) {
        DescriptionFragment fragment = DescriptionFragment.newInstance(location.getStrIdTitle(), location.getStrIdDescription());
        fragment.setOnCloseFragmentListener(() -> {
            Fragment currentFragment = fragmentManagerHelper.getCurrentFragment();
            fragmentManagerHelper.remove(currentFragment);
        });
        fragmentManagerHelper.replace(fragment);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensorType = event.sensor.getType();
        switch (sensorType) {
            case Sensor.TYPE_ACCELEROMETER:
                floatGravity = event.values;

                SensorManager.getRotationMatrix(floatRotationMatrix, null, floatGravity, floatGeoMagnetic);
                SensorManager.getOrientation(floatRotationMatrix, floatOrientation);

                imgCompass.setRotation((float) (-floatOrientation[0] * 180 / 3.14159));
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                floatGeoMagnetic = event.values;

                SensorManager.getRotationMatrix(floatRotationMatrix, null, floatGravity, floatGeoMagnetic);
                SensorManager.getOrientation(floatRotationMatrix, floatOrientation);

                imgCompass.setRotation((float) (-floatOrientation[0] * 180 / 3.14159));
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
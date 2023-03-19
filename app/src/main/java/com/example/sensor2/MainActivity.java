package com.example.sensor2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensorAccelerometer;
    private Sensor sensorMagnetometer;

    private TextView tvAzimuth;
    private TextView tvPitch;
    private TextView tvRoll;
    private ImageView imgSpotTop;
    private ImageView imgSpotBottom;
    private ImageView imgSpotStart;
    private ImageView imgSpotEnd;

    private float[] sensorValueAccelerometer = new float[3];
    private float[] sensorValueMagnetometer = new float[3];

    private static final float VALUE_DRIFT =  0.05f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorMagnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        tvAzimuth = findViewById(R.id.value_azimuth);
        tvPitch = findViewById(R.id.value_pitch);
        tvRoll = findViewById(R.id.value_roll);
        imgSpotTop = findViewById(R.id.spot_top);
        imgSpotBottom = findViewById(R.id.spot_bottom);
        imgSpotStart = findViewById(R.id.spot_start);
        imgSpotEnd = findViewById(R.id.spot_end);
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

    @Override
    public void onSensorChanged(SensorEvent event) {
        float currentValue = event.values[0];
        int sensorType = event.sensor.getType();
        switch (sensorType) {
            case Sensor.TYPE_ACCELEROMETER:
                sensorValueAccelerometer = event.values.clone();
                Log.d("FIKRI-SENSOR-ACCELEROMETER", String.valueOf(currentValue));
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                sensorValueMagnetometer = event.values.clone();
                Log.d("FIKRI-SENSOR-MAGNETOMETER", String.valueOf(currentValue));
                break;
        }
        float[] rotationMatrix = new float[9];
        boolean rotationOK = SensorManager.getRotationMatrix(rotationMatrix, null, sensorValueAccelerometer, sensorValueMagnetometer);

        float[] orientationValues = new float[3];
        if (rotationOK) {
            SensorManager.getOrientation(rotationMatrix, orientationValues);
        }
        float azimuth = orientationValues[0];
        float pitch = orientationValues[1];
        float roll = orientationValues[2];

        tvAzimuth.setText(getString(R.string.value_format, azimuth));
        tvPitch.setText(getString(R.string.value_format, pitch));
        tvRoll.setText(getString(R.string.value_format, roll));

        if (Math.abs(pitch) < VALUE_DRIFT) pitch = 0;
        if (Math.abs(roll) < VALUE_DRIFT) roll = 0;

        imgSpotTop.setAlpha(0f);
        imgSpotBottom.setAlpha(0f);
        imgSpotStart.setAlpha(0f);
        imgSpotEnd.setAlpha(0f);

        if (pitch > 0) {
            imgSpotBottom.setAlpha(pitch);
        } else {
            imgSpotTop.setAlpha(Math.abs(pitch));
        }

        if (roll > 0) {
            imgSpotEnd.setAlpha(roll);
        } else {
            imgSpotStart.setAlpha(Math.abs(roll));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
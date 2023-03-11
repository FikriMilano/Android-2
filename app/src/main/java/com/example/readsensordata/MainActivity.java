package com.example.readsensordata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensorLight;
    private Sensor sensorProximity;
    private TextView tvSensorLight;
    private TextView tvSensorProximity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorProximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        tvSensorLight = findViewById(R.id.label_light);
        tvSensorProximity = findViewById(R.id.label_proximity);
        if (sensorLight == null) tvSensorLight.setText(R.string.no_sensor);
        if (sensorProximity == null) tvSensorProximity.setText(R.string.no_sensor);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (sensorLight == null) return;
        sensorManager.registerListener(this, sensorLight, SensorManager.SENSOR_DELAY_NORMAL);

        if (sensorProximity == null) return;
        sensorManager.registerListener(this, sensorProximity, SensorManager.SENSOR_DELAY_NORMAL);
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
            case Sensor.TYPE_LIGHT:
                tvSensorLight.setText(getString(R.string.label_light, currentValue));
                changeBackgroundColor(currentValue);
                Log.d("FIKRI-SENSOR-LIGHT", String.valueOf(currentValue));
                break;
            case Sensor.TYPE_PROXIMITY:
                tvSensorProximity.setText(getString(R.string.label_proximity, currentValue));
                Log.d("FIKRI-SENSOR-PROXIMITY", String.valueOf(currentValue));
                break;
        }
    }

    private void changeBackgroundColor(float currentValue) {
        if (currentValue == 0) {
            ((LinearLayout) tvSensorLight.getParent()).setBackgroundColor(getColor(R.color.white));
            return;
        }
        if (currentValue <= 20000) {
            ((LinearLayout) tvSensorLight.getParent()).setBackgroundColor(getColor(R.color.purple_200));
            return;
        }
        if (currentValue > 20000) {
            ((LinearLayout) tvSensorLight.getParent()).setBackgroundColor(getColor(R.color.teal_200));
            return;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
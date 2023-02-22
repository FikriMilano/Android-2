package com.example.fragmentexample1updated;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button actionButton;
    private Boolean isFragmentDisplayed = false;
    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionButton = findViewById(R.id.action_button);
        actionButton.setOnClickListener(v -> {
            if (!isFragmentDisplayed) {
                displayFragment();
            } else {
                closeFragment();
            }
        });
    }

    private void displayFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, SimpleFragment.newInstance());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        isFragmentDisplayed = true;
        actionButton.setText(R.string.close);
    }

    private void closeFragment() {
        SimpleFragment simpleFragment = (SimpleFragment) fragmentManager.findFragmentById(R.id.fragment_container);
        if (simpleFragment != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(simpleFragment);
            fragmentTransaction.commit();
            isFragmentDisplayed = false;
            actionButton.setText(R.string.open);
        }
    }
}
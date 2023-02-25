package com.example.pertemuan2tugas;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private FragmentManagerHelper fragmentManagerHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManagerHelper = new FragmentManagerHelper(getSupportFragmentManager(), R.id.fragment_container);

        DescriptionFragment fragment = DescriptionFragment.newInstance(R.string.description_initial);
        fragmentManagerHelper.add(fragment);
    }
}
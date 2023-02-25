package com.example.pertemuan2tugas;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private FragmentManagerHelper fragmentManagerHelper;

    private Button btnAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManagerHelper = new FragmentManagerHelper(getSupportFragmentManager(), R.id.fragment_container);

        btnAction = findViewById(R.id.action_button);
        btnAction.setOnClickListener(this::onActionButtonClicked);

        DescriptionFragment fragment = DescriptionFragment.newInstance(R.string.description_initial);
        fragmentManagerHelper.add(fragment);
    }

    @SuppressLint("NonConstantResourceId")
    private void onActionButtonClicked(View v) {
        DescriptionFragment currentFragment = (DescriptionFragment) fragmentManagerHelper.getCurrentFragment();
        if (currentFragment == null) return;

        int resIdDescription = currentFragment.getResIdDescription();
        if (resIdDescription == R.string.description_initial) {
            DescriptionFragment fragment = DescriptionFragment.newInstance(R.string.description_more);
            fragmentManagerHelper.replace(fragment);
            updateActionButtonText(R.string.text_action_button_state_more);
        } else {
            DescriptionFragment fragment = DescriptionFragment.newInstance(R.string.description_initial);
            fragmentManagerHelper.replace(fragment);
            updateActionButtonText(R.string.text_action_button_state_initial);
        }
    }

    private void updateActionButtonText(@StringRes int resId) {
        btnAction.setText(resId);
    }
}
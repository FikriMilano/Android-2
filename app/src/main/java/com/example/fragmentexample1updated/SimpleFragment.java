package com.example.fragmentexample1updated;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SimpleFragment extends Fragment {

    private TextView simpleHeader;
    private TextView simpleAnswer;
    private RadioGroup simpleRadioGroup;
    private RadioButton simpleRadioButtonYes;
    private RadioButton simpleRadioButtonNo;

    public SimpleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_simple, container, false);
        simpleHeader = rootView.findViewById(R.id.simple_header);
        simpleAnswer = rootView.findViewById(R.id.simple_answer);
        simpleRadioGroup = rootView.findViewById(R.id.simple_radio_group);
        simpleRadioButtonYes = simpleRadioGroup.findViewById(R.id.simple_radio_button_yes);
        simpleRadioButtonNo = simpleRadioGroup.findViewById(R.id.simple_radio_button_no);

        simpleRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.simple_radio_button_yes:
                    simpleAnswer.setText(getResources().getString(R.string.nice));
                    break;

                case R.id.simple_radio_button_no:
                    simpleAnswer.setText(getResources().getString(R.string.lol_no));
                    break;
            }
        });
        return rootView;
    }
}
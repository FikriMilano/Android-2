package com.example.pertemuan2tugas;

import android.os.Bundle;

import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DescriptionFragment extends Fragment {

    private static final String ARG_RES_ID_DESCRIPTION = "res_id_description";

    private @StringRes int resIdDescription;

    private TextView tvDescription;

    public DescriptionFragment() {
        // Required empty public constructor
    }

    public static DescriptionFragment newInstance(@StringRes int description) {
        DescriptionFragment fragment = new DescriptionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_RES_ID_DESCRIPTION, description);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            resIdDescription = getArguments().getInt(ARG_RES_ID_DESCRIPTION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_description, container, false);
        tvDescription = root.findViewById(R.id.description);
        tvDescription.setText(getString(resIdDescription));
        return root;
    }

    public @StringRes int getResIdDescription() {
        return resIdDescription;
    }
}
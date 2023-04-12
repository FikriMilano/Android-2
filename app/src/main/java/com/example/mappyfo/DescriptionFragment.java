package com.example.mappyfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class DescriptionFragment extends Fragment {

    private static final String ARG_STR_ID_TITLE = "str_id_title";
    private static final String ARG_STR_ID_DESCRIPTION = "str_id_description";

    private String title;
    private String description;
    private OnCloseFragmentListener onCloseFragmentListener;
    private ImageView imgActionClose;
    private TextView tvTitle;
    private TextView tvDescription;

    public DescriptionFragment() {
        // Required empty public constructor
    }

    public static DescriptionFragment newInstance(int strIdTitle, int strIdDescription) {
        DescriptionFragment fragment = new DescriptionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STR_ID_TITLE, strIdTitle);
        args.putInt(ARG_STR_ID_DESCRIPTION, strIdDescription);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getString(getArguments().getInt(ARG_STR_ID_TITLE));
            description = getString(getArguments().getInt(ARG_STR_ID_DESCRIPTION));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_description, container, false);
        imgActionClose = root.findViewById(R.id.action_close);
        imgActionClose.setOnClickListener(v -> onCloseFragmentListener.onCloseFragment());
        tvTitle = root.findViewById(R.id.title);
        tvDescription = root.findViewById(R.id.description);
        tvTitle.setText(title);
        tvDescription.setText(description);
        return root;
    }

    public void setOnCloseFragmentListener(OnCloseFragmentListener onCloseFragmentListener) {
        this.onCloseFragmentListener = onCloseFragmentListener;
    }

    interface OnCloseFragmentListener {
        void onCloseFragment();
    }
}
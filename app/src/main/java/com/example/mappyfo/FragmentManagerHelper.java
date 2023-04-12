package com.example.mappyfo;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentManagerHelper {

    private FragmentManager fragmentManager;

    private int containerId;

    public FragmentManagerHelper() {

    }

    public FragmentManagerHelper(FragmentManager fragmentManager, @IdRes int containerId) {
        this.fragmentManager = fragmentManager;
        this.containerId = containerId;
    }

    public Fragment getCurrentFragment() {
        return fragmentManager.findFragmentById(containerId);
    }

    public void add(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(containerId, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void replace(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerId, fragment);
        fragmentTransaction.commit();
    }

    public void remove(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
    }
}

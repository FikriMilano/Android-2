package com.example.customview2;

import android.content.res.Resources;

import androidx.annotation.ColorRes;
import androidx.core.content.res.ResourcesCompat;

public class ResourceHelper {

    private static Resources resources;

    public ResourceHelper(Resources resources) {
        ResourceHelper.resources = resources;
    }

    public static int getColor(@ColorRes int colorId) {
        return ResourcesCompat.getColor(resources, colorId, null);
    }
}

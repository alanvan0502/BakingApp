package com.alanvan.bakingapp.utils;

import android.content.Context;
import android.content.res.Configuration;

import java.util.Objects;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class DeviceConfigUtils {

    public static boolean isDeviceSizeLarge(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static boolean isOrientationLandscape(Context context) {
        return context.getResources().getConfiguration().orientation
                == ORIENTATION_LANDSCAPE;
    }
}

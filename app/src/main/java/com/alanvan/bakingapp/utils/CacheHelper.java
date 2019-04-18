package com.alanvan.bakingapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CacheHelper {
    private static final String PREFS = "baking-app-preferences";
    private static final String PREFS_SYNCED = "baking-app-preferences-synced";

    private final SharedPreferences sharedPreferences;

    @Inject
    public CacheHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public boolean isDataSynced() {
        return sharedPreferences.getBoolean(PREFS_SYNCED, false);
    }

    public void setDataSynced(boolean flag) {
        sharedPreferences.edit().putBoolean(PREFS_SYNCED, flag).apply();
    }
}

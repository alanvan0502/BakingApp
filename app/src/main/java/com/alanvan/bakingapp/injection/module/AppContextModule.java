package com.alanvan.bakingapp.injection.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provide the way to inject classes
 */
@Module
public class AppContextModule {

    Context mAppContext;
    SharedPreferences mSharedPreferences;

    public AppContextModule(Context appContext) {
        mAppContext = appContext;
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(appContext);
    }

    @Provides
    @Singleton
    Context provideAppContext() {
        return mAppContext;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences() {
        return mSharedPreferences;
    }

}

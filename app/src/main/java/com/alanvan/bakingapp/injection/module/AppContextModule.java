package com.alanvan.bakingapp.injection.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.alanvan.bakingapp.utils.CacheHelper;

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
    CacheHelper mCacheHelper;

    public AppContextModule(Context appContext) {
        mAppContext = appContext;
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(appContext);
        mCacheHelper = new CacheHelper(appContext);
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

    @Provides
    @Singleton
    CacheHelper provideCacheHelper() {
        return mCacheHelper;
    }
}

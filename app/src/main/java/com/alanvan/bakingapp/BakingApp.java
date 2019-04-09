package com.alanvan.bakingapp;

import android.app.Application;
import android.content.Context;

import com.alanvan.bakingapp.db.DbHelper;
import com.alanvan.bakingapp.injection.Injector;
import com.alanvan.bakingapp.injection.module.AppServiceModule;
import com.squareup.leakcanary.LeakCanary;

public class BakingApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // LeakCanary initiation
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // We should not init our app in this process.
            return;
        }
        LeakCanary.install(this);

        // Application initiations
        Injector.initApp(this);
        Injector.initModules(new AppServiceModule());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        DbHelper.getInstance(base);
    }
}

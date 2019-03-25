package com.alanvan.bakingapp;

import android.app.Application;

import com.alanvan.bakingapp.injection.Injector;
import com.alanvan.bakingapp.injection.module.AppServiceModule;

public class BakingApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Injector.initApp(this);
        Injector.initModules(new AppServiceModule());
    }
}

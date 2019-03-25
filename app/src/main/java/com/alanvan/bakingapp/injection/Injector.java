package com.alanvan.bakingapp.injection;

import android.app.Application;

import com.alanvan.bakingapp.injection.component.AppComponent;
import com.alanvan.bakingapp.injection.component.ContextComponent;
import com.alanvan.bakingapp.injection.component.DaggerAppComponent;
import com.alanvan.bakingapp.injection.component.DaggerContextComponent;
import com.alanvan.bakingapp.injection.module.AppContextModule;
import com.alanvan.bakingapp.injection.module.AppServiceModule;

public class Injector {

    private static Injector sInstance;
    private AppComponent mAppComponent;
    private ContextComponent mContextComponent;

    public static void initApp(Application application) {
        sInstance = new Injector(application);
    }

    public Injector getInstance() {
        return sInstance;
    }

    public Injector(Application application) {
        mContextComponent = DaggerContextComponent.builder()
                .appContextModule(new AppContextModule(application.getApplicationContext()))
                .build();
    }

    public static void initModules(AppServiceModule appServiceModule) {
        sInstance.mAppComponent = DaggerAppComponent.builder()
                .appServiceModule(appServiceModule).build();
    }

    public static AppComponent getAppComponent() {
        return sInstance.mAppComponent;
    }

    public static ContextComponent getContextComponent() {
        return sInstance.mContextComponent;
    }
}

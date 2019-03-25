package com.alanvan.bakingapp.injection.component;

import com.alanvan.bakingapp.MainFragment;
import com.alanvan.bakingapp.injection.module.AppContextModule;
import com.alanvan.bakingapp.injection.module.AppServiceModule;

import javax.inject.Singleton;
import dagger.Component;

/**
 * Declare all classes to be injected into
 */
@Singleton
@Component(modules = AppServiceModule.class)
public interface AppComponent {

    void inject(MainFragment mainFragment);

}

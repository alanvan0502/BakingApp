package com.alanvan.bakingapp.injection.component;

import com.alanvan.bakingapp.BaseFragment;
import com.alanvan.bakingapp.BaseViewModel;
import com.alanvan.bakingapp.injection.module.AppServiceModule;
import com.alanvan.bakingapp.injection.subcomponent.AppServiceComponent;
import com.alanvan.bakingapp.ui.main.MainViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Declare all classes to be injected into
 */
@Singleton
@Component(modules = AppServiceModule.class)
public interface AppComponent extends AppServiceComponent {

    // Note: need to inject directly to concrete implementation of classes
    void inject(MainViewModel viewModel);

}

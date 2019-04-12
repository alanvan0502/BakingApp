package com.alanvan.bakingapp.injection.component;

import android.content.Context;
import android.content.SharedPreferences;

import com.alanvan.bakingapp.injection.module.AppContextModule;
import com.alanvan.bakingapp.repository.RecipeRepository;
import com.alanvan.bakingapp.utils.CacheHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by vuhoang on 12/21/16.
 */
@Singleton
@Component(modules = {
        AppContextModule.class,
})

public interface ContextComponent {
    Context appContext();

    SharedPreferences sharedPreferences();

    CacheHelper cacheHelper();
}

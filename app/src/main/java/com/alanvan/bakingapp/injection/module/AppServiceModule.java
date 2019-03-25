package com.alanvan.bakingapp.injection.module;

import com.alanvan.bakingapp.injection.RecipeRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provide the way to inject classes
 */
@Module
public class AppServiceModule {
    RecipeRepository mRepository;

    public AppServiceModule() {
        mRepository = RecipeRepository.getInstance();
    }

    @Provides
    @Singleton
    RecipeRepository provideRecipeRepository() {
        return mRepository;
    }
}

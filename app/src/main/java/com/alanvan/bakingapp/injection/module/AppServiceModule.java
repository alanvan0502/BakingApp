package com.alanvan.bakingapp.injection.module;

import com.alanvan.bakingapp.network.endpoints.RecipeEndPoint;
import com.alanvan.bakingapp.network.service.NetworkServiceFactory;
import com.alanvan.bakingapp.repository.RecipeRepository;
import com.alanvan.bakingapp.repository.RepositoryManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provide the way to inject classes
 */
@Module
public class AppServiceModule {
    private RepositoryManager mRepositoryManager;

    public AppServiceModule() {
        mRepositoryManager = RepositoryManager.getInstance();
    }

    @Provides
    @Singleton
    RecipeRepository provideRecipeRepository() {
        return mRepositoryManager.getRecipeRepository();
    }

    @Provides
    @Singleton
    RecipeEndPoint provideNetworkService() {
        return NetworkServiceFactory.createService(RecipeEndPoint.class, RecipeEndPoint.ENDPOINT);
    }

    @Provides
    @Singleton
    RepositoryManager provideRepositoryManager() {
        return RepositoryManager.getInstance();
    }
}

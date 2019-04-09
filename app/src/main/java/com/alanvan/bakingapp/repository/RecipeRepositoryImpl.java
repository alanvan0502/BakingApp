package com.alanvan.bakingapp.repository;

import com.alanvan.bakingapp.datasource.DataSource;
import com.alanvan.bakingapp.datasource.LocalDataSource;
import com.alanvan.bakingapp.datasource.RemoteDataSource;
import com.alanvan.bakingapp.model.Ingredient;
import com.alanvan.bakingapp.model.Recipe;
import com.alanvan.bakingapp.model.Step;
import com.alanvan.bakingapp.utils.CacheHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class RecipeRepositoryImpl implements RecipeRepository {

    private final DataSource remoteDataSource;
    private final DataSource localDataSource;

    @Inject
    CacheHelper cacheHelper;

    @Inject
    public RecipeRepositoryImpl() {
        remoteDataSource = RemoteDataSource.getInstance();
        localDataSource = LocalDataSource.getInstance();
    }

    @Override
    public Observable<List<Recipe>> getRecipes() {
        if (cacheHelper.isDataSynced()) {
            return localDataSource.getRecipes();
        } else {
            return remoteDataSource.getRecipes().doOnNext(recipeList -> {
                localDataSource.saveRecipes(recipeList);
                cacheHelper.setRecipeSynced(true);
            });
        }
    }

    @Override
    public void saveRecipes(List<Recipe> recipeList) {
        localDataSource.saveRecipes(recipeList);
    }

    @Override
    public void markRepoAsSynced() {
        cacheHelper.setRecipeSynced(true);
    }
}

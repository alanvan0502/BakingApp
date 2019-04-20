package com.alanvan.bakingapp.datasource;

import com.alanvan.bakingapp.injection.Injector;
import com.alanvan.bakingapp.model.Recipe;

import java.util.List;

import io.reactivex.Observable;

public class RemoteDataSource implements DataSource {

    private static DataSource sInstance;
    private static final Object LOCK = new Object();

    public synchronized static DataSource getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new RemoteDataSource();
            }
        }
        return sInstance;
    }

    private RemoteDataSource() {
    }

    @Override
    public Observable<List<Recipe>> getRecipes() {
        return Injector.getAppComponent().recipeEndPoint().loadRecipes();
    }

    @Override
    public Observable<Boolean> saveRecipes(List<Recipe> recipeList) {
        // Not implemented
        return Observable.just(true);
    }

    @Override
    public Observable<Boolean> clearLocalData() {
        // Not implemented
        return Observable.just(true);
    }

    @Override
    public Observable<Recipe> getRecipe(int recipeId) {
        return getRecipes().map(recipes -> {
            for (Recipe recipe: recipes) {
                if (recipe.getId() == recipeId) {
                    return recipe;
                }
            }
            return new Recipe();
        });
    }

    @Override
    public Observable<Boolean> saveRecipe(Recipe recipe) {
        // not implemented
        return Observable.just(true);
    }
}

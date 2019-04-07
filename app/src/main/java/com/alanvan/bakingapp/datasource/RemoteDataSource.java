package com.alanvan.bakingapp.datasource;

import com.alanvan.bakingapp.injection.Injector;
import com.alanvan.bakingapp.model.Ingredient;
import com.alanvan.bakingapp.model.Recipe;
import com.alanvan.bakingapp.model.Step;
import com.alanvan.bakingapp.network.endpoints.RecipeEndPoint;

import java.util.List;

import javax.inject.Inject;

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
    public void saveRecipes(List<Recipe> recipeList) {
        //Not implemented
    }
}

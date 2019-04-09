package com.alanvan.bakingapp.datasource;

import com.alanvan.bakingapp.model.Ingredient;
import com.alanvan.bakingapp.model.Recipe;
import com.alanvan.bakingapp.model.Step;

import java.util.List;

import io.reactivex.Observable;

public class LocalDataSource implements DataSource {

    private static DataSource sInstance;
    private static final Object LOCK = new Object();

    public synchronized static DataSource getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new LocalDataSource();
            }
        }
        return sInstance;
    }

    @Override
    public Observable<List<Recipe>> getRecipes() {
        return null;
    }

    private Observable<List<Step>> getRecipeSteps(int recipeId) {
        return null;
    }

    private Observable<List<Ingredient>> getRecipeIngredients(int recipeId) {
        return null;
    }

    @Override
    public void saveRecipes(List<Recipe> recipeList) {

    }
}

package com.alanvan.bakingapp.datasource;

import com.alanvan.bakingapp.db.cache.CacheServiceManager;
import com.alanvan.bakingapp.db.cache.RecipeCacheService;
import com.alanvan.bakingapp.model.Recipe;

import java.util.List;

import io.reactivex.Observable;

public class LocalDataSource implements DataSource {

    private static DataSource sInstance;
    private static final Object LOCK = new Object();
    private static RecipeCacheService recipeCacheService;

    public synchronized static DataSource getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new LocalDataSource();
            }
        }
        return sInstance;
    }

    private LocalDataSource() {
        recipeCacheService = CacheServiceManager.getInstance().getRecipeCacheService();
    }

    @Override
    public Observable<List<Recipe>> getRecipes() {
        return recipeCacheService.getRecipes().map(recipes -> {
            for (Recipe recipe : recipes) {
                recipe.setIngredients(
                        recipeCacheService.getIngredients(recipe.getId()).blockingFirst()
                );
                recipe.setSteps(
                        recipeCacheService.getSteps(recipe.getId()).blockingFirst()
                );
            }
            return recipes;
        });
    }

    @Override
    public Observable<Boolean> saveRecipes(List<Recipe> recipeList) {
        return recipeCacheService.saveRecipes(recipeList);
    }

    @Override
    public Observable<Boolean> clearLocalData() {
        return recipeCacheService.clearLocalData();
    }

    @Override
    public Observable<Recipe> getRecipe(int recipeId) {
        return recipeCacheService.getRecipe(recipeId).map(recipe -> {
            recipe.setIngredients(
                    recipeCacheService.getIngredients(recipeId).blockingFirst()
            );
            recipe.setSteps(
                    recipeCacheService.getSteps(recipeId).blockingFirst()
            );
            return recipe;
        });
    }

    @Override
    public Observable<Boolean> saveRecipe(Recipe recipe) {
        return recipeCacheService.saveRecipe(recipe);
    }
}

package com.alanvan.bakingapp.db.cache;

import com.alanvan.bakingapp.model.Ingredient;
import com.alanvan.bakingapp.model.Recipe;
import com.alanvan.bakingapp.model.Step;

import java.util.List;

import io.reactivex.Observable;

public interface RecipeCacheService {

    Observable<Boolean> saveRecipes(List<Recipe> recipes);

    Observable<List<Recipe>> getRecipes();

    Observable<List<Ingredient>> getIngredients(int recipeId);

    Observable<List<Step>> getSteps(int recipeId);

    Observable<Boolean> clearLocalData();

    Observable<Boolean> saveRecipe(Recipe recipe);

    Observable<Recipe> getRecipe(int recipeId);
}

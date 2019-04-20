package com.alanvan.bakingapp.repository;

import com.alanvan.bakingapp.model.Ingredient;
import com.alanvan.bakingapp.model.Recipe;
import com.alanvan.bakingapp.model.Step;

import java.util.List;

import io.reactivex.Observable;

public interface RecipeRepository {

    Observable<List<Recipe>> getRecipes();

    Observable<Recipe> getRecipe(int recipeId);

    void saveRecipes(List<Recipe> recipeList);

    void markRepoAsSynced();
}

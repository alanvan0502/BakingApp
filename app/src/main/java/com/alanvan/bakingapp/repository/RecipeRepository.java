package com.alanvan.bakingapp.repository;

import com.alanvan.bakingapp.model.Recipe;
import com.alanvan.bakingapp.model.Step;

import java.util.List;

import io.reactivex.Observable;

public interface RecipeRepository {

    Observable<List<Recipe>> getRecipes();

    Observable<Recipe> getRecipe(int recipeId);

    Observable<Step> getStep(int recipeId, int stepId);

    Observable<Integer> getLastStepId(int recipeId);
}

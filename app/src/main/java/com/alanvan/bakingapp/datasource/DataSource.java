package com.alanvan.bakingapp.datasource;

import com.alanvan.bakingapp.model.Ingredient;
import com.alanvan.bakingapp.model.Recipe;
import com.alanvan.bakingapp.model.Step;

import java.util.List;

import io.reactivex.Observable;

public interface DataSource {
    Observable<List<Recipe>> getRecipes();

    Observable<List<Step>> getSteps();

    Observable<List<Ingredient>> getIngredients();

    void saveRecipes(List<Recipe> recipeList);
}

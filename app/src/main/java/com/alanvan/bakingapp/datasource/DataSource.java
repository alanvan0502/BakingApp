package com.alanvan.bakingapp.datasource;

import com.alanvan.bakingapp.model.Recipe;

import java.util.List;

import io.reactivex.Observable;

public interface DataSource {
    Observable<List<Recipe>> getRecipes();

    void saveRecipes(List<Recipe> recipeList);
}

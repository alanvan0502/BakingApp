package com.alanvan.bakingapp.datasource;

import com.alanvan.bakingapp.model.Recipe;

import java.util.List;

import io.reactivex.Observable;

public interface DataSource {

    Observable<List<Recipe>> getRecipes();

    Observable<Boolean> saveRecipes(List<Recipe> recipeList);

    Observable<Boolean> clearLocalData();
}

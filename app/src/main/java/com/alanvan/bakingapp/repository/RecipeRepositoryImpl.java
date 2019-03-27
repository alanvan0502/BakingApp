package com.alanvan.bakingapp.repository;

import com.alanvan.bakingapp.datasource.DataSource;
import com.alanvan.bakingapp.datasource.LocalDataSource;
import com.alanvan.bakingapp.datasource.RemoteDataSource;
import com.alanvan.bakingapp.model.Ingredient;
import com.alanvan.bakingapp.model.Recipe;
import com.alanvan.bakingapp.model.Step;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class RecipeRepositoryImpl implements RecipeRepository {

    private final DataSource remoteDataSource;
    private final DataSource localDataSource;

    @Inject
    public RecipeRepositoryImpl() {
        remoteDataSource = RemoteDataSource.getInstance();
        localDataSource = LocalDataSource.getInstance();
    }

    //TODO: implement
    @Override
    public Observable<List<Recipe>> getRecipes() {
        return Observable.just(new ArrayList<>());
    }

    //TODO: implement
    @Override
    public Observable<List<Step>> getSteps() {
        return Observable.just(new ArrayList<>());
    }

    //TODO: implement
    @Override
    public Observable<List<Ingredient>> getIngredients() {
        return Observable.just(new ArrayList<>());
    }

    @Override
    public void saveRecipes(List<Recipe> recipeList) {

    }
}

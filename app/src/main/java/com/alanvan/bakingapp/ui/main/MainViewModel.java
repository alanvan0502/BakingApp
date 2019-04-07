package com.alanvan.bakingapp.ui.main;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.alanvan.bakingapp.injection.Injector;
import com.alanvan.bakingapp.model.Recipe;
import com.alanvan.bakingapp.repository.RecipeRepository;
import com.alanvan.bakingapp.ui.epoxy.BaseEpoxyModel;
import com.alanvan.bakingapp.ui.epoxy.MainItemEpoxyModel_;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class MainViewModel extends ViewModel {

    private Observable<List<Recipe>> loadDataFromRepository() {
        RecipeRepository recipeRepository = Injector.getAppComponent().repositoryManager().getRecipeRepository();
        return recipeRepository.getRecipes();
    }

    public Observable<List<BaseEpoxyModel>> setupView(MainFragment fragment) {
        Context context = fragment.getContext();

        return loadDataFromRepository().flatMap(recipeList ->
                Observable.fromCallable(() -> {
                    List<BaseEpoxyModel> models = new ArrayList<>();

                    int id = 0;
                    for (Recipe recipe: recipeList) {
                        models.add(new MainItemEpoxyModel_(fragment)
                                .id(MainViewModel.class.getName() + id)
                                .mainRecipeName(recipe.getName())
                                .mainRecipeServings(recipe.getServings().toString()));
                        id += 1;
                    }
                    return models;
                }));
    }
}

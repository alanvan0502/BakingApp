package com.alanvan.bakingapp.ui.main;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModel;

import com.alanvan.bakingapp.injection.Injector;
import com.alanvan.bakingapp.model.Recipe;
import com.alanvan.bakingapp.repository.RecipeRepository;
import com.alanvan.bakingapp.ui.epoxy.BaseEpoxyModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class MainViewModel extends ViewModel {

    public Observable<List<Recipe>> loadDataFromRepository() {
        RecipeRepository recipeRepository = Injector.getAppComponent().repositoryManager().getRecipeRepository();
        return recipeRepository.getRecipes();
    }

    @SuppressLint("CheckResult")
    public void setupView(MainFragment fragment) {
        loadDataFromRepository().flatMap(recipeList ->
                Observable.fromCallable(() -> {
                    List<BaseEpoxyModel> models = new ArrayList<>();

                    recipeList.get(0).getImage();

                    return null;
        }));
    }
}

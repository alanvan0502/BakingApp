package com.alanvan.bakingapp.ui.main;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alanvan.bakingapp.BaseFragment;
import com.alanvan.bakingapp.BaseViewModel;
import com.alanvan.bakingapp.R;
import com.alanvan.bakingapp.injection.Injector;
import com.alanvan.bakingapp.model.Recipe;
import com.alanvan.bakingapp.repository.RecipeRepository;
import com.alanvan.bakingapp.ui.epoxy.BaseEpoxyModel;
import com.alanvan.bakingapp.ui.epoxy.MainItemEpoxyModel_;
import com.alanvan.bakingapp.ui.recipe_detail.RecipeDetailActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

import static com.alanvan.bakingapp.Constants.RECIPE_ID;

public class MainViewModel extends BaseViewModel {

    @Inject
    RecipeRepository recipeRepository;

    private Observable<List<Recipe>> loadDataFromRepository() {
        Injector.getAppComponent().inject(this);
        return recipeRepository.getRecipes();
    }

    @Override
    public Observable<List<BaseEpoxyModel>> setupView(BaseFragment fragment) {
        Context context = fragment.getContext();
        return loadDataFromRepository().flatMap(recipeList ->
                Observable.fromCallable(() -> {
                    List<BaseEpoxyModel> models = new ArrayList<>();

                    for (Recipe recipe: recipeList) {
                        assert context != null;
                        models.add(new MainItemEpoxyModel_(fragment)
                                .id(MainViewModel.class.getName() + recipe.getId())
                                .mainRecipeName(recipe.getName())
                                .mainRecipeServings(context.getString(R.string.servings) + " " + recipe.getServings().toString())
                                .mainItemClick(v -> {
                                    Activity activity = fragment.getActivity();

                                    Intent intent = new Intent(activity, RecipeDetailActivity.class);

                                    intent.putExtra(RECIPE_ID, recipe.getId());

                                    if (activity != null) {
                                        activity.startActivity(intent);
                                    }
                                }));
                    }
                    return models;
                }));
    }
}

package com.alanvan.bakingapp.ui.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

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

import io.reactivex.Observable;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;
import static com.alanvan.bakingapp.Constants.RECIPE_ID;
import static com.alanvan.bakingapp.Constants.RECIPE_NAME;

public class MainViewModel extends BaseViewModel {

    private RecipeRepository recipeRepository
            = Injector.getAppComponent().repositoryManager().getRecipeRepository();

    private Observable<List<Recipe>> loadDataFromRepository() {
        return recipeRepository.getRecipes();
    }

    @Override
    public Observable<List<BaseEpoxyModel>> setupView(BaseFragment fragment) {
        Context context = fragment.getContext();
        return loadDataFromRepository().flatMap(recipeList ->
                Observable.fromCallable(() -> {
                    List<BaseEpoxyModel> models = new ArrayList<>();

                    for (Recipe recipe : recipeList) {
                        if (context != null) {
                            models.add(new MainItemEpoxyModel_(fragment)
                                    .id(MainViewModel.class.getSimpleName() + recipe.getId())
                                    .mainRecipeName(recipe.getName())
                                    .mainRecipeServings(context.getString(R.string.servings) + " " + recipe.getServings().toString())
                                    .mainItemClick(v -> {
                                        Activity activity = fragment.getActivity();

                                        Intent intent = new Intent(activity, RecipeDetailActivity.class);

                                        intent.putExtra(RECIPE_ID, recipe.getId());
                                        intent.putExtra(RECIPE_NAME, recipe.getName());

                                        if (activity != null) {
                                            activity.startActivity(intent);
                                        }
                                    }));
                        }
                    }
                    return models;
                }));
    }
}

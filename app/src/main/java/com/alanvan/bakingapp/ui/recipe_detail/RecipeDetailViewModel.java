package com.alanvan.bakingapp.ui.recipe_detail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.alanvan.bakingapp.BaseFragment;
import com.alanvan.bakingapp.BaseViewModel;
import com.alanvan.bakingapp.R;
import com.alanvan.bakingapp.injection.Injector;
import com.alanvan.bakingapp.model.Ingredient;
import com.alanvan.bakingapp.model.Recipe;
import com.alanvan.bakingapp.model.Step;
import com.alanvan.bakingapp.repository.RecipeRepository;
import com.alanvan.bakingapp.ui.epoxy.BaseEpoxyModel;
import com.alanvan.bakingapp.ui.epoxy.RecipeDetailItemEpoxyModel_;
import com.alanvan.bakingapp.ui.epoxy.RecipeIngredientsEpoxyModel_;
import com.alanvan.bakingapp.ui.step_detail.StepDetailActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static com.alanvan.bakingapp.Constants.RECIPE_ID;
import static com.alanvan.bakingapp.Constants.RECIPE_NAME;
import static com.alanvan.bakingapp.Constants.STEP_ID;

public class RecipeDetailViewModel extends BaseViewModel {

    private Observable<Recipe> loadDataFromRepository(int recipeId) {
        RecipeRepository recipeRepository = Injector.getAppComponent().repositoryManager().getRecipeRepository();
        return recipeRepository.getRecipe(recipeId);
    }

    @Override
    public Observable<List<BaseEpoxyModel>> setupView(BaseFragment fragment) {
        Context context = fragment.getContext();
        return loadDataFromRepository(((RecipeDetailFragment) fragment).getRecipeId())
                .flatMap(recipe ->  Observable.fromCallable(() -> {
                    List<BaseEpoxyModel> models = new ArrayList<>();

                    if (context != null) {
                        models.add(new RecipeIngredientsEpoxyModel_(fragment)
                                .id(RecipeDetailViewModel.class.getSimpleName() + recipe.getId() + "ingredients")
                                .recipeDetailsIngredients(loadIngredients(context, recipe)));

                        List<Step> steps = recipe.getSteps();
                        for (Step step: steps) {
                            models.add(new RecipeDetailItemEpoxyModel_(fragment)
                                    .id(RecipeDetailViewModel.class.getName() + recipe.getId() + step.getId())
                                    .stepShortDescription(step.getShortDescription())
                                    .recipeDetailItemClick(v -> {
                                        Activity activty = fragment.getActivity();

                                        Intent intent = new Intent(activty, StepDetailActivity.class);

                                        intent.putExtra(RECIPE_ID, recipe.getId());
                                        intent.putExtra(STEP_ID, step.getId());
                                        intent.putExtra(RECIPE_NAME, recipe.getName());

                                        if (activty != null) {
                                            activty.startActivity(intent);
                                        }
                                    }));
                        }
                    }
                    return models;
                }));

    }

    private String loadIngredients(Context context, Recipe recipe) {
        List<Ingredient> ingredients = recipe.getIngredients();

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(context.getString(R.string.ingredient_list_header));
        sb.append("\n");

        for (Ingredient ingredient : ingredients) {

            String ingredientString = ingredient.getIngredient();
            String ingredientCap = ingredientString.substring(0, 1).toUpperCase() + ingredientString.substring(1);

            sb.append("\u2022 ")
                    .append(ingredientCap)
                    .append(" (").append(ingredient.getQuantity())
                    .append(" ")
                    .append(ingredient.getMeasure()).append(") ");
            sb.append("\n");
        }

        return sb.toString();
    }
}

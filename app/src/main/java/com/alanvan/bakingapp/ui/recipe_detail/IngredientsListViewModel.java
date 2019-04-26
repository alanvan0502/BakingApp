package com.alanvan.bakingapp.ui.recipe_detail;

import android.content.Context;

import com.alanvan.bakingapp.BaseFragment;
import com.alanvan.bakingapp.BaseViewModel;
import com.alanvan.bakingapp.injection.Injector;
import com.alanvan.bakingapp.model.Recipe;
import com.alanvan.bakingapp.repository.RecipeRepository;
import com.alanvan.bakingapp.ui.epoxy.BaseEpoxyModel;
import com.alanvan.bakingapp.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class IngredientsListViewModel extends BaseViewModel {

    private RecipeRepository recipeRepository
            = Injector.getAppComponent().repositoryManager().getRecipeRepository();

    private int recipeId;

    private Observable<Recipe> loadDataFromRepository(int recipeId) {
        return recipeRepository.getRecipe(recipeId);
    }

    @Override
    public Observable<List<BaseEpoxyModel>> setupView(BaseFragment fragment) {
        // not implemented
        return Observable.just(new ArrayList<>());
    }

    public Observable<String> getRecipe(BaseFragment fragment) {

        Context context = fragment.getContext();

        return Observable.fromCallable(() -> {
            Recipe recipe = loadDataFromRepository(recipeId).blockingFirst();
            if (context != null) {
                return StringUtils.getIngredientListString(context, recipe);
            } else {
                return "";
            }
        });
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }
}

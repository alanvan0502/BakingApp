package com.alanvan.bakingapp.ui.widget;

import android.content.SharedPreferences;
import android.util.Pair;
import android.util.SparseArray;

import com.alanvan.bakingapp.injection.Injector;
import com.alanvan.bakingapp.model.Ingredient;
import com.alanvan.bakingapp.model.Recipe;
import com.alanvan.bakingapp.repository.RecipeRepository;

import java.util.List;

import io.reactivex.Observable;

public class WidgetDataProvider {

    public static final String WIDGET_PREF_ID_KEY = "widget-pref-id-key-";
    public static final String WIDGET_PREF_NAME_KEY = "widget-pref-name-key-";

    private RecipeRepository recipeRepository = Injector.getAppComponent().repositoryManager().getRecipeRepository();

    private SharedPreferences sharedPreferences = Injector.getContextComponent().sharedPreferences();

    public Observable<SparseArray<String>> getRecipeIdsAndNames() {
        return recipeRepository.getRecipes().map(recipes -> {

            SparseArray<String> result = new SparseArray<>();

            for (Recipe recipe: recipes) {
                result.put(recipe.getId(), recipe.getName());
            }

            return result;
        });
    }

    public void deleteRecipeFromPrefs(int appWidgetId) {
        sharedPreferences.edit().remove(WIDGET_PREF_NAME_KEY + appWidgetId).apply();
        sharedPreferences.edit().remove(WIDGET_PREF_ID_KEY + appWidgetId).apply();
    }

    public Observable<List<Ingredient>> getIngredientList(Integer recipeId) {
        return recipeRepository.getRecipe(recipeId).map(Recipe::getIngredients);
    }

    public void saveRecipeIdAndNameToPreferences(int appWidgetId, Integer id, String name) {
        sharedPreferences.edit().putString(WIDGET_PREF_NAME_KEY + appWidgetId, name).apply();
        sharedPreferences.edit().putInt(WIDGET_PREF_ID_KEY + appWidgetId, id).apply();
    }

    public Pair<Integer, String> getRecipeIdAndNameFromPreferences(int appWidgetId) {
        int recipeId =  sharedPreferences.getInt(WIDGET_PREF_ID_KEY + appWidgetId, 0);
        String recipeName = sharedPreferences.getString(WIDGET_PREF_NAME_KEY + appWidgetId, "");
        return new Pair<>(recipeId, recipeName);
    }
}

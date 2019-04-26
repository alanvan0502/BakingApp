package com.alanvan.bakingapp.utils;

import android.content.Context;

import com.alanvan.bakingapp.R;
import com.alanvan.bakingapp.model.Ingredient;
import com.alanvan.bakingapp.model.Recipe;

import java.util.List;

public class StringUtils {

    public static String getIngredientListString(Context context, Recipe recipe) {
        List<Ingredient> ingredients = recipe.getIngredients();

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(context.getString(R.string.ingredient_list_header));
        sb.append("\n");

        for (Ingredient ingredient : ingredients) {

            String ingredientString = ingredient.getIngredient();
            String ingredientCap = ingredientString.substring(0, 1).toUpperCase()
                    + ingredientString.substring(1);

            sb.append("\u2022 ")
                    .append(ingredientCap)
                    .append(" (").append(ingredient.getQuantity())
                    .append(" ")
                    .append(ingredient.getMeasure()).append(") ");
            sb.append("\n");
        }

        return sb.toString();
    }

    public static String formatIngredientLine(Ingredient ingredient) {

        StringBuilder sb = new StringBuilder();
        String ingredientCap = ingredient.getIngredient().substring(0, 1).toUpperCase()
                + ingredient.getIngredient().substring(1);

        sb.append("\u2022 ")
                .append(ingredientCap)
                .append(" (").append(ingredient.getQuantity())
                .append(" ")
                .append(ingredient.getMeasure()).append(") ");

        return sb.toString();
    }
}

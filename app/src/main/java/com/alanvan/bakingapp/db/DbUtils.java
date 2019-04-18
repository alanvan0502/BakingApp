package com.alanvan.bakingapp.db;

import android.database.Cursor;

import com.alanvan.bakingapp.model.Ingredient;
import com.alanvan.bakingapp.model.Recipe;
import com.alanvan.bakingapp.model.Step;

public class DbUtils {

    public static Recipe getRecipeFromCursor(Cursor recipeCursor) {
        Recipe recipe = new Recipe();

        String name = recipeCursor.getString(recipeCursor.getColumnIndex(Recipe.COLUMN_NAME));
        String image = recipeCursor.getString(recipeCursor.getColumnIndex(Recipe.COLUMN_IMAGE));
        Integer id = recipeCursor.getInt(recipeCursor.getColumnIndex(Recipe.COLUMN_RECIPE_ID));
        Integer servings = recipeCursor.getInt(recipeCursor.getColumnIndex(Recipe.COLUMN_SERVINGS));

        recipe.setId(id);
        recipe.setImage(image);
        recipe.setName(name);
        recipe.setServings(servings);

        return recipe;
    }

    public static Ingredient getIngredientFromCursor(Cursor cursor) {
        Ingredient ingredient = new Ingredient();

        ingredient.setIngredient(cursor.getString(cursor.getColumnIndex(Ingredient.COLUMN_INGREDIENT)));

        ingredient.setRecipeId(cursor.getInt(cursor.getColumnIndex(Ingredient.COLUMN_RECIPE_ID)));

        ingredient.setMeasure(cursor.getString(cursor.getColumnIndex(Ingredient.COLUMN_MEASURE)));

        ingredient.setQuantity(cursor.getDouble(cursor.getColumnIndex(Ingredient.COLUMN_QUANTITY)));

        return ingredient;
    }

    public static Step getStepFromCursor(Cursor cursor) {
        Step step = new Step();

        step.setDescription(cursor.getString(cursor.getColumnIndex(Step.COLUMN_DESCRIPTION)));

        step.setId(cursor.getInt(cursor.getColumnIndex(Step.COLUMN_STEP_ID)));

        step.setRecipeId(cursor.getInt(cursor.getColumnIndex(Step.COLUMN_RECIPE_ID)));

        step.setShortDescription(cursor.getString(cursor.getColumnIndex(Step.COLUMN_SHORT_DESC)));

        step.setThumbnailURL(cursor.getString(cursor.getColumnIndex(Step.COLUMN_THUMBNAIL_URL)));

        step.setVideoURL(cursor.getString(cursor.getColumnIndex(Step.COLUMN_THUMBNAIL_URL)));

        return step;
    }
}

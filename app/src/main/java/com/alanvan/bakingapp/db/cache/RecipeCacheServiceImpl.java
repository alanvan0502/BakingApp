package com.alanvan.bakingapp.db.cache;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.alanvan.bakingapp.db.DbUtils;
import com.alanvan.bakingapp.model.Ingredient;
import com.alanvan.bakingapp.model.Recipe;
import com.alanvan.bakingapp.model.Step;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;

public class RecipeCacheServiceImpl extends CacheServiceImpl implements RecipeCacheService {

    private static RecipeCacheServiceImpl sInstance;

    synchronized public static RecipeCacheServiceImpl getInstance() {
        if (sInstance == null) {
            sInstance = new RecipeCacheServiceImpl();
        }
        return sInstance;
    }

    @Override
    public Observable<Boolean> saveRecipes(List<Recipe> recipes) {

        return Observable.fromCallable(() -> {
            this.storeEntities(recipes);

            for (Recipe recipe : recipes) {
                storeStepsAndIngredients(recipe);
            }

            return true;
        });
    }

    @Override
    public Observable<Boolean> saveRecipe(Recipe recipe) {

        return Observable.fromCallable(() -> {
            this.storeEntity(recipe);
            storeStepsAndIngredients(recipe);
            return true;
        });
    }

    private void storeStepsAndIngredients(Recipe recipe) {
        List<Ingredient> ingredientEntities = new ArrayList<>();
        List<Ingredient> ingredients = recipe.getIngredients();
        int count = 0;
        for (Ingredient ingredient : ingredients) {
            ingredient.setRecipeId(recipe.getId());
            ingredient.setIngredientId("ingredient_" + recipe.getId() + "_" + count);
            count++;
            ingredientEntities.add(ingredient);
        }
        this.storeEntities(ingredientEntities);

        List<Step> stepEntities = new ArrayList<>();
        List<Step> steps = recipe.getSteps();
        for (Step step : steps) {
            step.setRecipeId(recipe.getId());
            step.setStepDbId("step_" + step.getId() + "_" + recipe.getId());
            stepEntities.add(step);
        }
        this.storeEntities(stepEntities);
    }

    @Override
    public Observable<List<Recipe>> getRecipes() {

        return Observable.fromCallable(() -> {
            List<Recipe> recipes = new ArrayList<>();

            Cursor cursor = null;
            try {
                String sql = "SELECT * FROM " + Recipe.TABLE_NAME;

                cursor = db.rawQuery(sql, null);
                if (cursor != null) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        Recipe recipe = DbUtils.getRecipeFromCursor(cursor);
                        recipes.add(recipe);
                        cursor.moveToNext();
                    }
                }
            } finally {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
            }
            return recipes;
        });
    }

    @Override
    public Observable<Recipe> getRecipe(int recipeId) {

        return Observable.fromCallable(() -> {
            Cursor cursor = null;
            Recipe recipe = new Recipe();

            String sql = String.format(Locale.US,
                    "SELECT " +
                            "r.* " +
                            "FROM %s r " +
                            "WHERE r.%s = %s",
                    // FROM
                    Recipe.TABLE_NAME,
                    // WHERE
                    Recipe.COLUMN_RECIPE_ID, recipeId);

            try {
                cursor = db.rawQuery(sql, null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    recipe = DbUtils.getRecipeFromCursor(cursor);
                    cursor.moveToNext();
                }
            } finally {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
            }
            return recipe;
        });
    }

    @Override
    public Observable<List<Ingredient>> getIngredients(int recipeId) {

        return Observable.fromCallable(() -> {
            List<Ingredient> ingredients = new ArrayList<>();

            Cursor cursor = null;
            try {
                String sql = String.format(Locale.US,
                        "SELECT * FROM %s st"
                                + " WHERE st.%s = %s",
                        //FROM
                        Ingredient.TABLE_NAME,
                        //WHERE
                        Ingredient.COLUMN_RECIPE_ID, recipeId
                );

                cursor = db.rawQuery(sql, null);
                if (cursor != null) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        Ingredient ingredient = DbUtils.getIngredientFromCursor(cursor);
                        ingredients.add(ingredient);
                        cursor.moveToNext();
                    }
                }
            } finally {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
            }

            return ingredients;
        });

    }

    @Override
    public Observable<List<Step>> getSteps(int recipeId) {

        return Observable.fromCallable(() -> {
            List<Step> steps = new ArrayList<>();

            String sql = String.format(Locale.US,
                    "SELECT * FROM %s st" + " WHERE st.%s = %s",
                    //FROM
                    Step.TABLE_NAME,
                    //WHERE
                    Step.COLUMN_RECIPE_ID, recipeId
            );

            Cursor cursor = null;
            try {
                cursor = db.rawQuery(sql, null);
                if (cursor != null) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        Step step = DbUtils.getStepFromCursor(cursor);
                        steps.add(step);
                        cursor.moveToNext();
                    }
                }
            } finally {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
            }

            return steps;
        });
    }

    @Override
    public Observable<Boolean> clearLocalData() {

        return Observable.fromCallable(() -> {
            List<SQLiteStatement> sqlStatements
                    = getDeleteLocalDataStatement(Arrays.asList(Recipe.TABLE_NAME, Step.TABLE_NAME, Ingredient.TABLE_NAME));

            for (SQLiteStatement statement : sqlStatements) {
                statement.execute();
            }
            return true;
        });
    }

    private List<SQLiteStatement> getDeleteLocalDataStatement(List<String> tableNames) {
        List<SQLiteStatement> result = new ArrayList<SQLiteStatement>();
        for (String tableName : tableNames) {
            String sql = String.format(Locale.US,
                    "DELETE FROM %s", tableName);
            result.add(db.compileStatement(sql));
        }
        return result;
    }


}

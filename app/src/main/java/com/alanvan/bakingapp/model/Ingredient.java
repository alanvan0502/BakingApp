package com.alanvan.bakingapp.model;

import com.alanvan.bakingapp.db.cache.CachableEntity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = Ingredient.TABLE_NAME)
public class Ingredient implements CachableEntity {

    public static final String TABLE_NAME = "Ingredient";
    public static final String INGREDIENT_ID = "ingredient_id";
    public static final String COLUMN_RECIPE_ID = "recipe_id";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_MEASURE = "measure";
    public static final String COLUMN_INGREDIENT = "ingredient";

    @DatabaseField(columnName = INGREDIENT_ID, id = true)
    private String ingredientId;

    @DatabaseField(columnName = COLUMN_RECIPE_ID)
    private int recipeId;

    @DatabaseField(columnName = COLUMN_QUANTITY)
    @SerializedName("quantity")
    @Expose
    private Double quantity;

    @DatabaseField(columnName = COLUMN_MEASURE)
    @SerializedName("measure")
    @Expose
    private String measure;

    @DatabaseField(columnName = COLUMN_INGREDIENT)
    @SerializedName("ingredient")
    @Expose
    private String ingredient;

    public int getRecipeId() {
        return recipeId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(String ingredientId) {
        this.ingredientId = ingredientId;
    }
}

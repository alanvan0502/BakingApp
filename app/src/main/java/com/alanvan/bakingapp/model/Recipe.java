package com.alanvan.bakingapp.model;

import java.util.List;

import com.alanvan.bakingapp.db.cache.CachableEntity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = Recipe.TABLE_NAME)
public class Recipe implements CachableEntity {

    public static final String TABLE_NAME = "Recipe";
    public static final String COLUMN_RECIPE_ID = "recipe_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SERVINGS = "servings";
    public static final String COLUMN_IMAGE = "image";

    @DatabaseField(id = true, columnName = COLUMN_RECIPE_ID)
    @SerializedName("id")
    @Expose
    private Integer id;

    @DatabaseField(columnName = COLUMN_NAME)
    @SerializedName("name")
    @Expose
    private String name;

    @DatabaseField(columnName = COLUMN_SERVINGS)
    @SerializedName("servings")
    @Expose
    private Integer servings;

    @DatabaseField(columnName = COLUMN_IMAGE)
    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("ingredients")
    @Expose
    private List<Ingredient> ingredients = null;

    @SerializedName("steps")
    @Expose
    private List<Step> steps = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

package com.alanvan.bakingapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Ingredient {

    public static final String CUP = "cup";
    public static final String TBLSP = "table-spool";
    public static final String TSP = "tea-spoon";
    public static final String K = "kilogram";
    public static final String G = "gram";
    public static final String OZ = "ounce";

    @SerializedName("quantity")
    @Expose
    private Double quantity;
    @SerializedName("measure")
    @Expose
    private String measure;
    @SerializedName("ingredient")
    @Expose
    private String ingredient;

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

    // Builder
    public abstract static class Builder {
        public abstract Builder quantity(float quantity);
        public abstract Builder measure(String measure);
        public abstract Builder ingredient(String ingredient);

        public abstract Ingredient build();
    }
}

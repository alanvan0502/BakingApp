package com.alanvan.bakingapp;

public interface BasePresenter {

    void subscribe();

    void unsubscribe();

    void loadRecipeTitle();

    void loadIngredients();

    void loadStepDetails();
}

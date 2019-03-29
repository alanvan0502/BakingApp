package com.alanvan.bakingapp.ui.recipe_detail;

import com.alanvan.bakingapp.BasePresenter;
import com.alanvan.bakingapp.BaseView;

public interface RecipeDetailFragmentContract {
    
    interface ViewContract extends BaseView {


    }

    interface PresenterContract extends BasePresenter {

        void loadRecipeTitle();

        void loadIngredients();

        void loadStepDetails();

    }
}

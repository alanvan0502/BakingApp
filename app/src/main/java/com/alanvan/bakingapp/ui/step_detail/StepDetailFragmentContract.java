package com.alanvan.bakingapp.ui.step_detail;

import com.alanvan.bakingapp.BasePresenter;
import com.alanvan.bakingapp.BaseView;

public interface StepDetailFragmentContract {
    
    interface ViewContract extends BaseView {


    }

    interface PresenterContract extends BasePresenter {

        void loadRecipeTitle();

        void loadIngredients();

        void loadStepDetails();

    }
}

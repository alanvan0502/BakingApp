package com.alanvan.bakingapp.ui.epoxy;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelClass;
import com.alanvan.bakingapp.BaseFragment;
import com.alanvan.bakingapp.R;
import com.alanvan.bakingapp.databinding.RecipeDetailItemBinding;
import com.alanvan.bakingapp.databinding.RecipeIngredientsItemBinding;

@EpoxyModelClass(layout = R.layout.recipe_ingredients_item)
public abstract class RecipeIngredientsEpoxyModel extends BaseEpoxyModel {

    @EpoxyAttribute
    public String recipeDetailsIngredients;

    public RecipeIngredientsEpoxyModel(BaseFragment fragment) {
        super(fragment);
    }

    @Override
    protected void setDataBindingVariables(ViewDataBinding binding) {

        RecipeIngredientsItemBinding recipeIngredientsItemBinding = (RecipeIngredientsItemBinding) binding;

        recipeIngredientsItemBinding.recipeDetailsIngredients.setText(recipeDetailsIngredients);
    }
}

package com.alanvan.bakingapp.ui.epoxy;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.OnModelClickListener;
import com.alanvan.bakingapp.BaseFragment;
import com.alanvan.bakingapp.R;
import com.alanvan.bakingapp.databinding.MainItemBinding;
import com.alanvan.bakingapp.databinding.RecipeDetailItemBinding;

@EpoxyModelClass(layout = R.layout.recipe_detail_item)
public abstract class RecipeDetailItemEpoxyModel extends BaseEpoxyModel {

    @EpoxyAttribute
    public View.OnClickListener recipeDetailItemClick;

    @EpoxyAttribute
    public String stepShortDescription;

    public RecipeDetailItemEpoxyModel(BaseFragment fragment) {
        super(fragment);
    }

    @Override
    protected void setDataBindingVariables(ViewDataBinding binding) {

        RecipeDetailItemBinding recipeDetailItemBinding = (RecipeDetailItemBinding) binding;

        recipeDetailItemBinding.stepShortDescription.setText(stepShortDescription);
        recipeDetailItemBinding.setRecipeDetailItemClick(recipeDetailItemClick);
    }
}

package com.alanvan.bakingapp.ui.epoxy;

import android.databinding.ViewDataBinding;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelClass;
import com.alanvan.bakingapp.BaseFragment;
import com.alanvan.bakingapp.R;
import com.alanvan.bakingapp.databinding.MainItemBinding;

@EpoxyModelClass(layout = R.layout.main_item)
public abstract class MainItemEpoxyModel extends BaseEpoxyModel {

    @EpoxyAttribute
    public int mainRecipeImage;

    @EpoxyAttribute
    public String mainRecipeName;

    @EpoxyAttribute
    public String mainRecipeServings;

    public MainItemEpoxyModel(BaseFragment fragment) {
        super(fragment);
    }

    @Override
    protected void setDataBindingVariables(ViewDataBinding binding) {

        MainItemBinding mainItemBinding = (MainItemBinding) binding;

        mainItemBinding.mainRecipeImage.setImageResource(mainRecipeImage);
        mainItemBinding.mainRecipeName.setText(mainRecipeName);
        mainItemBinding.mainRecipeServings.setText(mainRecipeServings);
    }
}

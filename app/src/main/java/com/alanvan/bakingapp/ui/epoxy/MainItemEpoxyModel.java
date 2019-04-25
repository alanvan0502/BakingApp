package com.alanvan.bakingapp.ui.epoxy;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelClass;
import com.alanvan.bakingapp.BaseFragment;
import com.alanvan.bakingapp.R;
import com.alanvan.bakingapp.databinding.MainItemBinding;

@EpoxyModelClass(layout = R.layout.main_item)
public abstract class MainItemEpoxyModel extends BaseEpoxyModel {

    @EpoxyAttribute
    public View.OnClickListener mainItemClick;

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

        mainItemBinding.mainRecipeName.setText(mainRecipeName);
        mainItemBinding.mainRecipeServings.setText(mainRecipeServings);
        mainItemBinding.setMainItemClick(mainItemClick);
    }
}

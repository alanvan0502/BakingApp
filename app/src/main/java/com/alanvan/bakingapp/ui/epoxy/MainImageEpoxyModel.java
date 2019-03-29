package com.alanvan.bakingapp.ui.epoxy;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelClass;
import com.alanvan.bakingapp.BaseFragment;
import com.alanvan.bakingapp.R;
import com.alanvan.bakingapp.databinding.MainItemBinding;
import com.bumptech.glide.Glide;

import android.databinding.ViewDataBinding;

@EpoxyModelClass(layout = R.layout.main_item)
public abstract class MainImageEpoxyModel extends BaseEpoxyModel {

    MainImageEpoxyModel(BaseFragment fragment) {
        super(fragment);
    }

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotUseInToString)
    String imageSource = null;

    @Override
    protected void setDataBindingVariables(ViewDataBinding viewBinding) {
        MainItemBinding binding = (MainItemBinding) viewBinding;
        Glide.with(binding.mainRecipeImage.getContext()).load(imageSource).into(binding.mainRecipeImage);
    }
}

package com.alanvan.bakingapp.ui.epoxy;

import com.airbnb.epoxy.DataBindingEpoxyModel;
import com.alanvan.bakingapp.BaseFragment;

public abstract class BaseEpoxyModel extends DataBindingEpoxyModel {

    protected BaseFragment fragment;

    public BaseEpoxyModel(BaseFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public boolean shouldSaveViewState() {
        return true;
    }

}

package com.alanvan.bakingapp.ui.epoxy;

import com.airbnb.epoxy.DataBindingEpoxyModel;
import com.alanvan.bakingapp.BaseFragment;

public abstract class BaseEpoxyModel extends DataBindingEpoxyModel {

    protected BaseFragment fragment;

    BaseEpoxyModel(BaseFragment fragment) {
        this.fragment = fragment;
    }

}

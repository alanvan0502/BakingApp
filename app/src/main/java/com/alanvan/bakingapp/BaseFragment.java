package com.alanvan.bakingapp;

import com.alanvan.bakingapp.ui.epoxy.EpoxyController;

public abstract class BaseFragment extends RxFragment {

    public abstract EpoxyController getController();
}

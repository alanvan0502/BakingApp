package com.alanvan.bakingapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alanvan.bakingapp.ui.epoxy.BaseEpoxyModel;
import com.alanvan.bakingapp.ui.epoxy.EpoxyController;
import com.alanvan.bakingapp.utils.RxUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseFragment extends RxFragment {

    private BaseViewModel viewModel;

    private List<BaseEpoxyModel> models = new ArrayList<>();

    private EpoxyController controller = new EpoxyController();

    @SuppressLint("CheckResult")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.setupView(this).compose(
                RxUtils.applyIOSchedulers()
        ).compose(
                bindToLifecycle()
        ).subscribe(result -> {
            models.addAll(result);
            controller.setModels(models);
            controller.requestModelBuild();
        });
    }

    public void setViewModel(BaseViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public EpoxyController getController() {
        return controller;
    }
}

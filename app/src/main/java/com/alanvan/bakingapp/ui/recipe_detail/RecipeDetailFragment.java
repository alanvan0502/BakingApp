package com.alanvan.bakingapp.ui.recipe_detail;


import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alanvan.bakingapp.BaseFragment;
import com.alanvan.bakingapp.R;
import com.alanvan.bakingapp.databinding.FragmentRecipeDetailBinding;
import com.alanvan.bakingapp.ui.epoxy.EpoxyController;
import com.alanvan.bakingapp.ui.epoxy.RecipeDetailEpoxyController;
import com.alanvan.bakingapp.utils.RxUtils;
import com.orhanobut.logger.Logger;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailFragment extends BaseFragment {

    private int recipeId;

    private int stepId = 0;

    private RecipeDetailEpoxyController controller = new RecipeDetailEpoxyController();

    private RecipeDetailViewModel viewModel;

    private CompositeDisposable bag = new CompositeDisposable();

    public RecipeDetailFragment() {
        // Required empty public constructor
    }

    public static RecipeDetailFragment getInstance() {
        return new RecipeDetailFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecipeDetailActivity activity = (RecipeDetailActivity) getActivity();
        viewModel = ViewModelProviders.of(activity).get(RecipeDetailViewModel.class);
        controller.setViewModel(viewModel);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentRecipeDetailBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_recipe_detail, container, false);
        binding.recyclerView.setControllerAndBuildModels(this.controller);

        return binding.getRoot();
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public EpoxyController getController() {
        return controller;
    }

    @SuppressLint("CheckResult")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Disposable d = viewModel.buildEpoxyView(this).compose(
                RxUtils.applyIOSchedulers()
        ).compose(
                bindToLifecycle()
        ).subscribe(result -> {
            viewModel.getSelectedStepIdLiveData().setValue(stepId);
            Logger.d("Successfully created view");
        }, error -> Logger.d("Failed to create view"));

        bag.add(d);
    }

    public void setStepId(int stepId) {
        this.stepId = stepId;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bag != null && bag.isDisposed()) {
            bag.dispose();
        }
    }
}

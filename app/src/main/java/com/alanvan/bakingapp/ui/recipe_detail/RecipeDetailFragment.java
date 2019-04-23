package com.alanvan.bakingapp.ui.recipe_detail;


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

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailFragment extends BaseFragment {

    private int recipeId;

    private boolean isTwoPane = false;

    public RecipeDetailFragment() {
        // Required empty public constructor
    }

    public static RecipeDetailFragment getInstance() {
        return new RecipeDetailFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecipeDetailViewModel viewModel = ViewModelProviders.of(this).get(RecipeDetailViewModel.class);
        this.setViewModel(viewModel);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentRecipeDetailBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_recipe_detail, container, false);
        binding.recyclerView.setControllerAndBuildModels(this.getController());

        return binding.getRoot();
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }
}

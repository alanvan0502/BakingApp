package com.alanvan.bakingapp.ui.recipe_detail;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;

import com.alanvan.bakingapp.BaseActivity;
import com.alanvan.bakingapp.R;
import com.alanvan.bakingapp.ui.step_detail.StepDetailFragment;
import com.alanvan.bakingapp.ui.step_detail.StepDetailViewModel;

import static com.alanvan.bakingapp.Constants.RECIPE_ID;
import static com.alanvan.bakingapp.Constants.RECIPE_NAME;

public class RecipeDetailActivity extends BaseActivity {

    private boolean isTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        RecipeDetailFragment fragment = RecipeDetailFragment.getInstance();

        Intent intent = getIntent();
        if (intent != null) {
            fragment.setRecipeId(intent.getIntExtra(RECIPE_ID, -1));

            String recipeName = intent.getStringExtra(RECIPE_NAME);
            if (!recipeName.equals("")) {
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle(recipeName);
                }
            }

            if (findViewById(R.id.fragment_step_detail) != null) {
                isTwoPane = true;
            }

            if (isTwoPane) {

                StepDetailViewModel viewModel = ViewModelProviders.of(this).get(StepDetailViewModel.class);

                if (viewModel.getRecipeId() == -1) {
                    viewModel.setRecipeId(intent.getIntExtra(RECIPE_ID, -1));
                }

                if (viewModel.getStepId() == -1) {
                    viewModel.setStepId(0);
                }

                StepDetailFragment stepDetailFragment = StepDetailFragment.getInstance();
                stepDetailFragment.setTwoPane(isTwoPane);

                replaceFragment(stepDetailFragment, R.id.fragment_step_detail, StepDetailFragment.class.getName());
            }
        }

        replaceFragment(fragment, R.id.fragment_recipe_detail, RecipeDetailFragment.class.getName());
    }

    public boolean isTwoPane() {
        return isTwoPane;
    }
}

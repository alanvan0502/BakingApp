package com.alanvan.bakingapp.ui.step_detail;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.alanvan.bakingapp.BaseActivity;
import com.alanvan.bakingapp.R;

import static com.alanvan.bakingapp.Constants.RECIPE_ID;
import static com.alanvan.bakingapp.Constants.RECIPE_NAME;
import static com.alanvan.bakingapp.Constants.STEP_ID;

public class StepDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        StepDetailFragment fragment = StepDetailFragment.getInstance();

        Intent intent = getIntent();
        if (intent != null) {

            StepDetailViewModel viewModel = ViewModelProviders.of(this).get(StepDetailViewModel.class);

            if (viewModel.getRecipeId() == -1) {
                viewModel.setRecipeId(intent.getIntExtra(RECIPE_ID, -1));
            }

            if (viewModel.getStepId() == -1) {
                viewModel.setStepId(intent.getIntExtra(STEP_ID, -1));
            }

            String recipeName = intent.getStringExtra(RECIPE_NAME);
            if (!recipeName.equals("")) {
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle(recipeName);
                }
            }
        }

        replaceFragment(fragment, R.id.fragment_step_detail, StepDetailFragment.class.getName());
    }
}

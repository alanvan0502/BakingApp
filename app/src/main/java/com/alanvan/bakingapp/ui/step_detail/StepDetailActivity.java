package com.alanvan.bakingapp.ui.step_detail;

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

    private int stepId = 0;
    private int recipeId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        StepDetailFragment fragment = StepDetailFragment.getInstance();

        Intent intent = getIntent();
        if (intent != null) {

            stepId = intent.getIntExtra(STEP_ID, 0);
            recipeId = intent.getIntExtra(RECIPE_ID, 0);

            fragment.setStepId(stepId);
            fragment.setRecipeId(recipeId);

            String recipeName = intent.getStringExtra(RECIPE_NAME);
            if (!recipeName.equals("")) {
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle(recipeName);
                }
            }
        }

        if (savedInstanceState != null) {
            stepId = savedInstanceState.getInt(STEP_ID, 0);
            recipeId = savedInstanceState.getInt(RECIPE_ID, 0);

            fragment.setStepId(stepId);
            fragment.setRecipeId(recipeId);
        }

        replaceFragment(fragment, R.id.fragment_step_detail, StepDetailFragment.class.getName());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STEP_ID, stepId);
        outState.putInt(RECIPE_ID, recipeId);
    }
}

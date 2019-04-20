package com.alanvan.bakingapp.ui.step_detail;

import android.content.Intent;
import android.os.Bundle;

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
            fragment.setStepId(intent.getIntExtra(STEP_ID, -1));
            fragment.setRecipeId(intent.getIntExtra(RECIPE_ID, -1));

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

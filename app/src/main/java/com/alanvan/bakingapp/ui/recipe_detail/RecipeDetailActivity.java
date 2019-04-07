package com.alanvan.bakingapp.ui.recipe_detail;

import android.content.Intent;
import android.os.Bundle;

import com.alanvan.bakingapp.BaseActivity;
import com.alanvan.bakingapp.R;

import static com.alanvan.bakingapp.Constants.RECIPE_ID;

public class RecipeDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        RecipeDetailFragment fragment = RecipeDetailFragment.getInstance();

        Intent intent = getIntent();
        if (intent != null) {
            fragment.setRecipeId(intent.getIntExtra(RECIPE_ID, -1));
        }

        replaceFragment(fragment, R.id.fragment_recipe_detail, RecipeDetailFragment.class.getName());
    }
}

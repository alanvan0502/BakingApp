package com.alanvan.bakingapp.ui.recipe_detail;

import android.os.Bundle;

import com.alanvan.bakingapp.BaseActivity;
import com.alanvan.bakingapp.R;

public class RecipeDetail extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        RecipeDetailFragment fragment = RecipeDetailFragment.getInstance();
        replaceFragment(fragment, R.id.fragment_recipe_detail, RecipeDetailFragment.class.getName());
    }
}

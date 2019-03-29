package com.alanvan.bakingapp.ui.recipe_detail;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alanvan.bakingapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailFragment extends Fragment implements RecipeDetailFragmentContract.ViewContract {


    public RecipeDetailFragment() {
        // Required empty public constructor
    }

    public static RecipeDetailFragment getInstance() {
        return new RecipeDetailFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public Object getPresenter() {
        return null;
    }
}

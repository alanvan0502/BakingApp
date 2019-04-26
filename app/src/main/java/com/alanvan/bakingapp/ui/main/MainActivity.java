package com.alanvan.bakingapp.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;

import com.alanvan.bakingapp.BaseActivity;
import com.alanvan.bakingapp.R;
import com.alanvan.bakingapp.ui.idling_resource.MainIdlingResource;

public class MainActivity extends BaseActivity {

    private MainIdlingResource idlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        MainFragment mainFragment = MainFragment.getInstance();
        mainFragment.setIdlingResource(idlingResource);
        replaceFragment(mainFragment, R.id.fragment_main, MainFragment.class.getName());
    }

    @VisibleForTesting
    @Nullable
    public IdlingResource getIdlingResource() {
        if (idlingResource == null) {
            idlingResource = new MainIdlingResource();
        }
        return idlingResource;
    }
}

package com.alanvan.bakingapp.ui.main;

import android.os.Bundle;

import com.alanvan.bakingapp.BaseActivity;
import com.alanvan.bakingapp.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        MainFragment mainFragment = MainFragment.getInstance();
        replaceFragment(mainFragment, R.id.fragment_main, MainFragment.class.getName());
    }
}

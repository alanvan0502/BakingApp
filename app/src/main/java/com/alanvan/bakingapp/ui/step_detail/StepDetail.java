package com.alanvan.bakingapp.ui.step_detail;

import android.os.Bundle;

import com.alanvan.bakingapp.BaseActivity;
import com.alanvan.bakingapp.R;

public class StepDetail extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        StepDetailFragment fragment = StepDetailFragment.getInstance();
        replaceFragment(fragment, R.id.fragment_step_detail, StepDetailFragment.class.getName());
    }
}

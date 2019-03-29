package com.alanvan.bakingapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addFragment(Fragment fragment, int containerId, boolean addToBackStack, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(containerId, fragment, tag);
        if (addToBackStack) ft.addToBackStack(tag);
        ft.commit();
    }

    public void replaceFragment(Fragment fragment, int containerId, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(containerId, fragment, tag);
        ft.commit();
    }
}

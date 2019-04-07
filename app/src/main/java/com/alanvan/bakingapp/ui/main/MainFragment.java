package com.alanvan.bakingapp.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alanvan.bakingapp.BaseFragment;
import com.alanvan.bakingapp.R;
import com.alanvan.bakingapp.databinding.FragmentMainBinding;

public class MainFragment extends BaseFragment {

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment getInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setViewModel(ViewModelProviders.of(this).get(MainViewModel.class));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentMainBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        binding.recyclerView.setControllerAndBuildModels(this.getController());
        return binding.getRoot();
    }
}

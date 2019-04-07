package com.alanvan.bakingapp.ui.main;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alanvan.bakingapp.BaseFragment;
import com.alanvan.bakingapp.EpoxyController;
import com.alanvan.bakingapp.R;
import com.alanvan.bakingapp.databinding.FragmentMainBinding;
import com.alanvan.bakingapp.ui.epoxy.BaseEpoxyModel;
import com.alanvan.bakingapp.utils.RxUtils;
import com.trello.rxlifecycle3.android.RxLifecycleAndroid;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.BehaviorSubject;

public class MainFragment extends BaseFragment {

    private MainViewModel viewModel;

    private FragmentMainBinding binding;

    private MainEpoxyController controller = new MainEpoxyController();

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment getInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        binding.recyclerView.setControllerAndBuildModels(controller);
        return binding.getRoot();
    }

    @SuppressLint("CheckResult")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<BaseEpoxyModel> models = new ArrayList<>();

        viewModel.setupView(this).compose(
                RxUtils.applyIOSchedulers()
        ).compose(
                bindToLifecycle()
        ).subscribe(result -> {
            models.addAll(result);
            controller.setModels(models);
            controller.requestModelBuild();
        }, error -> {

        });
    }
}

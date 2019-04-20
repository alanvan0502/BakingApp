package com.alanvan.bakingapp.ui.step_detail;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alanvan.bakingapp.R;
import com.alanvan.bakingapp.RxFragment;
import com.alanvan.bakingapp.databinding.FragmentStepDetailBinding;
import com.alanvan.bakingapp.utils.RxUtils;
import com.google.android.exoplayer2.ui.PlayerView;
import com.orhanobut.logger.Logger;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class StepDetailFragment extends RxFragment {

    private int stepId;

    private int recipeId;

    private StepDetailViewModel viewModel;

    private PlayerView playerView;

    private CompositeDisposable bag = new CompositeDisposable();

    public StepDetailFragment() {
        // Required empty public constructor
    }

    public static StepDetailFragment getInstance() {
        return new StepDetailFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = ViewModelProviders.of(this)
                .get(StepDetailViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentStepDetailBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_step_detail, container, false);
        binding.setStepDetailViewModel(viewModel);
        this.playerView = binding.playerView;
//        playerView.getLayoutParams().height;
//        playerView.getLayoutParams().width;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Disposable d = viewModel.loadVideoUri(recipeId, stepId)
                .compose(RxUtils.applyIOSchedulers())
                .subscribe(
                        uri -> {
                            viewModel.initializeMediaSession();
                            viewModel.initializePlayer(uri, playerView);
                            Logger.d("Success created video player");
                        }, error -> Logger.d("Failed to create video player"));
        bag.add(d);
    }

    @Override
    public void onPause() {
        super.onPause();
        viewModel.releasePlayer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bag.clear();
    }

    public void setStepId(int stepId) {
        this.stepId = stepId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }
}

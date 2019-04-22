package com.alanvan.bakingapp.ui.step_detail;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alanvan.bakingapp.R;
import com.alanvan.bakingapp.RxFragment;
import com.alanvan.bakingapp.databinding.FragmentStepDetailBinding;
import com.alanvan.bakingapp.utils.RxUtils;
import com.google.android.exoplayer2.ui.PlayerView;
import com.orhanobut.logger.Logger;

import java.util.Objects;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;
import static com.alanvan.bakingapp.Constants.RECIPE_ID;
import static com.alanvan.bakingapp.Constants.STEP_ID;

public class StepDetailFragment extends RxFragment {

    private int stepId;

    private int recipeId;

    private StepDetailViewModel viewModel;

    private PlayerView playerView;
    private TextView stepDescription;
    private ImageButton nextStep;
    private ImageButton previousStep;
    private LinearLayout container;

    private int lastStepId = 0;

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
        this.viewModel = ViewModelProviders.of(Objects.requireNonNull(this.getActivity()))
                .get(StepDetailViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentStepDetailBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_step_detail, container, false);
        binding.setStepDetailViewModel(viewModel);
        this.playerView = binding.playerView;
        this.stepDescription = binding.stepDescription;
        this.container = binding.container;

        this.nextStep = binding.nextStep;
        this.previousStep = binding.previousStep;

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int orientation = ORIENTATION_PORTRAIT;
        if (this.getActivity() != null) {
            orientation = this.getActivity().getResources().getConfiguration().orientation;
        }

        if (orientation == ORIENTATION_PORTRAIT) {
            loadVideo(recipeId, stepId);

            loadStepInstruction(recipeId, stepId);

            Disposable d = viewModel.getLastStepId(recipeId)
                    .compose(bindToLifecycle())
                    .compose(RxUtils.applyIOSchedulers())
                    .subscribe(lastStepId -> {
                        this.lastStepId = lastStepId;
                        if (stepId < lastStepId) {
                            this.nextStep.setVisibility(View.VISIBLE);
                        } else {
                            this.nextStep.setVisibility(View.GONE);
                        }

                        if (stepId > 0) {
                            this.previousStep.setVisibility(View.VISIBLE);
                        } else {
                            this.previousStep.setVisibility(View.GONE);
                        }
                    });

            bag.add(d);

        } else {
            setFullScreenMode();
            loadVideo(recipeId, stepId);
        }
    }

    private void setFullScreenMode() {

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) playerView.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.setMargins(0, 0, 0, 0);

        playerView.setLayoutParams(params);

        StepDetailActivity activity = (StepDetailActivity) getActivity();
        if (activity != null) {
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().hide();
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();

        this.previousStep.setOnClickListener(c -> {

            if (stepId > 0) {
                StepDetailFragment fragment = StepDetailFragment.getInstance();
                fragment.stepId = stepId - 1;
                fragment.recipeId = recipeId;
                StepDetailActivity activity = (StepDetailActivity) this.getActivity();
                if (activity != null) {
                    activity.replaceFragment(fragment, R.id.fragment_step_detail, StepDetailFragment.class.getName());
                }
            }

        });

        this.nextStep.setOnClickListener(c -> {

            if (stepId < lastStepId) {
                StepDetailFragment fragment = StepDetailFragment.getInstance();
                fragment.stepId = stepId + 1;
                fragment.recipeId = recipeId;
                StepDetailActivity activity = (StepDetailActivity) this.getActivity();
                if (activity != null) {
                    activity.replaceFragment(fragment, R.id.fragment_step_detail, StepDetailFragment.class.getName());
                }
            }
        });
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

    private void loadStepInstruction(int recipeId, int stepId) {
        Disposable d = viewModel.loadStepInstruction(recipeId, stepId)
                .compose(RxUtils.applyIOSchedulers())
                .compose(bindToLifecycle())
                .subscribe(
                        description -> {
                            stepDescription.setText(description);
                        }, error -> {
                            // not handled
                        }
                );
        bag.add(d);
    }

    private void loadVideo(int recipeId, int stepId) {
        Disposable d = viewModel.loadVideoUri(recipeId, stepId)
                .compose(RxUtils.applyIOSchedulers())
                .compose(bindToLifecycle())
                .subscribe(
                        uri -> {
                            viewModel.initializeMediaSession();
                            viewModel.initializePlayer(uri, playerView);
                            Logger.d("Success created video player");
                        }, error -> Logger.d("Failed to create video player"));
        bag.add(d);
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

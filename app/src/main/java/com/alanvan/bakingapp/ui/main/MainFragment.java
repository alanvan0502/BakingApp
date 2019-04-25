package com.alanvan.bakingapp.ui.main;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alanvan.bakingapp.BaseFragment;
import com.alanvan.bakingapp.BaseViewModel;
import com.alanvan.bakingapp.R;
import com.alanvan.bakingapp.databinding.FragmentMainBinding;
import com.alanvan.bakingapp.ui.epoxy.BaseEpoxyModel;
import com.alanvan.bakingapp.ui.epoxy.EpoxyController;
import com.alanvan.bakingapp.utils.DeviceConfigUtils;
import com.alanvan.bakingapp.utils.RxUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.alanvan.bakingapp.Constants.MAIN_LIST_COLUMN_WIDTH_DP;

public class MainFragment extends BaseFragment {

    private BaseViewModel viewModel;

    private List<BaseEpoxyModel> models = new ArrayList<>();

    private EpoxyController controller = new EpoxyController();

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

        if (DeviceConfigUtils.isDeviceSizeLarge(Objects.requireNonNull(this.getContext()))
                || DeviceConfigUtils.isOrientationLandscape(this.getContext())) {
            int columnCount = calculateNoColumns(Objects.requireNonNull(this.getContext()));
            binding.recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), columnCount));
        }

        binding.recyclerView.setControllerAndBuildModels(this.getController());

        return binding.getRoot();
    }

    @SuppressLint("CheckResult")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.setupView(this).compose(
                RxUtils.applyIOSchedulers()
        ).compose(
                bindToLifecycle()
        ).subscribe(result -> {
            models.addAll(result);
            controller.setModels(models);
            controller.requestModelBuild();
        });
    }

    public EpoxyController getController() {
        return controller;
    }

    public void setViewModel(BaseViewModel viewModel) {
        this.viewModel = viewModel;
    }

    private int calculateNoColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
        return (int) (screenWidthDp / MAIN_LIST_COLUMN_WIDTH_DP + 0.5);
    }
}

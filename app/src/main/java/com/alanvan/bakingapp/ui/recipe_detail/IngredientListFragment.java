package com.alanvan.bakingapp.ui.recipe_detail;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alanvan.bakingapp.BaseFragment;
import com.alanvan.bakingapp.R;
import com.alanvan.bakingapp.RxFragment;
import com.alanvan.bakingapp.databinding.FragmentIngredientListBinding;
import com.alanvan.bakingapp.databinding.FragmentStepDetailBinding;
import com.alanvan.bakingapp.ui.epoxy.EpoxyController;
import com.alanvan.bakingapp.ui.step_detail.StepDetailActivity;
import com.alanvan.bakingapp.ui.step_detail.StepDetailViewModel;
import com.alanvan.bakingapp.utils.RxUtils;
import com.google.android.exoplayer2.ui.PlayerView;
import com.orhanobut.logger.Logger;

import java.util.Objects;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class IngredientListFragment extends BaseFragment {

    private IngredientsListViewModel viewModel;

    private CompositeDisposable bag = new CompositeDisposable();

    private FragmentIngredientListBinding binding;

    public IngredientListFragment() {
        // Required empty public constructor
    }

    public static IngredientListFragment getInstance() {
        return new IngredientListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = ViewModelProviders.of(Objects.requireNonNull(this.getActivity()))
                .get(IngredientsListViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ingredient_list, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Disposable d = viewModel.getRecipe(this)
                .compose(RxUtils.applyIOSchedulers())
                .compose(bindToLifecycle())
                .subscribe(result -> binding.ingredientList.setText(result));
        bag.add(d);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bag != null && !bag.isDisposed()) {
            bag.dispose();
        }
    }

    @Override
    public EpoxyController getController() {
        // not implemented
        return null;
    }
}

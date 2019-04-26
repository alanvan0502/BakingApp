package com.alanvan.bakingapp.ui.recipe_detail;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;

import com.alanvan.bakingapp.BaseFragment;
import com.alanvan.bakingapp.BaseViewModel;
import com.alanvan.bakingapp.R;
import com.alanvan.bakingapp.injection.Injector;
import com.alanvan.bakingapp.model.Recipe;
import com.alanvan.bakingapp.model.Step;
import com.alanvan.bakingapp.repository.RecipeRepository;
import com.alanvan.bakingapp.ui.epoxy.BaseEpoxyModel;
import com.alanvan.bakingapp.ui.epoxy.RecipeDetailEpoxyController;
import com.alanvan.bakingapp.ui.epoxy.RecipeDetailItemEpoxyModel_;
import com.alanvan.bakingapp.ui.epoxy.RecipeIngredientsEpoxyModel_;
import com.alanvan.bakingapp.ui.step_detail.StepDetailActivity;
import com.alanvan.bakingapp.ui.step_detail.StepDetailFragment;
import com.alanvan.bakingapp.ui.step_detail.StepDetailViewModel;
import com.alanvan.bakingapp.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static com.alanvan.bakingapp.Constants.RECIPE_ID;
import static com.alanvan.bakingapp.Constants.RECIPE_NAME;
import static com.alanvan.bakingapp.Constants.STEP_ID;

public class RecipeDetailViewModel extends BaseViewModel {

    private MutableLiveData<Integer> selectedStepIdLiveData = new MutableLiveData<>();

    private Observable<Recipe> loadDataFromRepository(int recipeId) {
        RecipeRepository recipeRepository = Injector.getAppComponent().repositoryManager().getRecipeRepository();
        return recipeRepository.getRecipe(recipeId);
    }

    public MutableLiveData<Integer> getSelectedStepIdLiveData() {
        return this.selectedStepIdLiveData;
    }

    public Observable<Boolean> buildEpoxyView(BaseFragment fragment) {

        Context context = fragment.getContext();

        return Observable.fromCallable(() -> {
            Recipe recipe = loadDataFromRepository(((RecipeDetailFragment) fragment).getRecipeId()).blockingFirst();

            // must change stepIdLiveData after calling buildEpoxyView
            selectedStepIdLiveData.observe(fragment, integer -> {

                List<BaseEpoxyModel> models = new ArrayList<>();

                if (context != null) {
                    RecipeDetailActivity activity = (RecipeDetailActivity) fragment.getActivity();
                    RecipeDetailEpoxyController controller = (RecipeDetailEpoxyController) fragment.getController();

                    if (activity != null) {
                        controller.setTwoPane(activity.isTwoPane());

                        // add list of ingredients if activity is not 2 panes
                        if (!activity.isTwoPane()) {
                            models.add(new RecipeIngredientsEpoxyModel_(fragment)
                                    .id(RecipeDetailViewModel.class.getSimpleName() + recipe.getId() + "ingredients")
                                    .recipeDetailsIngredients(loadIngredients(context, recipe)));
                        }

                        // steps
                        List<Step> steps = recipe.getSteps();
                        for (Step step : steps) {

                            StepDetailFragment stepDetailFragment = StepDetailFragment.getInstance();
                            stepDetailFragment.setTwoPane(activity.isTwoPane());

                            RecipeDetailItemEpoxyModel_ stepHolder = new RecipeDetailItemEpoxyModel_(fragment)
                                    .id(step.getId())
                                    .stepShortDescription(step.getShortDescription())
                                    .recipeDetailItemClick(v -> {

                                        if (!activity.isTwoPane()) {
                                            // start step detail activity
                                            Intent intent = new Intent(activity, StepDetailActivity.class);

                                            intent.putExtra(RECIPE_ID, recipe.getId());
                                            intent.putExtra(STEP_ID, step.getId());
                                            intent.putExtra(RECIPE_NAME, recipe.getName());

                                            activity.startActivity(intent);
                                        } else {
                                            // add step detail activity fragment
                                            StepDetailViewModel viewModel
                                                    = ViewModelProviders.of(activity).get(StepDetailViewModel.class);
                                            viewModel.setStepId(step.getId());
                                            activity.replaceFragment(stepDetailFragment,
                                                    R.id.fragment_step_detail, StepDetailFragment.class.getName());

                                            selectedStepIdLiveData.postValue(step.getId());
                                        }
                                    });
                            models.add(stepHolder);
                        }
                    }

                    controller.setModels(models);
                    controller.requestModelBuild();
                }
            });

            // add ingredients if 2-pane
            RecipeDetailActivity activity = (RecipeDetailActivity) fragment.getActivity();

            if (activity != null && activity.isTwoPane()) {

                IngredientListFragment ingredientListFragment = IngredientListFragment.getInstance();

                IngredientsListViewModel viewModel = ViewModelProviders.of(activity).get(IngredientsListViewModel.class);
                viewModel.setRecipeId(recipe.getId());

                activity.replaceFragment(ingredientListFragment, R.id.fragment_ingredient_list,
                        IngredientListFragment.class.getName());
            }

            return true;
        });


    }

    @Override
    public Observable<List<BaseEpoxyModel>> setupView(BaseFragment fragment) {
        // not implemented
        return Observable.just(new ArrayList<>());
    }

    private String loadIngredients(Context context, Recipe recipe) {
        return StringUtils.getIngredientListString(context, recipe);
    }
}

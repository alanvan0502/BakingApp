package com.alanvan.bakingapp.ui.epoxy;

import com.alanvan.bakingapp.ui.recipe_detail.RecipeDetailViewModel;

import java.util.ArrayList;
import java.util.List;

public class RecipeDetailEpoxyController extends EpoxyController {

    private List<BaseEpoxyModel> models = new ArrayList<>();
    private RecipeDetailViewModel viewModel;
    private boolean isTwoPane;

    @Override
    protected void buildModels() {
        if (isTwoPane) {
            for (int i = 0; i < models.size(); i++) {
                if (models.get(i) instanceof RecipeDetailItemEpoxyModel_) {
                    RecipeDetailItemEpoxyModel_ model = (RecipeDetailItemEpoxyModel_) models.get(i);
                    if (viewModel.getSelectedStepIdLiveData().getValue() != null
                            && model.id() == viewModel.getSelectedStepIdLiveData().getValue()) {
                        model.isSelected(true);
                    } else {
                        model.isSelected(false);
                    }
                    model.addTo(this);
                } else {
                    models.get(i).addTo(this);
                }
            }
        } else {
            for (int i = 0; i < models.size(); i++) {
                models.get(i).addTo(this);
            }
        }

    }

    public void setTwoPane(boolean twoPane) {
        isTwoPane = twoPane;
    }

    public void setModels(List<BaseEpoxyModel> models) {
        this.models = models;
    }

    public void setViewModel(RecipeDetailViewModel viewModel) {
        this.viewModel = viewModel;
    }
}

package com.alanvan.bakingapp.ui.epoxy;

import com.alanvan.bakingapp.ui.recipe_detail.RecipeDetailViewModel;

import java.util.ArrayList;
import java.util.List;

public class RecipeDetailEpoxyController extends EpoxyController {

    private List<BaseEpoxyModel> models = new ArrayList<>();
    private RecipeDetailViewModel viewModel;

    @Override
    protected void buildModels() {
        for (int i = 0; i < models.size(); i++) {
            RecipeDetailItemEpoxyModel_ model = (RecipeDetailItemEpoxyModel_) models.get(i);
            if (viewModel.getSelectedStepIdLiveData().getValue() != null
                    && model.id() == viewModel.getSelectedStepIdLiveData().getValue()) {
                model.isSelected(true);
            } else {
                model.isSelected(false);
            }
            model.addTo(this);
        }
    }

    public void setModels(List<BaseEpoxyModel> models) {
        this.models = models;
    }

    public void setViewModel(RecipeDetailViewModel viewModel) {
        this.viewModel = viewModel;
    }
}

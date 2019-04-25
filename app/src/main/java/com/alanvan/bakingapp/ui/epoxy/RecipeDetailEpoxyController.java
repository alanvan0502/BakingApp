package com.alanvan.bakingapp.ui.epoxy;

import java.util.ArrayList;
import java.util.List;

public class RecipeDetailEpoxyController extends EpoxyController {

    private List<BaseEpoxyModel> models = new ArrayList<>();
    private int stepId = 0;

    @Override
    protected void buildModels() {
        for (int i = 0; i < models.size(); i++) {
            RecipeDetailItemEpoxyModel_ model = (RecipeDetailItemEpoxyModel_) models.get(i);
            if (model.id() == stepId) {
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

    public void setStepId(int stepId) {
        this.stepId = stepId;
    }
}

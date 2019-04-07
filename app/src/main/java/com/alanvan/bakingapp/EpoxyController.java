package com.alanvan.bakingapp;

import com.airbnb.epoxy.AsyncEpoxyController;
import com.alanvan.bakingapp.ui.epoxy.BaseEpoxyModel;

import java.util.ArrayList;
import java.util.List;

public abstract class EpoxyController extends AsyncEpoxyController {

    private List<BaseEpoxyModel> models = new ArrayList<>();

    @Override
    protected void buildModels() {
        for (BaseEpoxyModel model: models) {
                model.addTo(this);
        }
    }

    public void setModels(List<BaseEpoxyModel> models) {
        this.models = models;
    }
}

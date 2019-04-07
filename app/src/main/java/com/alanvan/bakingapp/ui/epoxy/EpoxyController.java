package com.alanvan.bakingapp.ui.epoxy;

import com.airbnb.epoxy.AsyncEpoxyController;

import java.util.ArrayList;
import java.util.List;

public class EpoxyController extends AsyncEpoxyController {

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

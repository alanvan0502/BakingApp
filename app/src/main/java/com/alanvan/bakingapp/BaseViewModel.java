package com.alanvan.bakingapp;

import android.arch.lifecycle.ViewModel;

import com.alanvan.bakingapp.ui.epoxy.BaseEpoxyModel;

import java.util.List;

import io.reactivex.Observable;

public abstract class BaseViewModel extends ViewModel {

    public abstract Observable<List<BaseEpoxyModel>> setupView(BaseFragment fragment);

}

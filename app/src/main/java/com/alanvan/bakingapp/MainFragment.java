package com.alanvan.bakingapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alanvan.bakingapp.datasource.RemoteDataSource;
import com.alanvan.bakingapp.utils.RxUtils;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class MainFragment extends Fragment {

    public static MainFragment newInstance() {
        return new MainFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RemoteDataSource.getInstance().getRecipes().compose(RxUtils.applyIOSchedulers())
                .subscribe(recipeList -> Log.d("Recipe List", recipeList.toString()), throwable -> {

                });

    }

    private Boolean connectedToInternet(Context ctx) {
        ConnectivityManager conMgr =
                (ConnectivityManager) ctx.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = conMgr.getActiveNetworkInfo();
        return info.isConnected();
    }

}

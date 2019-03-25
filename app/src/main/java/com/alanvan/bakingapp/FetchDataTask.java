package com.alanvan.bakingapp;

import android.os.AsyncTask;

import com.alanvan.bakingapp.model.Recipe;
import com.alanvan.bakingapp.network.JsonUtils;
import com.alanvan.bakingapp.network.NetworkUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FetchDataTask extends AsyncTask<String, Void, String> {
    List<Recipe> recipeList = new ArrayList<>();
    @Override
    protected String doInBackground(String... strings) {
        String jsonString = "";
        try {
            jsonString = NetworkUtils.getRecipes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    @Override
    protected void onPostExecute(String s) {
        recipeList = JsonUtils.getRecipeList(s);
    }
}

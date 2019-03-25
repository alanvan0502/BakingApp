package com.alanvan.bakingapp.network;

import com.alanvan.bakingapp.model.Ingredient;
import com.alanvan.bakingapp.model.Recipe;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class JsonUtils {

    private static Gson gson = new Gson();

    public static List<Recipe> getRecipeList(String jsonString) {
        Type listType = new TypeToken<List<Recipe>>() {}.getType();
        return gson.fromJson(jsonString, listType);
    }
}

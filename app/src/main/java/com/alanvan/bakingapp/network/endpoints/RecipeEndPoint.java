package com.alanvan.bakingapp.network.endpoints;

import com.alanvan.bakingapp.model.Recipe;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RecipeEndPoint {
    String ENDPOINT = "https://d17h27t6h515a5.cloudfront.net";

    @GET("/topher/2017/May/59121517_baking/baking.json")
    Observable<List<Recipe>> loadRecipes();
}

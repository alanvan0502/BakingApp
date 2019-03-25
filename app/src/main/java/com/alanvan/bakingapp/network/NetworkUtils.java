package com.alanvan.bakingapp.network;

import android.view.textclassifier.TextLinks;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkUtils {

    public static String getRecipes() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json").build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}

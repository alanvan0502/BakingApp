package com.alanvan.bakingapp.network.service;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkServiceFactory {

    private static NetworkServiceFactory sInstance;
    private static final Object LOCK = new Object();

    private Retrofit.Builder builder;

    public synchronized static NetworkServiceFactory getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new NetworkServiceFactory();
            }
        }
        return sInstance;
    }

    private NetworkServiceFactory() {

    }

    public static <T> T createService(Class<T> serviceClass, String endpoint) {

        OkHttpClient client = new OkHttpClient.Builder().build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(endpoint)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();

        return retrofit.create(serviceClass);
    }
}

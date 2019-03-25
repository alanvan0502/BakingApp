package com.alanvan.bakingapp.injection;

public class RecipeRepository {

    private static RecipeRepository sInstance;
    private static final Object LOCK = new Object();

    private RecipeRepository() {
    }

    public synchronized static RecipeRepository getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new RecipeRepository();
            }
        }
        return sInstance;
    }

}

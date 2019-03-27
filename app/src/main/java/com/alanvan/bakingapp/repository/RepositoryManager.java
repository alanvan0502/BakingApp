package com.alanvan.bakingapp.repository;

public class RepositoryManager {

    private static final Object LOCK = new Object();

    volatile private static RepositoryManager sInstance;

    public static RepositoryManager getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = new RepositoryManager();
                }
            }
        }
        return sInstance;
    }

    private RecipeRepository mRecipeRepository;

    private RepositoryManager() {
        mRecipeRepository = new RecipeRepositoryImpl();
    }

    public RecipeRepository getRecipeRepository() {
        return mRecipeRepository;
    }
}

package com.alanvan.bakingapp.db.cache;

public class CacheServiceManager {

    private static final Object LOCK = new Object();
    private RecipeCacheService mRecipeCacheService;

    volatile private static CacheServiceManager sInstance;

    public static CacheServiceManager getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = new CacheServiceManager();
                }
            }
        }
        return sInstance;
    }

    private CacheServiceManager() {
        mRecipeCacheService = new RecipeCacheServiceImpl();
    }

    public RecipeCacheService getRecipeCacheService() {
        return mRecipeCacheService;
    }
}

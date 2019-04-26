package com.alanvan.bakingapp.ui.idling_resource;

import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainIdlingResource implements IdlingResource {

    private volatile ResourceCallback callback;

    private AtomicBoolean isIdling = new AtomicBoolean(true);

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public boolean isIdleNow() {
        return isIdling.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.callback = callback;
    }

    public void setIsIdling(boolean isIdling) {
        this.isIdling.set(isIdling);
        if (isIdling && callback != null) {
            callback.onTransitionToIdle();
        }
    }
}

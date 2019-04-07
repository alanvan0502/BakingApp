package com.alanvan.bakingapp.ui.epoxy;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.airbnb.epoxy.EpoxyRecyclerView;

public class BakingAppRecyclerView extends EpoxyRecyclerView {

    Runnable computeScrollRunnable;

    public BakingAppRecyclerView(Context context) {
        super(context);
    }

    public BakingAppRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BakingAppRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setComputeScrollRunnable(Runnable computeScrollRunnable) {
        this.computeScrollRunnable = computeScrollRunnable;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (computeScrollRunnable != null) {
            computeScrollRunnable.run();
        }
    }
}

package com.alanvan.bakingapp.utils;

import io.reactivex.ObservableTransformer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class RxUtils {
    private static final ObservableTransformer backgroundSchedulersTransformer =
            o -> o.subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io());

    private static final ObservableTransformer ioSchedulersTransformer =
            o -> o.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());

    private static final ObservableTransformer computationSchedulersTransformer =
            o -> o.subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread());

    @SuppressWarnings("unchecked")
    public static <T> ObservableTransformer<T, T> applyIOSchedulers() {
        return (ObservableTransformer<T, T>) ioSchedulersTransformer;
    }

    @SuppressWarnings("unchecked")
    public static <T> ObservableTransformer<T, T> applyComputationSchedulers() {
        return (ObservableTransformer<T, T>) computationSchedulersTransformer;
    }

    @SuppressWarnings("unchecked")
    public static <T> ObservableTransformer<T, T> applyBackgroundSchedulers() {
        return (ObservableTransformer<T, T>) backgroundSchedulersTransformer;
    }
}

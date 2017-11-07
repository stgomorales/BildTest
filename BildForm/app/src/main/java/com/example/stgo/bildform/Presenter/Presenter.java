package com.example.stgo.bildform.Presenter;

/**
 * Created by shagos on 06-11-17.
 */

public interface Presenter<V> {
    void attachView(V view);
    void detachView();
    void onResume() throws Exception;
}

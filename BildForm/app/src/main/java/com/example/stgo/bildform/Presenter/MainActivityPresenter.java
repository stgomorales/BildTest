package com.example.stgo.bildform.Presenter;

import android.content.Context;

import com.example.stgo.bildform.Activity.MainActivity;
import com.example.stgo.bildform.Interactor.MainActivityInteractor;
import com.example.stgo.bildform.View.MainActivityView;

/**
 * Created by shagos on 05-11-17.
 */

public class MainActivityPresenter implements Presenter<MainActivityView> {

    private Context context;
    private MainActivityView mainActivityView;
    private MainActivityInteractor mainActivityInteractor;

    public MainActivityPresenter(Context context) {
        this.context = context;
        mainActivityInteractor = new MainActivityInteractor(this.context);
    }

    public void makeDefaultParameter(){
        mainActivityInteractor.makeDefaultData();
    }

    public String getInitFragment(){
        return mainActivityInteractor.getInitFragment();
    }
    @Override
    public void attachView(MainActivityView view) {
        if (view == null)
            throw new IllegalArgumentException("view is Null");
        mainActivityView = view;
    }

    @Override
    public void detachView() {
        mainActivityView = null;
    }

    @Override
    public void onResume() throws Exception {

    }
}

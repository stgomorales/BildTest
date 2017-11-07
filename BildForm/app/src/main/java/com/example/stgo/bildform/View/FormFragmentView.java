package com.example.stgo.bildform.View;

import android.view.View;

import java.util.ArrayList;

/**
 * Created by shagos on 05-11-17.
 */

public interface FormFragmentView {
    void showProgress();
    void hideProgress();
    void showMessage(String title , String message);
    void goToFragment(String fragmentName);
    void showForm(ArrayList<View> views);
    void showFromTitle(String title);
}

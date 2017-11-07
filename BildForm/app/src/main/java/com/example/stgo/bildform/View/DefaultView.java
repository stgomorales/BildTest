package com.example.stgo.bildform.View;

/**
 * Created by shagos on 05-11-17.
 */

public interface DefaultView {
    void showProgress();
    void hideProgress();
    void showMessage(String title , String message);
    void goToFragment(String fragmentName);
}

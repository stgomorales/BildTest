package com.example.stgo.bildform.Presenter;

import android.content.Context;

import com.example.stgo.bildform.Interactor.SettingFragmentInteractor;
import com.example.stgo.bildform.R;
import com.example.stgo.bildform.View.DefaultView;

/**
 * Created by shagos on 06-11-17.
 */

public class SettingFragmentPresenter implements Presenter<DefaultView>{
    private Context context;
    private DefaultView defaultView;
    private SettingFragmentInteractor settingFragmentInteractor;

    public SettingFragmentPresenter(Context context) {
        this.context = context;
        settingFragmentInteractor = new SettingFragmentInteractor(this.context);
    }

    public void saveSettingData(String ip, String port){
        if (settingFragmentInteractor.setConnectionParameter(ip, port)){
            defaultView.showMessage(context.getString(R.string.settings_message_title),
                    context.getString(R.string.settings_success));
            defaultView.goToFragment("Login");
        }else{
            defaultView.showMessage(context.getString(R.string.settings_message_title),
                    context.getString(R.string.settings_error));
        }
    }
    @Override
    public void attachView(DefaultView view) {
        if (view == null)
            throw new IllegalArgumentException("view is Null");
        defaultView = view;
    }

    @Override
    public void detachView() {
        defaultView = null;
    }

    @Override
    public void onResume() throws Exception {

    }
}

package com.example.stgo.bildform.Presenter;

import android.content.Context;

import com.example.stgo.bildform.APIS.ConnectCallback;
import com.example.stgo.bildform.Database.TokenDbAdapter;
import com.example.stgo.bildform.Interactor.LoginFragmentInteractor;
import com.example.stgo.bildform.Model.Token;
import com.example.stgo.bildform.R;
import com.example.stgo.bildform.View.DefaultView;

import org.json.JSONObject;

/**
 * Created by shagos on 05-11-17.
 */

public class LoginFragmentPresenter implements Presenter<DefaultView>{
    private Context context;
    private DefaultView defaultView;
    private LoginFragmentInteractor loginFragmentInteractor;

    public LoginFragmentPresenter(Context context) {
        this.context = context;
        loginFragmentInteractor = new LoginFragmentInteractor(this.context);
    }

    public void getToken(String user, String password){
        loginFragmentInteractor.getLoginToken(user, password, new ConnectCallback() {
            @Override
            public void onResponse(Object result) {
                saveToken((JSONObject) result);
                defaultView.showMessage(context.getString(R.string.token_tittle),
                        context.getString(R.string.token_success));
                defaultView.goToFragment("Form");
            }
            @Override
            public void onError(Object error) {
                defaultView.showMessage(context.getString(R.string.token_tittle),
                        context.getString(R.string.token_error));
            }
        });
    }

    private void saveToken(JSONObject jsonToken){
        TokenDbAdapter tdB = TokenDbAdapter.getInstance(context);
        Token t = new Token();
        t.setToken(jsonToken.toString());
        tdB.addToken(t);

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

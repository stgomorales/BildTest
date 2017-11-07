package com.example.stgo.bildform.Interactor;

import android.content.Context;

import com.example.stgo.bildform.APIS.AppParameters;
import com.example.stgo.bildform.APIS.Connection;
import com.example.stgo.bildform.APIS.ConnectCallback;
import com.example.stgo.bildform.Model.Parameter;

/**
 * Created by shagos on 05-11-17.
 */

public class LoginFragmentInteractor {
    private Context context;

    public LoginFragmentInteractor(Context context) {
        this.context = context;
    }

    public void getLoginToken(String user, String password, ConnectCallback callback){
        AppParameters appParameters = AppParameters.getInstance(context);

        String url = appParameters.getParametroWithDefault(Parameter.CONECCTION_IP, "").getValue();
        String port = appParameters.getParametroWithDefault(Parameter.CONNECTION_PORT, "").getValue();
        String service = appParameters.getParametroWithDefault(Parameter.TOKEN_SERVICE, "").getValue();
        Connection connection = new Connection(url, port, service, Connection.TIMEOUT, Connection.RETRY, context);
        connection.getAccessToken(user, password, callback);
    }
}

package com.example.stgo.bildform.Interactor;

import android.content.Context;

import com.example.stgo.bildform.APIS.AppParameters;
import com.example.stgo.bildform.APIS.Connection;
import com.example.stgo.bildform.APIS.ConnectCallback;
import com.example.stgo.bildform.Database.FormDbAdapter;
import com.example.stgo.bildform.Database.TokenDbAdapter;
import com.example.stgo.bildform.Model.Form;
import com.example.stgo.bildform.Model.Parameter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by shagos on 05-11-17.
 */

public class FormFragmentInteractor {
    private Context context;

    public FormFragmentInteractor(Context context) {
        this.context = context;
    }

    public boolean existFrom(){
        FormDbAdapter fdb = FormDbAdapter.getInstance(context);
        ArrayList<JSONObject> jForm = new ArrayList<>();
        jForm = fdb.getAllFrom();
        if (jForm.size()> 0)
            return true;
        else
            return false;
    }

    public Boolean saveFromJson(JSONObject form){
        FormDbAdapter fdb  = FormDbAdapter.getInstance(context);
        Form f = new Form(form.toString());
        if (fdb.addFormJson(f) > 0)
            return true;
        else return false;
    }

    public Form getForm(){
        FormDbAdapter fdb = FormDbAdapter.getInstance(context);
        return fdb.getLastFrom();
    }
    public long deleteTokens(){
        TokenDbAdapter tdb = TokenDbAdapter.getInstance(context);
        return tdb.deleteAllToken();
    }
    public void getForms(ConnectCallback callback){
        AppParameters appParameters = AppParameters.getInstance(context);

        String url = appParameters.getParametroWithDefault(Parameter.CONECCTION_IP, "").getValue();
        String port = appParameters.getParametroWithDefault(Parameter.CONNECTION_PORT, "").getValue();
        String service = appParameters.getParametroWithDefault(Parameter.FORM_SERVICE, "").getValue();
        Connection connection = new Connection(url, port, service, Connection.TIMEOUT, 1, context);
        TokenDbAdapter tdb = TokenDbAdapter.getInstance(context);
        JSONObject token = tdb.getLastToken();
        JSONObject bearer = new JSONObject();
        try {
            bearer.put("Authorization", "Bearer " + token.getString("access_token"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        connection.getForms(bearer, callback);
    }
}

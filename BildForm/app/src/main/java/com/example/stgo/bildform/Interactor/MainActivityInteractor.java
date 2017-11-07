package com.example.stgo.bildform.Interactor;

import android.content.Context;
import com.example.stgo.bildform.Database.ParameterDbAdapter;
import com.example.stgo.bildform.Database.TokenDbAdapter;
import com.example.stgo.bildform.Model.Parameter;

import org.json.JSONObject;

/**
 * Created by shagos on 05-11-17.
 */

public class MainActivityInteractor {
    private Context context;

    public MainActivityInteractor(Context context) {
        this.context = context;
    }

    public void makeDefaultData(){
        ParameterDbAdapter pdB = ParameterDbAdapter.getInstance(context);
        pdB.makeDefaultParameters();
    }

    public String getInitFragment(){
        String fragmentName = "";
        TokenDbAdapter tdb = TokenDbAdapter.getInstance(context);
        ParameterDbAdapter pdb  = ParameterDbAdapter.getInstance(context);
        JSONObject jToken = tdb.getLastToken();
        String ip = pdb.getParametroWithDefault(Parameter.CONECCTION_IP, "").getValue();
        if (ip.equals(""))
            return "Setting";
        if (jToken.isNull("access_token"))
            return "Login";
        else
            return "Form";
    }
}

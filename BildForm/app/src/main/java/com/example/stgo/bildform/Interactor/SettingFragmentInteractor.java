package com.example.stgo.bildform.Interactor;

import android.content.Context;

import com.example.stgo.bildform.Database.ParameterDbAdapter;
import com.example.stgo.bildform.Model.Parameter;

import java.util.ArrayList;

import static com.example.stgo.bildform.Model.Parameter.CONECCTION_IP;
import static com.example.stgo.bildform.Model.Parameter.CONNECTION_PORT;

/**
 * Created by shagos on 06-11-17.
 */

public class SettingFragmentInteractor {
    private Context context;
    public SettingFragmentInteractor(Context context) {
        this.context = context;
    }

    public boolean setConnectionParameter(String ip, String port){
        ParameterDbAdapter pdb = ParameterDbAdapter.getInstance(context);
        ArrayList<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter(CONECCTION_IP, ip));
        parameters.add(new Parameter(CONNECTION_PORT, port));
        if (pdb.addParameters(parameters) > 0)
            return true;
        else return false;
    }
}

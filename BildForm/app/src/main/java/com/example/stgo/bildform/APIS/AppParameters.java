package com.example.stgo.bildform.APIS;

import android.content.Context;

import com.example.stgo.bildform.Database.ParameterDbAdapter;
import com.example.stgo.bildform.Model.Parameter;

/**
 * Created by shagos on 05-11-17.
 */

public class AppParameters {
    private static final String TAG = "TerminalParameters";
    private ParameterDbAdapter parametersDbAdapter;
    private Context context;
    private static AppParameters instance = null;

    /**
     * Singleton implementation;
     */

    public static AppParameters getInstance(Context context){
        if (instance == null)
            instance = new AppParameters(context);
        return instance;
    }

    private AppParameters(Context context) {
        this.context = context;
        this.parametersDbAdapter = ParameterDbAdapter.getInstance(context);
    }

    public Parameter getParametroWithDefault(String id, String defaultValue){
        return parametersDbAdapter.getParametroWithDefault(id, defaultValue);
    }
}

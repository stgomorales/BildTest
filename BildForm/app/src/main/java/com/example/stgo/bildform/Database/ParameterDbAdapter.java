package com.example.stgo.bildform.Database;

import android.content.Context;
import android.util.Log;

import com.example.stgo.bildform.Model.Parameter;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;

import static com.example.stgo.bildform.Model.Parameter.FORM_SERVICE;
import static com.example.stgo.bildform.Model.Parameter.TOKEN_SERVICE;

/**
 * Created by shagos on 05-11-17.
 */

public class ParameterDbAdapter {
    private DbHelper mDBHelper;
    private Context context;
    private Dao parameterDao;
    private static String TAG = "PARAMETERSDB";

    private static ParameterDbAdapter instance = null;

    /**
     * Singleton implementation;
     */

    public static ParameterDbAdapter getInstance(Context context){
        if (instance == null)
            instance = new ParameterDbAdapter(context);
        return instance;
    }

    private ParameterDbAdapter(Context context) {
        this.context = context;
        this.mDBHelper = getHelper();
    }

    private DbHelper getHelper(){
        if (mDBHelper == null)
            mDBHelper = OpenHelperManager.getHelper(context, DbHelper.class);
        return mDBHelper;
    }

    private void closeDb(){
        if (mDBHelper != null){
            OpenHelperManager.releaseHelper();
            mDBHelper = null;
        }
    }

    private Dao getParameterDao() throws SQLException {
        if (parameterDao == null)
            parameterDao = mDBHelper.getParametersDao();
        return parameterDao;
    }

    public long addParameter(Parameter p){
        try {
            Dao dao = getParameterDao();
            return dao.createOrUpdate(p).getNumLinesChanged();
        }catch (SQLException e){
            Log.d(TAG, "addParameter: " + e.getMessage());
            return -1;
        }
    }

    public int addParameters(ArrayList<Parameter> parameters){
        Dao dao;
        int i = 0;
        try {
            dao = getParameterDao();
            for (Parameter p: parameters) {
                i = i +dao.createOrUpdate(p).getNumLinesChanged();
            }
        } catch (SQLException e) {
            Log.d(TAG, "addParameters: " + e.getMessage());
            return -1;
        }
        return i;
    }

    public Parameter getParametroWithDefault(String parameterId, String defaultValue){
        Parameter parameter;
        try {
            Dao dao = getParameterDao();
            QueryBuilder queryBuilder = dao.queryBuilder();
            queryBuilder.setWhere(queryBuilder.where().eq(Parameter.COLUMN_PARAMETER_ID, parameterId));
            parameter = (Parameter) dao.queryForFirst(queryBuilder.prepare());
            if (parameter == null)
                parameter = new Parameter(parameterId, defaultValue);
        }catch (SQLException e){
            Log.d(TAG, "getParametroWithDefault: " + e.getMessage());
            parameter = new Parameter(parameterId,defaultValue);
        }catch (Exception e){
            Log.d(TAG, "getParametroWithDefault: " + e.getMessage());
            parameter = new Parameter(parameterId,defaultValue);
        }
        return parameter;
    }

    public void makeDefaultParameters(){
        ArrayList<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter(FORM_SERVICE, "/getforms"));
        parameters.add(new Parameter(TOKEN_SERVICE, "/oauth/token"));
        addParameters(parameters);
    }

}

package com.example.stgo.bildform.Database;

import android.content.Context;
import android.util.Log;

import com.example.stgo.bildform.Model.Form;
import com.example.stgo.bildform.Model.Token;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by shagos on 05-11-17.
 */

public class FormDbAdapter {
    private DbHelper mDBHelper;
    private Context context;
    private Dao formDao;
    private static String TAG = "FORMDB";


    private static FormDbAdapter instance = null;

    /**
     * Singleton implementation;
     */

    public static FormDbAdapter getInstance(Context context){
        if (instance == null)
            instance = new FormDbAdapter(context);
        return instance;
    }

    private FormDbAdapter(Context context) {
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

    private Dao getFormDao() throws SQLException {
        if (formDao == null)
            formDao = mDBHelper.getFormsDao();
        return formDao;
    }

    public long addFormJson(Form  f){
        try {
            Dao dao = getFormDao();
            return dao.create(f);
        }catch (SQLException e){
            Log.d(TAG, "addFormJson: " + e.getMessage());
            return -1;
        }
    }

    public long deleteToken(Form f){
        try {
            Dao dao = getFormDao();
            return dao.delete(f);
        }catch (SQLException e){
            Log.d(TAG, "deleteToken: " + e.getMessage());
            return -1;
        }
    }

    public Form getLastFrom(){
        Dao dao;
        try {
            dao = getFormDao();
            QueryBuilder queryBuilder = dao.queryBuilder();
            queryBuilder.orderBy(Form.COLUMN_ID,true);
            queryBuilder.selectColumns(Form.COLUMN_VALUE);
            Form form = (Form) dao.queryForFirst(queryBuilder.prepare());
            if (form != null)
                return form;
            else
                return new Form();
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d(TAG, "getLastFrom: " + e.getMessage());
            return new Form();
        }
    }

    public ArrayList<JSONObject>getAllFrom(){
        ArrayList<JSONObject>jForm = new ArrayList<>();
        Dao dao;
        try {
            dao = getFormDao();
            jForm = (ArrayList<JSONObject>) dao.queryForAll();
        } catch (SQLException e) {
            Log.d(TAG, "getAllFrom: " + e.getMessage());
        }
        return jForm;
    }
}

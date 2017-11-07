package com.example.stgo.bildform.Database;

import android.content.Context;
import android.util.Log;

import com.example.stgo.bildform.Model.Form;
import com.example.stgo.bildform.Model.FormResponse;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by shagos on 07-11-17.
 */

public class FormResponseDbAdapter {
    private DbHelper mDBHelper;
    private Context context;
    private Dao formResponseDao;
    private static String TAG = "FORMRESPONSEDB";


    private static FormResponseDbAdapter instance = null;

    /**
     * Singleton implementation;
     */

    public static FormResponseDbAdapter getInstance(Context context){
        if (instance == null)
            instance = new FormResponseDbAdapter(context);
        return instance;
    }

    private FormResponseDbAdapter(Context context) {
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

    private Dao getFormResponseDao() throws SQLException {
        if (formResponseDao == null)
            formResponseDao = mDBHelper.getFormResponsesDao();
        return formResponseDao;
    }

    public long addFormResponse(FormResponse fr){
        try {
            Dao dao = getFormResponseDao();
            return dao.create(fr);
        }catch (SQLException e){
            Log.d(TAG, "addFormResponse: " + e.getMessage());
            return -1;
        }
    }

    public ArrayList<JSONObject> getAllFrom(){
        ArrayList<JSONObject>jForm = new ArrayList<>();
        Dao dao;
        try {
            dao = getFormResponseDao();
            jForm = (ArrayList<JSONObject>) dao.queryForAll();
        } catch (SQLException e) {
            Log.d(TAG, "getAllFrom: " + e.getMessage());
        }
        return jForm;
    }

}

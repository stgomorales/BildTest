package com.example.stgo.bildform.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.stgo.bildform.Model.Form;
import com.example.stgo.bildform.Model.FormResponse;
import com.example.stgo.bildform.Model.Parameter;
import com.example.stgo.bildform.Model.Token;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;

/**
 * Created by shagos on 05-11-17.
 */

public class DbHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "bildApp.db";
    private static final int DB_VERSION = 1;
    private static final String TAG = "DBHelper";

    private Dao<Parameter, Integer> parametersDao;
    private Dao<Form, Integer> formsDao;
    private Dao<FormResponse, Integer> formResponsesDao;
    private Dao<Token, Integer> tokensDao;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        Log.d(TAG, "onCreate: " + " create database tables");
        try {
            TableUtils.createTable(connectionSource, Parameter.class);
            TableUtils.createTable(connectionSource, Form.class);
            TableUtils.createTable(connectionSource, FormResponse.class);
            TableUtils.createTable(connectionSource, Token.class);
        }catch (SQLException e){
            e.printStackTrace();
            Log.d(TAG, "onCreate: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        onCreate(database,connectionSource);
        Log.d(TAG, "onUpgrade: " + "update database");
    }

    @Override
    public void close() {
        super.close();
    }

    public Dao<Parameter, Integer> getParametersDao() throws SQLException {
        if (parametersDao == null)
            parametersDao = getDao(Parameter.class);
        return parametersDao;
    }

    public Dao<Form, Integer> getFormsDao() throws SQLException {
        if (formsDao == null)
            formsDao = getDao(Form.class);
        return formsDao;
    }

    public Dao<FormResponse, Integer> getFormResponsesDao() throws SQLException {
        if (formResponsesDao == null)
            formResponsesDao = getDao(FormResponse.class);
        return formResponsesDao;
    }

    public Dao<Token, Integer> getTokensDao() throws SQLException {
        if (tokensDao == null)
            tokensDao = getDao(Token.class);
        return tokensDao;
    }
}

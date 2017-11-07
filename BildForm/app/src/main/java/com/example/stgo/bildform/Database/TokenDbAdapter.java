package com.example.stgo.bildform.Database;

import android.content.Context;
import android.util.Log;

import com.example.stgo.bildform.Model.Token;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by shagos on 06-11-17.
 */

public class TokenDbAdapter {
    private DbHelper mDBHelper;
    private Context context;
    private Dao tokenDao;
    private static String TAG = "TOKENDB";


    private static TokenDbAdapter instance = null;

    /**
     * Singleton implementation;
     */

    public static TokenDbAdapter getInstance(Context context){
        if (instance == null)
            instance = new TokenDbAdapter(context);
        return instance;
    }

    private TokenDbAdapter(Context context) {
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

    private Dao getTokenDao() throws SQLException {
        if (tokenDao == null)
            tokenDao = mDBHelper.getTokensDao();
        return tokenDao;
    }

    public long addToken(Token t){
        try {
            Dao dao = getTokenDao();
            return dao.create(t);
        }catch (SQLException e){
            Log.d(TAG, "addToken: " + e.getMessage());
            return -1;
        }
    }

    public long deleteAllToken(){
        try {
            Dao dao = getTokenDao();
            dao.delete(getAllToken());
            return dao.delete(getAllToken());
        }catch (SQLException e){
            Log.d(TAG, "addToken: " + e.getMessage());
            return -1;
        }
    }

    public long deleteToken(Token t){
        try {
            Dao dao = getTokenDao();
            return dao.delete(t);
        }catch (SQLException e){
            Log.d(TAG, "addToken: " + e.getMessage());
            return -1;
        }
    }
    public ArrayList<Token> getAllToken(){
        Dao dao;
        try {
            dao = getTokenDao();
            return (ArrayList<Token>) dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Token>();
        }
    }

    public JSONObject getLastToken(){
        Dao dao;
        JSONObject jToken = new JSONObject();
        try {
            dao = getTokenDao();
            QueryBuilder queryBuilder = dao.queryBuilder();
            queryBuilder.orderBy(Token.COLUMN_ID,true);
            queryBuilder.selectColumns(Token.COLUMN_TOKEN);
            Token token = (Token) dao.queryForFirst(queryBuilder.prepare());
            if (token != null)
                jToken = new JSONObject(token.getToken());
            return jToken;
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d(TAG, "getLastToken: " + e.getMessage());
            return jToken;
        } catch (JSONException e) {
            e.printStackTrace();
            return jToken;
        }
    }
}

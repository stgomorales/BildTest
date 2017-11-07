package com.example.stgo.bildform.APIS;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by shagos on 05-11-17.
 */

public class Connection {

    private String basePath;
    private String port;
    private String service;
    private int timeOut;
    private int retry;
    private Context context;

    public static int TIMEOUT = 30000;
    public static int RETRY = 3;

    public Connection() {
    }

    public Connection(String basePath, String port, String service, int timeOut, int retry, Context context) {
        this.basePath = basePath;
        this.port = port;
        this.service = service;
        this.timeOut = timeOut;
        this.retry = retry;
        this.context = context;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public int getRetry() {
        return retry;
    }

    public void setRetry(int retry) {
        this.retry = retry;
    }

    public void getAccessToken(final String user , final String password , final ConnectCallback callback){
        String url = "http://" + basePath + ":" + port + service;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            callback.onResponse(new JSONObject(response));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            try {
                                callback.onResponse(new JSONObject("{}"));
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError(error);
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("client_id", user);
                params.put("client_secret", password);
                params.put("grant_type", "client_credentials");
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(timeOut,retry, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue rQueue = Volley.newRequestQueue(context);
        rQueue.add(stringRequest);
    }

    public void getForms(final JSONObject token, final ConnectCallback callback){
        String url = "http://" + basePath + ":" + port + service;
        JsonObjectRequest jrequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                callback.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError(error);
            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                String auth = "";
                try {
                    auth = token.getString("Authorization");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                headers.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
                headers.put("Authorization",auth);
                return headers;
            }

        };
        jrequest.setRetryPolicy(new DefaultRetryPolicy(timeOut,retry, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue rQueue = Volley.newRequestQueue(context);
        rQueue.add(jrequest);
    }
}

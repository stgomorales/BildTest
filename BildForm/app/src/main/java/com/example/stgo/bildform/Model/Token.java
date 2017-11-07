package com.example.stgo.bildform.Model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by shagos on 06-11-17.
 */

public class Token {
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TOKEN = "token";

    @DatabaseField(generatedId = true, columnName = COLUMN_ID)
    private int id;
    @DatabaseField(columnName = COLUMN_TOKEN)
    private String token;

    public Token() {
    }

    public Token(int id, String token) {
        this.id = id;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

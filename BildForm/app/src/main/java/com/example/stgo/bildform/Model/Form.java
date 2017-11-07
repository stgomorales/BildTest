package com.example.stgo.bildform.Model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by shagos on 05-11-17.
 */

public class Form {
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_VALUE = "value";

    @DatabaseField(generatedId = true, columnName = COLUMN_ID)
    private int id;
    @DatabaseField(columnName = COLUMN_VALUE)
    private String value;

    public Form() {
    }

    public Form(String value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

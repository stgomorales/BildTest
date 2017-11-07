package com.example.stgo.bildform.Model;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by shagos on 05-11-17.
 */
@DatabaseTable(tableName = "app_parameters")
public class Parameter {

    public static String CONECCTION_IP = "CONNECTION_IP";
    public static String CONNECTION_PORT = "CONNECTION_PORT";
    public static String FORM_SERVICE = "FORM_SERVICE";
    public static String TOKEN_SERVICE = "TOKEN_SERVICE";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PARAMETER_ID = "parameter_id";
    public static final String COLUMN_VALUE = "value";

    @DatabaseField(generatedId = true, columnName = COLUMN_ID)
    private int id;
    @DatabaseField(columnName = COLUMN_PARAMETER_ID)
    private String parameterId;
    @DatabaseField(columnName = COLUMN_VALUE)
    private String value;

    public Parameter() {
    }

    public Parameter(String parameterId, String value) {
        this.parameterId = parameterId;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParameterId() {
        return parameterId;
    }

    public void setParameterId(String parameterId) {
        this.parameterId = parameterId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

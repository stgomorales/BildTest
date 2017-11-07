package com.example.stgo.bildform.Model;

/**
 * Created by shagos on 07-11-17.
 */

public class Input {
    private String name;
    private  String type;
    private boolean required;

    public Input() {
    }

    public Input(String name, String type, boolean required) {
        this.name = name;
        this.type = type;
        this.required = required;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}

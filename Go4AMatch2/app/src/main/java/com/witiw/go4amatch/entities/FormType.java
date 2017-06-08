package com.witiw.go4amatch.entities;

/**
 * Created by Patryk on 16.05.2017.
 */
public enum FormType {

    GOOD("Good"),
    MEDIUM("Medium"),
    BAD("Bad");

    private String name;

    FormType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

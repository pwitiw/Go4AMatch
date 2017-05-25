package com.witiw.go4amatch.logic.classifier;

/**
 * Created by Patryk on 16.05.2017.
 */
public enum ConditionType {

    GOOD("Good"),
    MEDIUM("Medium"),
    BAD("Bad");

    private String name;

    ConditionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

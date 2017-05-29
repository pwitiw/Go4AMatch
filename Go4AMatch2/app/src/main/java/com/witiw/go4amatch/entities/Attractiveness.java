package com.witiw.go4amatch.entities;

/**
 * Created by Patryk on 16.05.2017.
 */
public class Attractiveness {

    private double value;
    private String form;
    private int position;
    private int importance;
    private boolean isDerby;

    public Attractiveness(double result) {
        this.value = result;
    }

    public Attractiveness() {
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public boolean isDerby() {
        return isDerby;
    }

    public void setDerby(boolean derby) {
        isDerby = derby;
    }
}

package com.witiw.go4amatch.entities;

/**
 * Created by Patryk on 16.05.2017.
 */
public class Attractiveness {

    private double value;
    private FormType formType;
    private int position;
    private int importance;
    private int isDerby;

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

    public FormType getFormType() {
        return formType;
    }

    public void setFormType(FormType formType) {
        this.formType = formType;
    }

    public void setIsDerby(int isDerby) {
        this.isDerby = isDerby;
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

    public int getIsDerby() {
        return isDerby;
    }

    public void setDerby(int derby) {
        isDerby = derby;
    }
}

package com.witiw.go4amatch.logic;

import java.util.List;

/**
 * Created by Patryk on 29.04.2017.
 */
public interface Algorithm<T> {

    void compute(List<T> entries);
}

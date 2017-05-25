package com.witiw.go4amatch.logic.ahp;


import com.witiw.go4amatch.logic.objects.Criterion;

/**
 * Created by Patryk on 29.04.2017.
 */
public class SaatyScale {

    private static final Integer EQUAL = 1;
    private static final Integer MODERATE = 3;
    private static final Integer STRONG = 5;
    private static final Integer VERY_STRONG = 7;
    private static final Integer EXTREME = 9;

    public static double getRange(Criterion c1, Criterion c2) {
        int difference = (c1.getImportance().getRange() - c2.getImportance().getRange());
        double result = -1;
        switch (Math.abs(difference)) {
            case 0:
                result = EQUAL;
                break;
            case 1:
                result = MODERATE;
                break;
            case 2:
                result = STRONG;
                break;
            case 3:
                result = VERY_STRONG;
                break;
            case 4:
                result = EXTREME;
                break;
        }
        return difference >= 0 ? result : 1 / result;
    }
}

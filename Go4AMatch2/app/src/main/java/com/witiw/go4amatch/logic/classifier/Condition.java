package com.witiw.go4amatch.logic.classifier;

/**
 * Created by Patryk on 15.05.2017.
 */
public class Condition {

    private static final int TRESHOLD_GOOD = 63;
    private static final int TRESHOLD_MEDIUM = 45;

    public static ConditionType setDownConditionType(String form1, String form2) {

        int factor1 = computeConditionFactor(form1);
        int factor2 = computeConditionFactor(form2);
        return getFormTypeForFactors(factor1, factor2);
    }

    private static ConditionType getFormTypeForFactors(int f1, int f2) {
        int f = f1 + f2;
        System.out.println(f);
        if (f >= TRESHOLD_GOOD)
            return ConditionType.GOOD;
        else if (f >= TRESHOLD_MEDIUM)
            return ConditionType.MEDIUM;
        else
            return ConditionType.BAD;
    }

    private static int computeConditionFactor(String condition) {
        condition = condition.toUpperCase();
        int factor = 0;
        int multiplier = condition.toCharArray().length;
        for (Character c : condition.toCharArray()) {
            int conditionValue = getValueForCondition(c);
            factor += multiplier * conditionValue;
            multiplier--;
        }

        return factor;
    }

    private static int getValueForCondition(char condition) {
        if (condition == 'L') {
            return 0;
        } else if (condition == 'D')
            return 1;
        else if (condition == 'W')
            return 3;
        else
            throw new IllegalArgumentException();
    }


}

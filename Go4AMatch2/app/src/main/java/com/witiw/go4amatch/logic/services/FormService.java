package com.witiw.go4amatch.logic.services;

import com.witiw.go4amatch.entities.FormType;
import com.witiw.go4amatch.entities.Teams;

/**
 * Created by Patryk on 15.05.2017.
 */
public class FormService {

    private static final int TRESHOLD_GOOD = 63;
    private static final int TRESHOLD_MEDIUM = 45;

    public FormType getFormType(Teams teams) {
        return getFormType(teams.getFormHome(), teams.getFormAway());
    }

    public FormType getFormType(String team1, String team2) {
        int factor1 = computeFormFactor(team1);
        int factor2 = computeFormFactor(team2);
        return getFormTypeForFactors(factor1, factor2);
    }

    private FormType getFormTypeForFactors(int f1, int f2) {
        int f = f1 + f2;
        System.out.println(f);
        if (f >= TRESHOLD_GOOD)
            return FormType.GOOD;
        else if (f >= TRESHOLD_MEDIUM)
            return FormType.MEDIUM;
        else
            return FormType.BAD;
    }

    private int computeFormFactor(String condition) {
        condition = condition.toUpperCase();
        int factor = 0;
        int multiplier = condition.toCharArray().length;
        for (Character c : condition.toCharArray()) {
            int conditionValue = getValueForForm(c);
            factor += multiplier * conditionValue;
            multiplier--;
        }

        return factor;
    }

    private int getValueForForm(char condition) {
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

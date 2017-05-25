package com.witiw.go4amatch.logic.ahp;

/**
 * Created by Patryk on 28.04.2017.
 */
public enum Importance {

    IRRELEVANT(1),
    NOT_IMPORTANT(2),
    NEUTRAL(3),
    IMPORTANT(4),
    VERY_IMPORTANT(5);

    private final int range;

    Importance(final int range) {
        this.range = range;
    }

    public int getRange() {
        return range;
    }

    public static Importance valueOf(final int range) {
        for (Importance i : values()) {
            if (i.getRange() == range)
                return i;
        }
        throw new IllegalArgumentException("Importance with given range " + range + " does not exist.");
    }

}

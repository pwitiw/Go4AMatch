package com.witiw.go4amatch.logic.ahp;

/**
 * Created by Patryk on 28.04.2017.
 */
public enum Importance {
    NEGLIGIBLE(0, "Pomijalne"),
    EQUAL_IMPORTANCE(1, "Nieważne"),
    WEAK(2, "Nieznacznie ważne"),
    MODERATE_IMPORTANCE(3, "Nieznacznie ważne"),
    MODERATE_PLUS(4, "Umiarkowane"),
    STRONG_IMPORTANCE(5, "Umiarkowane+"),
    STRONG_PLUS(6, "Bardzo ważne"),
    VERY_STRONG(7, "Bardzo ważne+"),
    VERY_VERY_STRONG(8, "Nadzwyczaj ważne"),
    EXTREME_IMPORTANCE(9, "Nadzwyczaj ważne+");

    private final int range;
    private final String importanceLevel;

    Importance(final int range, final String importanceLevel) {
        this.range = range;
        this.importanceLevel = importanceLevel;
    }

    public int getRange() {
        return range;
    }


    public String getImportanceLevel() {
        return importanceLevel;
    }

    public static Importance valueOf(final int range) {
        for (Importance i : values()) {
            if (i.getRange() == range)
                return i;
        }
        throw new IllegalArgumentException("Importance with given range " + range + " does not exist.");
    }

}

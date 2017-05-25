package com.witiw.go4amatch.entities;

/**
 * Created by Patryk on 21.05.2017.
 */
public enum LigueType {

    CHAMPIONS(0),
    EUROPE(0),
    PRIMERA_DIVISION(98.284),
    BUNDESLIGA(76.498),
    PREMIERE_LEAGUE(72.391),
    SERIE_A(69.998),
    LIGUE_1(52.999),
    PRIMEIRA_LIGA(48.999),
    PRIEMJER_LIGA(48.732),
    PREMIER_LIHA(42.233),
    EERSTE_KLASE(38.400),
    SUPER_LIG(36.800);

    private double uefaCoefficient;
    private String leagueCode;

    LigueType(double uefaCoefficient) {
        this.uefaCoefficient = uefaCoefficient;
    }

    public double getUefaCoefficient() {
        return uefaCoefficient;
    }

    public String getLeagueCode() {
        return leagueCode;
    }

    public LigueType getTypeForCode(String leagueCode) {

        for (LigueType type : values()) {
            if (type.leagueCode == leagueCode)
                return type;
        }
        throw new IllegalArgumentException("League Type for given code: " + leagueCode + "does not exists");
    }
}

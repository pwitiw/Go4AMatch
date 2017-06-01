package com.witiw.go4amatch.entities;

/**
 * Created by Patryk on 21.05.2017.
 */
public enum LigueType {

    CHAMPIONS_LEAGUE(0, "Champions League"),
    EUROPE(0, "Europe League"),
    PRIMERA_DIVISION(98.284, "Primera Divison"),
    BUNDESLIGA(76.498, "Bundesliga"),
    PREMIERE_LEAGUE(72.391, "Premier League"),
    SERIE_A(69.998, "Serie A"),
    LIGUE_1(52.999, "Ligue 1"),
    PRIMEIRA_LIGA(48.999, "Primeira Liga"),
    PRIEMJER_LIGA(48.732, "Priemjer Liga"),
    PREMIER_LIHA(42.233, "Premier Liha"),
    EERSTE_KLASSE(38.400, "Eerste Klasse"),
    SUPER_LIG(36.800, "Super Lig");

    private double uefaCoefficient;
    private String leagueName;

    LigueType(double uefaCoefficient, String leagueName) {
        this.uefaCoefficient = uefaCoefficient;
        this.leagueName = leagueName;
    }

    public double getUefaCoefficient() {
        return uefaCoefficient;
    }

    public void setUefaCoefficient(double uefaCoefficient) {
        this.uefaCoefficient = uefaCoefficient;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public static LigueType getTypeForName(String leagueName) {
        for (LigueType type : values()) {
            if (type.leagueName.toLowerCase().equals(leagueName.toLowerCase()))
                return type;
        }
        throw new IllegalArgumentException("League Type for given code: " + leagueName + "does not exists");
    }
}

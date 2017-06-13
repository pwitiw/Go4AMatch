package com.witiw.go4amatch.entities;

/**
 * Created by Patryk on 21.05.2017.
 */
public enum LeagueType {

    CHAMPIONS_LEAGUE(0, "Champions League", "sr:tournament:7"),
    EUROPE(0, "Europe League", "sr:tournament:679"),
    SPAIN(98.284, "Primera Divison", "sr:tournament:8"),
    GERMANY(76.498, "Bundesliga", "sr:tournament:35"),
    ENGLAND(72.391, "Premier League", "sr:tournament:17"),
    ITALY(69.998, "Serie A", "sr:tournament:23"),
    FRANCE(52.999, "Ligue 1", "sr:tournament:34"),
    PORTUGAL(48.999, "Primeira Liga", "sr:tournament:238"),
    RUSSIA(48.732, "Premier League", "sr:tournament:203"),
    UKRAINE(42.233, "Premier League", "sr:tournament:218"),
    BELGIUM(38.400, "Pro League", "sr:tournament:38"),
    TURKEY(36.800, "Super Lig", "sr:tournament:52"),
    OTHER(21.001, "-", "other");

    private double uefaCoefficient;
    private String leagueName;
    private String sportRadarId;

    LeagueType(double uefaCoefficient, String leagueName, String sportRadarId) {
        this.uefaCoefficient = uefaCoefficient;
        this.leagueName = leagueName;
        this.sportRadarId = sportRadarId;
    }

    public double getUefaCoefficient() {
        return uefaCoefficient;
    }

    public String getSportRadarId() {
        return sportRadarId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public static LeagueType getTypeForName(String leagueName) {
        for (LeagueType type : values()) {
            if (type.leagueName.toLowerCase().equals(leagueName.toLowerCase()))
                return type;
        }
        return LeagueType.OTHER;
    }

    public static double getCoefficientForSportRadarId(String sportRadarId) {
        for (LeagueType type : values()) {
            if (type.sportRadarId.toLowerCase().equals(sportRadarId))
                return type.getUefaCoefficient();
        }
        return OTHER.getUefaCoefficient();
    }

}

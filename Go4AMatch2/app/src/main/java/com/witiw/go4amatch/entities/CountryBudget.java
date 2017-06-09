package com.witiw.go4amatch.entities;

/**
 * Created by Patryk on 09.06.2017.
 */

public enum CountryBudget {


    CHAMPIONS_LEAGUE(0, 0, 1.5, ""),
    EUROPE(0, 0, 1.2, ""),
    SPAIN(283, 26, 124, "ESP"),
    GERMANY(238, 29, 59, "DEU"),
    ENGLAND(370, 29, 247, "ENG"),
    ITALY(293, 35, 89, "ITA"),
    FRANCE(317, 39, 75, "FRA"),
    PORTUGAL(239, 22, 32, "PRT"),
    RUSSIA(163, 19, 24, "RUS"),
    UKRAINE(154, 12, 5, "UKR"),
    BELGIUM(300, 37, 64, "BEL"),
    TURKEY(192, 19, 35, "TUR"),
    OTHER(255, 27, 45, "");

    private int accomodation;
    private int food;
    private double tickets;
    private String countryCode;


    CountryBudget(int accomodation, int food, double tickets, String countryCode) {
        this.accomodation = accomodation;
        this.food = food;
        this.tickets = tickets;
        this.countryCode = countryCode;
    }

    public static double getBudget(String countryCode) {
        for (CountryBudget countryBudget : CountryBudget.values()) {
            if (countryBudget.countryCode.equals(countryCode)) {

                return getBudgetSum(countryBudget);
            }
        }
        return getBudgetSum(OTHER);
    }

    private static double getBudgetSum(CountryBudget countryBudget) {
        return countryBudget.accomodation + countryBudget.food + getTicketBudget(countryBudget);
    }

    private static double getTicketBudget(CountryBudget countryBudget) {
        if (countryBudget == CHAMPIONS_LEAGUE)
            return CHAMPIONS_LEAGUE.tickets * countryBudget.tickets;
        else if (countryBudget == EUROPE)
            return EUROPE.tickets * countryBudget.tickets;
        else
            return countryBudget.tickets;
    }


}

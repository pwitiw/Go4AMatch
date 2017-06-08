package com.witiw.go4amatch.entities;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patryk on 05.06.2017.
 */

public final class DerbyList {
    public static final List<Pair<String, String>> DERBY_PAIRS = new ArrayList() {{
        //Spain
        add(new Pair("Real Madrid", "FC Barcelona"));
        add(new Pair("Athletic Bilbao", "Real Sociedad"));
        //Germany
        add(new Pair("Bayern Munich", "Borussia Dortmund"));
        add(new Pair("FC Schalke 04", "Borussia Dortmund"));
        add(new Pair("Bayern Munich", "FC Schalke 04"));
        //England
        add(new Pair("Manchester United", "Liverpool FC"));
        add(new Pair("Manchester United", "Arsenal FC"));
        add(new Pair("Manchester United", "Chelsea FC"));
        add(new Pair("Manchester City", "Everton FC"));
        //Italy
        add(new Pair("Inter Milan ", "Juventus Turin"));
        add(new Pair("SSC Napoli", "AS Roma"));
        //France
        add(new Pair("Paris Saint-Germain", "Olympique Lyon"));
        add(new Pair("Olympique Marseille", "FC Girondins Bordeaux"));
        add(new Pair("Olympique Lyon", "Lille OSC"));
        //France
        add(new Pair("Benfica Lisbon", "FC Porto"));
        //Russia
        add(new Pair("FC Spartak Moscow", "FC Zenit St Petersburg"));
        //Ukraine
        add(new Pair("FC Shakhtar Donetsk", "FC Dynamo Kiev"));
        //Belgium
        add(new Pair("RSC Anderlecht", "Standard Liege"));
        add(new Pair("RSC Anderlecht", "Club Brugge"));
        add(new Pair("KAA Gent", "Club Brugge"));
        //Turkey
        add(new Pair("Trabzonspor", "Fenerbahce"));
    }};

    public static boolean contains(String team1, String team2) {
        for (Pair<String, String> pair : DERBY_PAIRS) {
            if (pair.first.equals(team1) && pair.second.equals(team2)
                    || pair.first.equals(team2) && pair.second.equals(team1))
                return true;
        }
        return false;
    }
}

package com.witiw.go4amatch.utils;

import com.witiw.go4amatch.entities.Attractiveness;
import com.witiw.go4amatch.entities.LigueType;
import com.witiw.go4amatch.entities.SportingEvent;
import com.witiw.go4amatch.rest.sportradar.sdk.Sport;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patryk on 28.05.2017.
 */

public class XmlPojoConverter {

    private static final String SPORTING_EVENT = "SportingEvent";
    private static final String TEAM_HOME = "home";
    private static final String TEAM_AWAY = "away";
    private static final String ATTRACTIVENESS = "Attractiveness";
    private static final String AREA_ATTRACTIVENESS = "AreaAttractiveness";
    private static final String IMPORTANCE = "Importance";
    private static final String POSITION = "Position";
    private static final String DERBY = "Derby";
    private static final String BUDGET = "Budget";
    private static final String LIGUE_TYPE = "LigueType";
    private static final String DISTANCE = "Distance";
    private static final String FORM_HOME = "FormHome";
    private static final String FORM_AWAY = "FormAway";


    public static List<SportingEvent> convert(Document document) {
        List<SportingEvent> events = new ArrayList<>();
        NodeList nodes = document.getElementsByTagName(SPORTING_EVENT);
        for (int i = 0; i < nodes.getLength(); i++) {
            SportingEvent event = new SportingEvent();
            Element element = (Element) nodes.item(i);
            event.getTeams().setHome(element.getAttribute(TEAM_HOME));
            event.getTeams().setAway(element.getAttribute(TEAM_AWAY));

            /* ATRACTIVENESS */
            Element attractiveness = (Element) element.getElementsByTagName(ATTRACTIVENESS).item(0);
            event.getAttractiveness().setImportance(Integer.parseInt(attractiveness.getElementsByTagName(IMPORTANCE).item(0).getTextContent()));
            event.getAttractiveness().setDerby(Integer.parseInt(attractiveness.getElementsByTagName(DERBY).item(0).getTextContent()));
            event.getAttractiveness().setPosition(Integer.parseInt(attractiveness.getElementsByTagName(POSITION).item(0).getTextContent()));
            event.getTeams().setFormHome(attractiveness.getElementsByTagName(FORM_HOME).item(0).getTextContent());
            event.getTeams().setFormAway(attractiveness.getElementsByTagName(FORM_AWAY).item(0).getTextContent());
            /* OTHER */
            event.setAreaAttractiveness(Double.parseDouble(element.getElementsByTagName(AREA_ATTRACTIVENESS).item(0).getTextContent()));
            event.setBudget(Integer.parseInt(element.getElementsByTagName(BUDGET).item(0).getTextContent()));
            event.setLigueType(LigueType.getTypeForName(element.getElementsByTagName(LIGUE_TYPE).item(0).getTextContent()));
            event.setDistance(Double.parseDouble(element.getElementsByTagName(DISTANCE).item(0).getTextContent()));
            events.add(event);
        }

        return events;
    }

    public static SportingEvent XmlToPojoSportingEvent(Node sportingEvent) {
        SportingEvent event = new SportingEvent();
        Attractiveness attractiveness = new Attractiveness();

//
//        Element firstWeatherElement = (Element) firstWeatherNode;
//        //-------
//        NodeList idList = firstWeatherElement.getElementsByTagName(KEY_ID);
//        Element firstIdElement = (Element) idList.item(0);
//        NodeList textIdList = firstIdElement.getChildNodes();
//        //--id
//        map.put(KEY_ID, ((Node) textIdList.item(0)).getNodeValue().trim());
//
//        //2.-------
//        NodeList cityList = firstWeatherElement.getElementsByTagName(KEY_CITY);
//        Element firstCityElement = (Element) cityList.item(0);
//        NodeList textCityList = firstCityElement.getChildNodes();
//        //--city
//        map.put(KEY_CITY, ((Node) textCityList.item(0)).getNodeValue().trim());
//
//        //3.-------
//        NodeList tempList = firstWeatherElement.getElementsByTagName(KEY_TEMP_C);
//        Element firstTempElement = (Element) tempList.item(0);
//        NodeList textTempList = firstTempElement.getChildNodes();
//        //--temperature
//        map.put(KEY_TEMP_C, ((Node) textTempList.item(0)).getNodeValue().trim());

        return event;

    }
}
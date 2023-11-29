package org.example.DirectionSSAU.IIK.IIKDirectionOfGroups;

import org.example.Telegram.Model.DirectionIIK;

import java.util.*;

public class IIKDirectionOfGroupCourses {
    public static Map<String, List<String>> directionOfGroupFirst = new HashMap() {{
        put(DirectionIIK.PMI.get(), new ArrayList<>(Arrays.asList("6101", "6102", "6103", "6104", "6105", "6106")));
        put(DirectionIIK.FIIT.get(), new ArrayList<>(Arrays.asList("6101", "6102", "6103", "6104")));
        put(DirectionIIK.IVT.get(), new ArrayList<>(Arrays.asList("6101", "6102", "6103", "6104", "6105", "6106")));
        put(DirectionIIK.IBAS.get(), new ArrayList<>(Arrays.asList("6111", "6112", "6113", "6114")));
        put(DirectionIIK.RADIO_TECH.get(), new ArrayList<>(Collections.singletonList("6101")));
        put(DirectionIIK.ELECTRONICS.get(), new ArrayList<>(Collections.singletonList("6101")));
        put(DirectionIIK.BIO_TECH.get(), new ArrayList<>(Arrays.asList("6101", "6102")));
        put(DirectionIIK.PMF.get(), new ArrayList<>(Arrays.asList("6101", "6102")));
    }};
    public static Map<String, List<String>> directionOfGroupSecond = new HashMap() {{
        put(DirectionIIK.PMI.get(), new ArrayList<>(Arrays.asList("6201", "6202", "6203", "6204", "6205")));
        put(DirectionIIK.FIIT.get(), new ArrayList<>(Arrays.asList("6201", "6202", "6203", "6204")));
        put(DirectionIIK.IVT.get(), new ArrayList<>(Arrays.asList("6201", "6202", "6203", "6204", "6205")));
        put(DirectionIIK.IBAS.get(), new ArrayList<>(Arrays.asList("6211", "6212", "6213", "6214")));
        put(DirectionIIK.RADIO_TECH.get(), new ArrayList<>(Collections.singletonList("6262")));
        put(DirectionIIK.ELECTRONICS.get(), new ArrayList<>(Collections.singletonList("6266")));
        put(DirectionIIK.BIO_TECH.get(), new ArrayList<>(Arrays.asList("6264")));
        put(DirectionIIK.PMF.get(), new ArrayList<>(Arrays.asList("6201", "6202")));
        put(DirectionIIK.CONSTRUCTION_AND_TECH_ELECTRONICS.get(), new ArrayList<>(Arrays.asList("6265")));
        put(DirectionIIK.LASER_TECH.get(), new ArrayList<>(Arrays.asList("6263")));
    }};
    public static Map<String, List<String>> directionOfGroupThird = new HashMap() {{
        put(DirectionIIK.PMI.get(), new ArrayList<>(Arrays.asList("6307", "6308", "6309")));
        put(DirectionIIK.FIIT.get(), new ArrayList<>(Arrays.asList("6313", "6314", "6315")));
        put(DirectionIIK.IVT.get(), new ArrayList<>(Arrays.asList("6302", "6303", "6304")));
        put(DirectionIIK.IBAS.get(), new ArrayList<>(Arrays.asList("6311", "6312")));
        put(DirectionIIK.RADIO_TECH.get(), new ArrayList<>(Collections.singletonList("6362")));
        put(DirectionIIK.ELECTRONICS.get(), new ArrayList<>(Collections.singletonList("6366")));
        put(DirectionIIK.BIO_TECH.get(), new ArrayList<>(Arrays.asList("6364")));
        put(DirectionIIK.PMF.get(), new ArrayList<>(Arrays.asList("6306")));
        put(DirectionIIK.CONSTRUCTION_AND_TECH_ELECTRONICS.get(), new ArrayList<>(Arrays.asList("6365")));
        put(DirectionIIK.LASER_TECH.get(), new ArrayList<>(Arrays.asList("6363")));
    }};
    public static Map<String, List<String>> directionOfGroupFourth = new HashMap() {{
        put(DirectionIIK.PMI.get(), new ArrayList<>(Arrays.asList("6307", "6308", "6309")));
        put(DirectionIIK.FIIT.get(), new ArrayList<>(Arrays.asList("6414", "6415")));
        put(DirectionIIK.IVT.get(), new ArrayList<>(Arrays.asList("6402", "6403", "6404")));
        put(DirectionIIK.IBAS.get(), new ArrayList<>(Arrays.asList("6411", "6412")));
        put(DirectionIIK.RADIO_TECH.get(), new ArrayList<>(Collections.singletonList("6462")));
        put(DirectionIIK.ELECTRONICS.get(), new ArrayList<>(Collections.singletonList("6466")));
        put(DirectionIIK.BIO_TECH.get(), new ArrayList<>(Arrays.asList("6464")));
        put(DirectionIIK.PMF.get(), new ArrayList<>(Arrays.asList("6406")));
        put(DirectionIIK.CONSTRUCTION_AND_TECH_ELECTRONICS.get(), new ArrayList<>(Arrays.asList("6465")));
        put(DirectionIIK.LASER_TECH.get(), new ArrayList<>(Arrays.asList("6463")));
    }};
    public static Map<String, List<String>> directionOfGroupFifth = new HashMap() {{
        put(DirectionIIK.IBAS.get(), new ArrayList<>(Arrays.asList("6511", "6512")));
        put(DirectionIIK.RADIO_ELECTRONICS_AND_COMPLEXES.get(), new ArrayList<>(Arrays.asList("6561")));
    }};

    public static List<String> choiceCourse(String direction, int course) {
        if (course == 1)
            return directionOfGroupFirst.get(direction);
        else if (course == 2)
            return directionOfGroupSecond.get(direction);
        else if (course == 3)
            return directionOfGroupThird.get(direction);
        else if (course == 4)
            return directionOfGroupFourth.get(direction);
        else
            return directionOfGroupFifth.get(direction);
    }
}
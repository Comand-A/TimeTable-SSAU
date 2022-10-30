package org.example.DirectionSSAU.IIK.IIKDirectionOfGroups;

import org.example.Telegram.Model.DirectionIIK;

import java.util.*;

public class IIKDirectionOfGroupSecondCourse {
    public Map<String, List<String>> directionOfGroup = new HashMap() {{
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
    private String direction;

    public IIKDirectionOfGroupSecondCourse(String direction) {
        this.direction = direction;

    }

    public boolean checkAvailabilityDirection() {
        return directionOfGroup.containsKey(direction);
    }

    public List<String> getGroupUser() {
        return directionOfGroup.get(direction);
    }
}

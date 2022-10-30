package org.example.DirectionSSAU.IIK.IIKDirectionOfGroups;

import org.example.Telegram.Model.DirectionIIK;

import java.util.*;

public class IIKDirectionOfGroupThirdCourse {
    public Map<String, List<String>> directionOfGroup = new HashMap() {{
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
    private String direction;

    public IIKDirectionOfGroupThirdCourse(String direction) {
        this.direction = direction;

    }

    public boolean checkAvailabilityDirection() {
        return directionOfGroup.containsKey(direction);
    }

    public List<String> getGroupUser() {
        return directionOfGroup.get(direction);
    }
}

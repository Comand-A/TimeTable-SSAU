package org.example.DirectionSSAU.IIK.IIKDirectionOfGroups;

import org.example.Telegram.Model.DirectionIIK;

import java.util.*;

public class IIKDirectionOfGroupFourthCourse {
    public Map<String, List<String>> directionOfGroup = new HashMap() {{
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
    private String direction;

    public IIKDirectionOfGroupFourthCourse(String direction) {
        this.direction = direction;

    }

    public boolean checkAvailabilityDirection() {
        return directionOfGroup.containsKey(direction);
    }

    public List<String> getGroupUser() {
        return directionOfGroup.get(direction);
    }
}

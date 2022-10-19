package org.example.DirectionSSAU;

import org.example.Telegram.Model.Direction;

import java.util.*;

public class IIKDirectionOfGroupFirstCourse {
    public Map<String, List<String>> directionOfGroup = new HashMap() {{
        put(Direction.PMI.get(), new ArrayList<>(Arrays.asList("6101","6102","6103","6104","6105","6106")));
        put(Direction.FIIT.get(), new ArrayList<>(Arrays.asList("6101","6102","6103","6104")));
        put(Direction.IVT.get(), new ArrayList<>(Arrays.asList("6101","6102","6103","6104","6105","6106")));
        put(Direction.RADIO_TECH.get(), new ArrayList<>(Collections.singletonList("6101")));
        put(Direction.ELECTRONICS.get(), new ArrayList<>(Collections.singletonList("6101")));
        put(Direction.BIOTECH.get(), new ArrayList<>(Arrays.asList("6101","6102")));
        put(Direction.PMF.get(), new ArrayList<>(Arrays.asList("6101","6102")));
    }};
    private String direction;

    public IIKDirectionOfGroupFirstCourse(String direction) {
        this.direction = direction;

    }

    public boolean checkAvailabilityDirection() {
        return directionOfGroup.containsKey(direction);
    }

    public List<String> getDirectionUser() {
        return directionOfGroup.get(direction);
    }

}

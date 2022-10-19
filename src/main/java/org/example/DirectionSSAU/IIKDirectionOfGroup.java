package org.example.DirectionSSAU;

import org.example.Telegram.Model.Direction;

import java.util.HashMap;
import java.util.Map;

public class IIKDirectionOfGroup {
    public Map<String, Integer> directionOfGroup = new HashMap<String, Integer>() {{
        put(Direction.PMI.get(), 6);
        put(Direction.FIIT.get(), 4);
        put(Direction.IVT.get(), 6);
        put(Direction.RADIO_TECH.get(), 1);
        put(Direction.ELECTRONICS.get(), 1);
        put(Direction.BIOTECH.get(), 2);
        put(Direction.PMF.get(), 2);
    }};
    private String direction;

    public IIKDirectionOfGroup(String direction) {
        this.direction = direction;

    }

    public boolean checkAvailabilityDirection() {
        return directionOfGroup.containsKey(direction);
    }

    public int getDirectionUser() {
        return directionOfGroup.get(this.direction);
    }

}

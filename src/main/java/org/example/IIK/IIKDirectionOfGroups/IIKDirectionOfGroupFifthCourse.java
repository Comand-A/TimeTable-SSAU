package org.example.IIK.IIKDirectionOfGroups;

import org.example.Telegram.Model.DirectionIIK;

import java.util.*;

public class IIKDirectionOfGroupFifthCourse {
    public Map<String, List<String>> directionOfGroup = new HashMap() {{
        put(DirectionIIK.IBAS.get(), new ArrayList<>(Arrays.asList("6511", "6512")));
        put(DirectionIIK.RADIO_ELECTRONICS_AND_COMPLEXES.get(), new ArrayList<>(Arrays.asList("6561")));
    }};
    private String direction;

    public IIKDirectionOfGroupFifthCourse(String direction) {
        this.direction = direction;

    }
    public List<String> returnList(){
        return directionOfGroup.get(direction);
    }
}

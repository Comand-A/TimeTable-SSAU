package org.example.IIK.IIKDirectionOfGroups;

import org.example.Telegram.Model.DirectionIIK;

import java.util.*;

public class IIKDirectionOfGroupFirstCourse {
    public Map<String, List<String>> directionOfGroup = new HashMap() {{
        put(DirectionIIK.PMI.get(), new ArrayList<>(Arrays.asList("6101", "6102", "6103", "6104", "6105", "6106")));
        put(DirectionIIK.FIIT.get(), new ArrayList<>(Arrays.asList("6101", "6102", "6103", "6104")));
        put(DirectionIIK.IVT.get(), new ArrayList<>(Arrays.asList("6101", "6102", "6103", "6104", "6105", "6106")));
        put(DirectionIIK.IBAS.get(), new ArrayList<>(Arrays.asList("6111", "6112", "6113", "6114")));
        put(DirectionIIK.RADIO_TECH.get(), new ArrayList<>(Collections.singletonList("6101")));
        put(DirectionIIK.ELECTRONICS.get(), new ArrayList<>(Collections.singletonList("6101")));
        put(DirectionIIK.BIO_TECH.get(), new ArrayList<>(Arrays.asList("6101", "6102")));
        put(DirectionIIK.PMF.get(), new ArrayList<>(Arrays.asList("6101", "6102")));
    }};
    private String direction;

    public IIKDirectionOfGroupFirstCourse(String direction) {
        this.direction = direction;
    }
    public List<String> returnList(){
        return directionOfGroup.get(direction);
    }
}

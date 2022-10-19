package org.example.DirectionSSAU;

import org.example.Telegram.Model.Direction;

import java.util.HashMap;
import java.util.Map;

public class IIKFirstCourseId {
    public Map<String, String> map = new HashMap<String, String>() {{
        put(Direction.PMI.get() + "6101", "755921059");
        put(Direction.PMI.get() + "6102", "755921402");
        put(Direction.PMI.get() + "6103", "755921408");
        put(Direction.PMI.get() + "6104", "755921409");
        put(Direction.PMI.get() + "6105", "755921609");
        put(Direction.PMI.get() + "6106", "762577712");
        put(Direction.FIIT.get() + "6101", "755922237");
        put(Direction.FIIT.get() + "6102", "755922246");
        put(Direction.FIIT.get() + "6103", "755922248");
        put(Direction.FIIT.get() + "6104", "762577970");
        put(Direction.IVT.get() + "6101", "531023128");
        put(Direction.IVT.get() + "6102", "531023236");
        put(Direction.IVT.get() + "6103", "531023665");
        put(Direction.IVT.get() + "6104", "531872709");
        put(Direction.IVT.get() + "6105", "755922552");
        put(Direction.IVT.get() + "6106", "762577998");
        put(Direction.RADIO_TECH.get()+ "6101", "804399515");
        put(Direction.ELECTRONICS.get()+ "6101", "804433186");
        put(Direction.BIOTECH.get() + "6101", "804435060");
        put(Direction.BIOTECH.get() + "6102", "804435212");
        put(Direction.PMF.get() + "6101", "755922274");
        put(Direction.PMF.get() + "6102", "755922277");
    }};
    private String directionOfGroup;

    public IIKFirstCourseId(String direction, String numberGroup) {
        this.directionOfGroup = direction + numberGroup;

    }

    public String getIdDirectionUser() {
        return map.get(directionOfGroup);
    }


}

package org.example.DirectionSSAU;

import org.example.Telegram.Model.Direction;

import java.util.HashMap;
import java.util.Map;

public class IIKFirstCourse {
    public Map<String, String> map = new HashMap<String, String>() {{
        put(Direction.PMI.get() + "1", "755921059");
        put(Direction.PMI.get() + "2", "755921402");
        put(Direction.PMI.get() + "3", "755921408");
        put(Direction.PMI.get() + "4", "755921409");
        put(Direction.PMI.get() + "5", "755921609");
        put(Direction.PMI.get() + "6", "762577712");
        put(Direction.FIIT.get() + "1", "755922237");
        put(Direction.FIIT.get() + "2", "755922246");
        put(Direction.FIIT.get() + "3", "755922248");
        put(Direction.FIIT.get() + "4", "762577970");
        put(Direction.IVT.get() + "1", "531023128");
        put(Direction.IVT.get() + "2", "531023236");
        put(Direction.IVT.get() + "3", "531023665");
        put(Direction.IVT.get() + "4", "531872709");
        put(Direction.IVT.get() + "5", "755922552");
        put(Direction.IVT.get() + "6", "762577998");
        put(Direction.RADIO_TECH.get()+ "1", "804399515");
        put(Direction.ELECTRONICS.get()+ "1", "804433186");
        put(Direction.BIOTECH.get() + "1", "804435060");
        put(Direction.BIOTECH.get() + "2", "804435212");
        put(Direction.PMF.get() + "1", "755922274");
        put(Direction.PMF.get() + "2", "755922277");
    }};
    private String directionOfGroup;

    public IIKFirstCourse(String direction, String numberGroup) {
        this.directionOfGroup = direction + numberGroup;

    }

    public String initializeDirectionUser() {
        return map.get(directionOfGroup);
    }


}

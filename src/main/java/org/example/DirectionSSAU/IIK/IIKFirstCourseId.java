package org.example.DirectionSSAU.IIK;

import org.example.Telegram.Model.DirectionIIK;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IIKFirstCourseId {
    public Map<String, String> map = new HashMap<String, String>() {{
        put(DirectionIIK.PMI.get() + "6101", "755921059");
        put(DirectionIIK.PMI.get() + "6102", "755921402");
        put(DirectionIIK.PMI.get() + "6103", "755921408");
        put(DirectionIIK.PMI.get() + "6104", "755921409");
        put(DirectionIIK.PMI.get() + "6105", "755921609");
        put(DirectionIIK.PMI.get() + "6106", "762577712");
        put(DirectionIIK.FIIT.get() + "6101", "755922237");
        put(DirectionIIK.FIIT.get() + "6102", "755922246");
        put(DirectionIIK.FIIT.get() + "6103", "755922248");
        put(DirectionIIK.FIIT.get() + "6104", "762577970");
        put(DirectionIIK.IVT.get() + "6101", "531023128");
        put(DirectionIIK.IVT.get() + "6102", "531023236");
        put(DirectionIIK.IVT.get() + "6103", "531023665");
        put(DirectionIIK.IVT.get() + "6104", "531872709");
        put(DirectionIIK.IVT.get() + "6105", "755922552");
        put(DirectionIIK.IVT.get() + "6106", "762577998");
        put(DirectionIIK.RADIO_TECH.get() + "6101", "804399515");
        put(DirectionIIK.ELECTRONICS.get() + "6101", "804433186");
        put(DirectionIIK.BIO_TECH.get() + "6101", "804435060");
        put(DirectionIIK.BIO_TECH.get() + "6102", "804435212");
        put(DirectionIIK.PMF.get() + "6101", "755922274");
        put(DirectionIIK.PMF.get() + "6102", "755922277");
    }};
    private String directionOfGroup;

    public IIKFirstCourseId(ArrayList<String> directionOfGroup) {
        this.directionOfGroup = directionOfGroup.get(1) + directionOfGroup.get(2);

    }

    public String getIdDirectionUser() {
        return map.get(directionOfGroup);
    }


}

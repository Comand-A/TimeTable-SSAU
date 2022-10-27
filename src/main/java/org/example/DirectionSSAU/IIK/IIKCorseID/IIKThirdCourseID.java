package org.example.DirectionSSAU.IIK.IIKCorseID;

import org.example.Telegram.Model.DirectionIIK;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IIKThirdCourseID {
    public Map<String, String> map = new HashMap<String, String>() {{
        put(DirectionIIK.PMI.get() + "6307", "531873550");
        put(DirectionIIK.PMI.get() + "6308", "531873790");
        put(DirectionIIK.PMI.get() + "6309", "531873791");
        put(DirectionIIK.FIIT.get() + "6313", "531874228");
        put(DirectionIIK.FIIT.get() + "6314", "531874229");
        put(DirectionIIK.FIIT.get() + "6315", "531874230");
        put(DirectionIIK.IVT.get() + "6302", "531023239");
        put(DirectionIIK.IVT.get() + "6303", "531023670");
        put(DirectionIIK.IVT.get() + "6304", "531873290");
        put(DirectionIIK.RADIO_TECH.get() + "6362", "751514468");
        put(DirectionIIK.ELECTRONICS.get() + "6366", "531319570");
        put(DirectionIIK.BIO_TECH.get() + "6364", "531010832");
        put(DirectionIIK.PMF.get() + "6306", "531874219");
        put(DirectionIIK.LASER_TECH.get() + "6363", "531013109");
        put(DirectionIIK.CONSTRUCTION_AND_TECH_ELECTRONICS.get() + "6365", "531013095");
    }};
    private String directionOfGroup;

    public IIKThirdCourseID(ArrayList<String> directionOfGroup) {
        this.directionOfGroup = directionOfGroup.get(1) + directionOfGroup.get(2);

    }

    public String getIdDirectionUser() {
        return map.get(directionOfGroup);
    }
}

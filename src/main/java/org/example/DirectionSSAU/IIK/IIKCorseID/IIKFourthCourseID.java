package org.example.DirectionSSAU.IIK.IIKCorseID;

import org.example.Telegram.Model.DirectionIIK;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IIKFourthCourseID {
    public Map<String, String> map = new HashMap<String, String>() {{
        put(DirectionIIK.PMI.get() + "6407", "531873795");
        put(DirectionIIK.PMI.get() + "6408", "531873797");
        put(DirectionIIK.PMI.get() + "6409", "531873798");
        put(DirectionIIK.FIIT.get() + "6414", "531874232");
        put(DirectionIIK.FIIT.get() + "6415", "531874233");
        put(DirectionIIK.IVT.get() + "6402", "531023552");
        put(DirectionIIK.IVT.get() + "6403", "531023671");
        put(DirectionIIK.IVT.get() + "6404", "531873291");
        put(DirectionIIK.RADIO_TECH.get() + "6462", "531319571");
        put(DirectionIIK.ELECTRONICS.get() + "6466", "531319570");
        put(DirectionIIK.BIO_TECH.get() + "6464", "531010833");
        put(DirectionIIK.PMF.get() + "6406", "799360924");
        put(DirectionIIK.LASER_TECH.get() + "6463", "531013110");
        put(DirectionIIK.CONSTRUCTION_AND_TECH_ELECTRONICS.get() + "6465", "531013096");
    }};
    private String directionOfGroup;

    public IIKFourthCourseID(ArrayList<String> directionOfGroup) {
        this.directionOfGroup = directionOfGroup.get(1) + directionOfGroup.get(2);

    }

    public String getIdDirectionUser() {
        return map.get(directionOfGroup);
    }
}

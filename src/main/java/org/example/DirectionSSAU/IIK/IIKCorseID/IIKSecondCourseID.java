package org.example.DirectionSSAU.IIK.IIKCorseID;

import org.example.Telegram.Model.DirectionIIK;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IIKSecondCourseID {
    public Map<String, String> map = new HashMap<String, String>() {{
        put(DirectionIIK.PMI.get() + "6201", "799359428");
        put(DirectionIIK.PMI.get() + "6202", "799359452");
        put(DirectionIIK.PMI.get() + "6203", "799359514");
        put(DirectionIIK.PMI.get() + "6204", "799359533");
        put(DirectionIIK.PMI.get() + "6205", "799359534");
        put(DirectionIIK.FIIT.get() + "6201", "799360212");
        put(DirectionIIK.FIIT.get() + "6202", "799360214");
        put(DirectionIIK.FIIT.get() + "6203", "799360268");
        put(DirectionIIK.FIIT.get() + "6204", "799360269");
        put(DirectionIIK.IVT.get() + "6201", "531023227");
        put(DirectionIIK.IVT.get() + "6202", "531023237");
        put(DirectionIIK.IVT.get() + "6203", "531023666");
        put(DirectionIIK.IVT.get() + "6204", "531872797");
        put(DirectionIIK.IVT.get() + "6205", "799362723");
        put(DirectionIIK.RADIO_TECH.get() + "6262", "531293139");
        put(DirectionIIK.ELECTRONICS.get() + "6266", "531319560");
        put(DirectionIIK.BIO_TECH.get() + "6264", "531010822");
        put(DirectionIIK.PMF.get() + "6201", "799360924");
        put(DirectionIIK.PMF.get() + "6202", "799360928");
        put(DirectionIIK.LASER_TECH.get() + "6263", "531013107");
        put(DirectionIIK.CONSTRUCTION_AND_TECH_ELECTRONICS.get() + "6265", "531013093");
    }};
    private String directionOfGroup;

    public IIKSecondCourseID(ArrayList<String> directionOfGroup) {
        this.directionOfGroup = directionOfGroup.get(1) + directionOfGroup.get(2);

    }

    public String getIdDirectionUser() {
        return map.get(directionOfGroup);
    }
}

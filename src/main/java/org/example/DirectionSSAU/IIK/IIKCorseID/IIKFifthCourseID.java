package org.example.DirectionSSAU.IIK.IIKCorseID;

import org.example.Telegram.Model.DirectionIIK;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IIKFifthCourseID {
    public Map<String, String> map = new HashMap<String, String>() {{
        put(DirectionIIK.IBAS.get() + "6511", "531874010");
        put(DirectionIIK.IBAS.get() + "6512", "531874011");
        put(DirectionIIK.RADIO_ELECTRONICS_AND_COMPLEXES.get() + "6561", "531046380");
    }};
    private String directionOfGroup;

    public IIKFifthCourseID(ArrayList<String> directionOfGroup) {
        this.directionOfGroup = directionOfGroup.get(1) + directionOfGroup.get(2);

    }

    public String getIdDirectionUser() {
        return map.get(directionOfGroup);
    }
}

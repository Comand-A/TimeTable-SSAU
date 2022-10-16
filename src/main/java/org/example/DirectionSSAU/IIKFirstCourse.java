package org.example.DirectionSSAU;

import java.util.HashMap;
import java.util.Map;

public class IIKFirstCourse {
    public Map<String, String> map = new HashMap<String,String>(){{
        put("ПМИ1","755921059");
        put("ПМИ2","755921402");
        put("ПМИ3","755921408");
        put("ПМИ4","755921409");
        put("ПМИ5","755921609");
        put("ПМИ6","762577712");
        put("ФИИТ1","755922237");
        put("ФИИТ2","755922246");
        put("ФИИТ3","755922248");
        put("ФИИТ4","762577970");
        put("ИВТ1","531023128");
        put("ИВТ2","531023236");
        put("ИВТ3","531023665");
        put("ИВТ4","531872709");
        put("ИВТ5","755922552");
        put("ИВТ6","762577998");
        put("РАДИОТЕХНИКА","804399515");
        put("ЭЛЕКТРОНИКА И НАНОЭЛЕКТРОНИКА","804433186");
        put("БИОТЕХНИЧЕСКИЕ СИСТЕМЫ И ТЕХНОЛОГИИ1","804435060");
        put("БИОТЕХНИЧЕСКИЕ СИСТЕМЫ И ТЕХНОЛОГИИ2","804435212");
        put("ПМФ1","755922274");
        put("ПМФ2","755922277");
    }};
    private String directionOfGroup;

    public IIKFirstCourse(String direction, String numberGroup) {
        this.directionOfGroup=direction+numberGroup;

    }

    public String initializeDirectionUser() {
        return map.get(directionOfGroup);
    }


}

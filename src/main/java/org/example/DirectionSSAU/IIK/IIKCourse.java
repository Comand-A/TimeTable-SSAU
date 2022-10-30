package org.example.DirectionSSAU.IIK;

import org.example.Telegram.Model.DirectionIIK;
import org.example.Telegram.Model.Emoji;

import java.util.*;

public class IIKCourse {
    public Map<String, List<String>> coursesOfDirection = new HashMap() {{
        put("1", new ArrayList<>(Arrays.asList(DirectionIIK.FIIT.get(), DirectionIIK.PMF.get(), DirectionIIK.IVT.get(), DirectionIIK.PMI.get(),
                DirectionIIK.IBAS.get(), DirectionIIK.RADIO_TECH.get(), DirectionIIK.ELECTRONICS.get(), DirectionIIK.BIO_TECH.get())));

        put("2", new ArrayList<>(Arrays.asList(DirectionIIK.FIIT.get(), DirectionIIK.PMF.get(), DirectionIIK.IVT.get(), DirectionIIK.PMI.get(),
                DirectionIIK.IBAS.get(), DirectionIIK.RADIO_TECH.get(), DirectionIIK.ELECTRONICS.get(), DirectionIIK.BIO_TECH.get(),
                DirectionIIK.CONSTRUCTION_AND_TECH_ELECTRONICS.get(), DirectionIIK.LASER_TECH.get())));

        put("3", new ArrayList<>(Arrays.asList(DirectionIIK.FIIT.get(), DirectionIIK.PMF.get(), DirectionIIK.IVT.get(), DirectionIIK.PMI.get(),
                DirectionIIK.IBAS.get(), DirectionIIK.RADIO_TECH.get(), DirectionIIK.ELECTRONICS.get(), DirectionIIK.BIO_TECH.get(),
                DirectionIIK.CONSTRUCTION_AND_TECH_ELECTRONICS.get(), DirectionIIK.LASER_TECH.get())));

        put("4", new ArrayList<>(Arrays.asList(DirectionIIK.FIIT.get(), DirectionIIK.PMF.get(), DirectionIIK.IVT.get(), DirectionIIK.PMI.get(),
                DirectionIIK.IBAS.get(), DirectionIIK.RADIO_TECH.get(), DirectionIIK.ELECTRONICS.get(), DirectionIIK.BIO_TECH.get(),
                DirectionIIK.CONSTRUCTION_AND_TECH_ELECTRONICS.get(), DirectionIIK.LASER_TECH.get())));

        put("5", new ArrayList<>(Arrays.asList(DirectionIIK.RADIO_ELECTRONICS_AND_COMPLEXES.get(),DirectionIIK.IBAS.get())));
    }};
    private String course;

    public IIKCourse(String course) {
        this.course = course;

    }

    public boolean checkAvailabilityCoursesOfDirection() {
        return coursesOfDirection.containsKey(course);
    }

    public List<String> getDirectionUser() {
        return coursesOfDirection.get(course);
    }
}

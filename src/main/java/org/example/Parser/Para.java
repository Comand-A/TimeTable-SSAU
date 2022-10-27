package org.example.Parser;

import java.util.ArrayList;
import java.util.Arrays;

public class Para {
    ArrayList<String> timePara = new ArrayList<>(Arrays.asList("\n08:00 - 09:35: ", "\n09:45 - 11:20: ", "\n11:30 - 13:05: ", "\n13:30 - 15:05: ", "\n15:15 - 16:50: ","\n17:00 - 18:35: "));

    public Para(String para, String place, String teacher, String group, int numberPara) {
        this.numberPara = numberPara;
        this.para = para;
        this.place = place;
        this.teacher = teacher;
        this.group = group;
    }

    private String para, place, teacher, group;
    private int numberPara;

    @Override
    public String toString() {
        return String.format(timePara.get(numberPara) + "%s\nМесто: %s\nПреподаватель: %s\nГруппа: %s\n\n",
                this.para, this.place, this.teacher, this.group);
    }
}

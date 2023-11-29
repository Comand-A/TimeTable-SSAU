package org.example.Parser;

import java.util.ArrayList;
import java.util.Arrays;

public class Para {
    ArrayList<String> timePara = new ArrayList<>(Arrays.asList("\n08:00 - 09:35: ", "\n09:45 - 11:20: ", "\n11:30 - 13:05: ", "\n13:30 - 15:05: ", "\n15:15 - 16:50: ", "\n17:00 - 18:35: "));

    public Para(String para, String place, String teacher, String group, int numberPara) {
        this.numberPara = numberPara;
        this.para = para;
        this.place = place;
        this.teacher = teacher;
        if (!place.contains("online")) {
            this.group = group;
        } else
            this.group = "Вся группа";
    }

    private String para, place, teacher, group;
    private int numberPara;

    @Override
    public String toString() {
        if (!para.contains("Военная подготовка")){
        return String.format(timePara.get(numberPara) + "%s\nМесто: %s\nГруппа: %s\n\n",
                this.para, this.place, this.group);
        }else
            return String.format(timePara.get(numberPara) + "%s\nМесто: %s",
                    this.para, this.place);
    }
}

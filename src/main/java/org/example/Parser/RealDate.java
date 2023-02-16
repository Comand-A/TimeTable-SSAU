package org.example.Parser;

import java.io.IOException;
import java.util.Date;

public class RealDate {
    public long numberOfWeek;

    public void getNumberOfWeek(int realDate) throws IOException {
        long time = realDate;
        Date date = new Date(time*1000);
        if (date.getDay()==0)
            numberOfWeek = ((realDate - 1661630400 / 604800) + 1)-21;
        else
            numberOfWeek = ((realDate - 1661630400 / 604800) + 1)-22;
    }
}

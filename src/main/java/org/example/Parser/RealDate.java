package org.example.Parser;

import org.jsoup.nodes.Document;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class RealDate {
    private Document page;
    private String realDate = page.select("span[class=top-bar__text]").text();
    public RealDate(Document page) {
        this.page = page;
    }
    public long getNumberOfWeek() {
        long millisecondsInOneWeek = 604800000;
        Date begin = new Date(1661630400001L);
        Date realDateInDate;
        try {
            realDateInDate = new SimpleDateFormat("dd.MM.yyyy").parse(realDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        long numberOfWeek = (((realDateInDate.getTime() - begin.getTime()) / millisecondsInOneWeek) + 1);
        return numberOfWeek;
    }
}

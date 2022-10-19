package org.example.Parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RealDate {
    private Document page;
    private Element table;
    private String realDate;

    private void getPage() throws IOException {

        String url = "https://www.xn----7sbanbef0eh1ai8n.xn--p1ai/";
        page = Jsoup.parse(new URL(url), 5000);
        table = page.select("ul[class=text-center]").first();
        realDate = table.select("span").first().text();
    }

    public String getNumberOfWeek(long number) throws IOException {
        getPage();
        long millisecondsInOneWeek = 604800000;
        Date begin = new Date(1661630400001L);
        Date realDateInDate;
        try {
            realDateInDate = new SimpleDateFormat("dd.MM.yyyy").parse(realDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        long numberOfWeek = (((realDateInDate.getTime() - begin.getTime()) / millisecondsInOneWeek) + 1);

        return String.valueOf(numberOfWeek + number);
    }
}

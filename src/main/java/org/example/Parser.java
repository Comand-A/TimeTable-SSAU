package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Parser {
    public Document page;
    public Element table;
    public ArrayList<String> week, schedulePlace, dates, anotherDays, monday;
    public List<Day> timeTable;


    public Parser() {
        try {
            page = getPage();
            table = page.select("div[class=schedule__items]").first();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Document getPage() throws IOException {
        String url = "https://ssau.ru/rasp?groupId=755922237&selectedWeek=2&selectedWeekday=1";
        page = Jsoup.parse(new URL(url), 5000);
        return page;
    }

    private ArrayList<String> getSchedulePlace() {
        String cssQuery = "div[class=caption-text schedule__place]";
        Elements SchedulePlaces = table.select(cssQuery);
        schedulePlace = new ArrayList<>();
        for (Element Place : SchedulePlaces) {
            schedulePlace.add(Place.select(cssQuery).text());
        }
        return schedulePlace;
    }

    public ArrayList<String> getMonday() {
        String cssQuery = "div[class=schedule__item schedule__item_show]";
        String[] cssQueryArray = {"div[class=body-text schedule__discipline lesson-color lesson-color-type-","1]","2]","3]","4]"};
        Elements Mondays = table.select(cssQuery);
        week = new ArrayList<>();
        int i=0;
        for (Element Monday : Mondays) {
            if (!Monday.select(cssQuery).text().equals("")) {
                for (int j=1;j<5;j++){
                    week.add(Monday.select(cssQueryArray[0]+cssQueryArray[j]).text());
                    if (week.get(i).equals("")) {week.remove(i);}
                }
            }
            i++;
        }
        for (int n=0;n<5;n++){
            if (week.get(n).equals("")){
                week.set(n,"Нет пары");
            }
        }
        return week;
    }

    private ArrayList<String> getDate() {
        String cssQuery = "div[class=caption-text schedule__head-date]";
        Elements dates = table.select(cssQuery);
        this.dates = new ArrayList<>();
        for (Element date : dates) {
            this.dates.add(date.select(cssQuery).text() + '\n');
        }
        return this.dates;
    }

    public ArrayList<String> getAnotherDays() {
        String cssQuery = "div[class=schedule__item]";
        String[] cssQueryArray = {"div[class=body-text schedule__discipline lesson-color lesson-color-type-","1]","2]","3]","4]"};
        Elements allDay = table.select(cssQuery);
        anotherDays = new ArrayList<>();
        int i=0;
        for (Element day : allDay) {
            if (!day.select(cssQuery).text().equals("")) {
                for (int j=1;j<5;j++){
                    anotherDays.add(day.select(cssQueryArray[0]+cssQueryArray[j]).text());
                    if (anotherDays.get(i).equals("")) {anotherDays.remove(i);}
                }
            }
            i++;
        }
        for (int n=0;n<anotherDays.size();n++){
            if (anotherDays.get(n).equals("")){
                anotherDays.set(n,"Нет пары");
            }
        }
        return anotherDays;
    }

    public List<Day> getTimeTable() {
        monday = this.getMonday();
        dates = this.getDate();
        anotherDays = this.getAnotherDays();
        timeTable = new ArrayList<>(Collections.singletonList(new Day(dates.get(0), monday.get(0), monday.get(1), monday.get(2), monday.get(3), monday.get(4))));
        dates.remove(0);
        anotherDays.addAll(0, dates);
        for (int i = 0; i < 5; i++) {
            timeTable.add((new Day(anotherDays.get(i), anotherDays.get(i + 5), anotherDays.get(i + 10), anotherDays.get(i + 15), anotherDays.get(i + 20), anotherDays.get(i + 25))));
        }
        return timeTable;
    }

    public void Print() {
        Parser parser = new Parser();
        ArrayList<String> schedulePlace = parser.getSchedulePlace();
        timeTable = parser.getTimeTable();
        for (Day s : timeTable) {
            System.out.println(s);
        }
        System.out.println(schedulePlace);
    }
}
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
        Elements Mondays = table.select(cssQuery);
        week = new ArrayList<>();
        for (Element Monday : Mondays) {
            if (!Monday.select(cssQuery).text().equals("")) {
                week.add(Monday.select(cssQuery).text() + '\n');
            } else {
                week.add("Нет пары\n");
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
        Elements allDay = table.select(cssQuery);
        anotherDays = new ArrayList<>();
        for (Element day : allDay) {
            if (!day.select(cssQuery).text().equals("")) anotherDays.add(day.select(cssQuery).text() + "\n");
            else anotherDays.add("Нет пары\n");
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
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
    public ArrayList<String> week, schedulePlace, dates, anotherDays, monday, teachers, groups;
    public List<Day> timeTable;


    public Parser() {
        try {
            page = getPage();
            table = page.select("div[class=schedule__items]").first();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Document getPage() throws IOException {
        String url = "https://ssau.ru/rasp?groupId=755922237&selectedWeek=6&selectedWeekday=1";
        page = Jsoup.parse(new URL(url), 5000);
        return this.page;
    }
//private ArrayList<String> getCycle(){
//        return
//}
    private ArrayList<String> getSchedulePlace() {
        String[] cssQueryArray = {"div[class=schedule__item schedule__item_show]", "div[class=caption-text schedule__place]", "div[class=schedule__item]"};
        schedulePlace = new ArrayList<>();
        Elements schedulePlaceMonday = table.select(cssQueryArray[0]);
        Elements schedulePlaceAnotherDays = table.select(cssQueryArray[2]);
        for (Element place : schedulePlaceMonday) {
            schedulePlace.add(place.select(cssQueryArray[1]).text());
        }
        for (Element place : schedulePlaceAnotherDays) {
            schedulePlace.add(place.select(cssQueryArray[1]).text());
        }
        return this.schedulePlace;
    }

    private ArrayList<String> getGroups() {
        String[] cssQueryArray = {"div[class=schedule__item schedule__item_show]","span[class=caption-text]","div[class=schedule__item]"};
        Elements groupMonday = table.select(cssQueryArray[0]);
        Elements groupAnotherDays = table.select(cssQueryArray[2]);
        groups = new ArrayList<>();
        for (Element group : groupMonday) {
            if (!group.select(cssQueryArray[1]).text().equals("")) groups.add(group.select(cssQueryArray[1]).text());
            else groups.add("Вся группа");
        }
        for (Element group : groupAnotherDays) {
            if (!group.select(cssQueryArray[1]).text().equals("")) groups.add(group.select(cssQueryArray[1]).text());
            else groups.add("Вся группа");
        }
        return this.groups;
    }

    private ArrayList<String> getTeacher() {
        String[] cssQueryArray = {"div[class=schedule__item schedule__item_show]", "div[class=schedule__teacher]", "div[class=schedule__item]"};
        Elements teacherMonday = table.select(cssQueryArray[0]);
        Elements teacherAnotherDays = table.select(cssQueryArray[2]);
        teachers = new ArrayList<>();
        for (Element teacher : teacherMonday) {
            teachers.add(teacher.select(cssQueryArray[1]).text());
        }
        for (Element teacher : teacherAnotherDays) {
            teachers.add(teacher.select(cssQueryArray[1]).text());
        }
        return this.teachers;
    }

    private ArrayList<String> getMonday() {
        String cssQuery = "div[class=schedule__item schedule__item_show]";
        String[] cssQueryArray = {"div[class=body-text schedule__discipline lesson-color lesson-color-type-", "1]", "2]", "3]", "4]"};
        Elements Mondays = table.select(cssQuery);
        week = new ArrayList<>();
        int i = 0;
        for (Element Monday : Mondays) {
            if (!Monday.select(cssQuery).text().equals("")) {
                for (int j = 1; j <= 4; j++) {
                    if (!Monday.select(cssQueryArray[0] + cssQueryArray[j]).text().equals("")) {
                        week.add(Monday.select(cssQueryArray[0] + cssQueryArray[j]).text());
                    }
                }

            } else {
                week.add("Нет пары");
            }
            i++;
        }
        return this.week;
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

    private ArrayList<String> getAnotherDays() {
        String cssQuery = "div[class=schedule__item]";
        String[] cssQueryArray = {"div[class=body-text schedule__discipline lesson-color lesson-color-type-", "1]", "2]", "3]", "4]"};
        Elements allDay = table.select(cssQuery);
        anotherDays = new ArrayList<>();
        int i = 0;
        for (Element day : allDay) {
            if (!day.select(cssQuery).text().equals("")) {
                for (int j = 1; j < 5; j++) {
                    if (!day.select(cssQueryArray[0] + cssQueryArray[j]).text().equals("")) {
                        anotherDays.add(day.select(cssQueryArray[0] + cssQueryArray[j]).text());
                    }
                }
            } else {
                anotherDays.add("Нет пары");
            }
            i++;
        }
        return this.anotherDays;
    }

    private List<Day> getTimeTable() {
        monday = this.getMonday();
        dates = this.getDate();
        anotherDays = this.getAnotherDays();
        teachers = this.getTeacher();
        schedulePlace = this.getSchedulePlace();
        timeTable = new ArrayList<>(Collections.singletonList(new Day(dates.get(0),
                monday.get(0), schedulePlace.get(0), teachers.get(0),
                monday.get(1), schedulePlace.get(1), teachers.get(1),
                monday.get(2), schedulePlace.get(2), teachers.get(2),
                monday.get(3), schedulePlace.get(3), teachers.get(3),
                monday.get(4), schedulePlace.get(4), teachers.get(4))));
        dates.remove(0);
        anotherDays.addAll(0, dates);
        for (int i = 0; i < 5; i++) {
            timeTable.add((new Day(anotherDays.get(i),
                    anotherDays.get(i + 5), schedulePlace.get(i + 5), teachers.get(i + 5),
                    anotherDays.get(i + 10), schedulePlace.get(i + 10), teachers.get(i + 10),
                    anotherDays.get(i + 15), schedulePlace.get(i + 15), teachers.get(i + 15),
                    anotherDays.get(i + 20), schedulePlace.get(i + 20), teachers.get(i + 20),
                    anotherDays.get(i + 25), schedulePlace.get(i + 25), teachers.get(i + 25))));
        }
        return this.timeTable;
    }

    public void Print() {
        Parser parser = new Parser();
//        schedulePlace = getSchedulePlace();
//        teachers=getTeacher();
        timeTable = parser.getTimeTable();
        groups=getGroups();
        for (Day s : timeTable) {
            System.out.println(s);
        }
        //System.out.println(schedulePlace+"\n"+teachers);
    }
}
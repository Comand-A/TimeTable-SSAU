package org.example.Parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Parser {
    private Document page;
    private Element table;
    private ArrayList<String> schedulePlace, dates, anotherDays, monday, teachers, groups, volatileDataCollector, cssQueryList;
    private List<Day> timeTable;

    private String numberOfWeek;



    public Parser() {
        try {
            RealDate realDate = new RealDate();
            numberOfWeek = Long.toString(realDate.getNumberOfWeek());
            page = getPage();
            table = page.select("div[class=schedule__items]").first();
            getDate();
            getMonday();
            getAnotherDays();
            getSchedulePlace();
            getTeacher();
            getGroups();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Document getPage() throws IOException {
        String url = "https://ssau.ru/rasp?groupId=755922237&selectedWeek=" + numberOfWeek;
        page = Jsoup.parse(new URL(url), 5000);
        return this.page;
    }

    private ArrayList<String> getCycle(ArrayList<String> cssQueryList) {
        volatileDataCollector = new ArrayList<>();
        Elements countMonday = table.select((cssQueryList.get(0)));
        Elements countAnotherDays = table.select(cssQueryList.get(2));
        for (Element place : countMonday) {
            volatileDataCollector.add(place.select(cssQueryList.get(1)).text());
        }
        for (Element place : countAnotherDays) {
            volatileDataCollector.add(place.select(cssQueryList.get(1)).text());
        }
        return volatileDataCollector;
    }

    private void getSchedulePlace() {
        cssQueryList = new ArrayList<>(Arrays.asList("div[class=schedule__item schedule__item_show]", "div[class=caption-text schedule__place]", "div[class=schedule__item]"));
        schedulePlace = getCycle(cssQueryList);
    }


    private void getGroups() {
        cssQueryList = new ArrayList<>(Arrays.asList("div[class=schedule__item schedule__item_show]", "div[class=schedule__groups]", "div[class=schedule__item]"));
        groups = getCycle(cssQueryList);
        for (int i = 0; i < 30; i++) {
            if (groups.get(i).equals("")) groups.set(i, "Вся группа");
        }
    }

    private void getTeacher() {
        cssQueryList = new ArrayList<>(Arrays.asList("div[class=schedule__item schedule__item_show]", "div[class=schedule__teacher]", "div[class=schedule__item]"));
        teachers = getCycle(cssQueryList);
    }

    private void getMonday() {
        String cssQuery = "div[class=schedule__item schedule__item_show]";
        String[] cssQueryArray = {"div[class=body-text schedule__discipline lesson-color lesson-color-type-", "1]", "2]", "3]", "4]"};
        Elements Mondays = table.select(cssQuery);
        monday = new ArrayList<>();
        for (Element Monday : Mondays) {
            if (!Monday.select(cssQuery).text().equals("")) {
                for (int j = 1; j <= 4; j++) {
                    if (!Monday.select(cssQueryArray[0] + cssQueryArray[j]).text().equals("")) {
                        monday.add(Monday.select(cssQueryArray[0] + cssQueryArray[j]).text());
                    }
                }

            } else {
                monday.add("Нет пары");
            }
        }
    }

    private void getDate() {
        String cssQuery = "div[class=caption-text schedule__head-date]";
        Elements datesVolatile = table.select(cssQuery);
        dates = new ArrayList<>();
        for (Element date : datesVolatile) {
            dates.add(date.select(cssQuery).text() + '\n');
        }
    }

    private void getAnotherDays() {
        String[] cssQueryArray = {"div[class=schedule__item]","div[class=body-text schedule__discipline lesson-color lesson-color-type-", "1]", "2]", "3]", "4]"};
        Elements allDay = table.select(cssQueryArray[0]);
        anotherDays = new ArrayList<>();
        for (Element day : allDay) {
            if (!day.select(cssQueryArray[0]).text().equals("")) {
                for (int j = 2; j < 5; j++) {
                    if (!day.select(cssQueryArray[1] + cssQueryArray[j]).text().equals("")) {
                        anotherDays.add(day.select(cssQueryArray[1] + cssQueryArray[j]).text());
                    }
                }
            } else {
                anotherDays.add("Нет пары");
            }
        }
    }

    private List<Day> getTimeTable() {
        timeTable = new ArrayList<>(Collections.singletonList(new Day(dates.get(0),
                monday.get(0), schedulePlace.get(0), teachers.get(0), groups.get(0),
                monday.get(1), schedulePlace.get(1), teachers.get(1), groups.get(1),
                monday.get(2), schedulePlace.get(2), teachers.get(2), groups.get(2),
                monday.get(3), schedulePlace.get(3), teachers.get(3), groups.get(3),
                monday.get(4), schedulePlace.get(4), teachers.get(4), groups.get(4))));
        dates.remove(0);
        anotherDays.addAll(0, dates);
        for (int i = 0; i < 5; i++) {
            timeTable.add((new Day(anotherDays.get(i),
                    anotherDays.get(i + 5), schedulePlace.get(i + 5), teachers.get(i + 5), groups.get(i + 5),
                    anotherDays.get(i + 10), schedulePlace.get(i + 10), teachers.get(i + 10), groups.get(i + 10),
                    anotherDays.get(i + 15), schedulePlace.get(i + 15), teachers.get(i + 15), groups.get(i + 15),
                    anotherDays.get(i + 20), schedulePlace.get(i + 20), teachers.get(i + 20), groups.get(i + 20),
                    anotherDays.get(i + 25), schedulePlace.get(i + 25), teachers.get(i + 25), groups.get(i + 25))));
        }
        return this.timeTable;
    }

    public void Print(){
        Parser parser = new Parser();
        timeTable = parser.getTimeTable();
        for (Day s : timeTable) {
            System.out.println(s);
        }
    }
}
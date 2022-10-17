package org.example.Parser;

import org.example.Telegram.Model.Emoji;
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
    private ArrayList<String> schedulePlace, dates, fullWeekDays, teachers, groups, volatileDataCollector, cssQueryList;
    private List<Day> timeTable;
    private String idDirection, numberOfWeek;


    public Parser(String idDirection, long numberOfWeek) {
        this.idDirection = idDirection;
        RealDate realDate = new RealDate();
        try {
            this.numberOfWeek = realDate.getNumberOfWeek(numberOfWeek);
            page = getPage();
            table = page.select("div[class=schedule__items]").first();

        } catch (IOException e) {
            e.printStackTrace();
        }
        getDate();
        getFullWeekDays();
        getSchedulePlace();
        getTeacher();
        getGroups();
    }


    private Document getPage() throws IOException {
        String url = "https://ssau.ru/rasp?groupId=" + idDirection + "&selectedWeek=" + numberOfWeek + "&selectedWeekday=7";
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

    private void getDate() {
        ArrayList<String> nameDayWeek = new ArrayList<>(Arrays.asList(Emoji.UNDERAGE.get() + "Понедельник" + Emoji.UNDERAGE.get(),
                Emoji.RULER.get() + "Вторник" + Emoji.RULER.get(), Emoji.MEMO.get() + "Среда" + Emoji.MEMO.get(),
                Emoji.BALLOON.get() + "Четверг" + Emoji.BALLOON.get(), Emoji.TADA.get() + "Пятница" + Emoji.TADA.get(), Emoji.PAINTBRUSH.get() + "Суббота" + Emoji.PAINTBRUSH.get()));
        String cssQuery = "div[class=caption-text schedule__head-date]";
        Elements datesVolatile = table.select(cssQuery);
        dates = new ArrayList<>();
        int i = 0;
        for (Element date : datesVolatile) {
            dates.add(nameDayWeek.get(i) + " (" + date.select(cssQuery).text() + ')' + '\n');
            i++;
        }
    }

    private void getFullWeekDays() {
        String[] cssQueryArray = {"div[class=schedule__item]", "div[class=body-text schedule__discipline lesson-color lesson-color-type-", "1]", "2]", "3]", "4]"};
        Elements allDay = table.select(cssQueryArray[0]);
        fullWeekDays = new ArrayList<>();
        for (Element day : allDay) {
            if (!day.select(cssQueryArray[0]).text().equals("")) {
                for (int j = 2; j < 5; j++) {
                    if (!day.select(cssQueryArray[1] + cssQueryArray[j]).text().equals("")) {
                        fullWeekDays.add(day.select(cssQueryArray[1] + cssQueryArray[j]).text());
                    }
                }
            } else {
                fullWeekDays.add(Emoji.CROSS.get());
            }
        }
    }

    private List<Day> getTimeTable() {
        timeTable = new ArrayList<>();
        if ((fullWeekDays.get(5).equals(Emoji.CROSS.get())) && (fullWeekDays.get(11).equals(Emoji.CROSS.get()))
                && (fullWeekDays.get(17).equals(Emoji.CROSS.get())) && (fullWeekDays.get(23).equals(Emoji.CROSS.get()))
                && (fullWeekDays.get(29).equals(Emoji.CROSS.get()))) {
            dates.set(5, "");
        }
        if (fullWeekDays.size() <= 30) {
            for (int i = 0; i < 6; i++) {
                fullWeekDays.add(Emoji.CROSS.get());
                schedulePlace.add("");
                teachers.add("");
                groups.add("");
            }
        }
        for (int i = 0; i < 6; i++) {
            ArrayList<String> paraFirst = new ArrayList<>();
            ArrayList<String> paraSecond = new ArrayList<>();
            ArrayList<String> paraThird = new ArrayList<>();
            ArrayList<String> paraFourth = new ArrayList<>();
            ArrayList<String> paraFifth = new ArrayList<>();
            ArrayList<String> paraSixth = new ArrayList<>();
            paraFirst.add(String.valueOf(new Para(fullWeekDays.get(i), schedulePlace.get(i), teachers.get(i), groups.get(i), 0)));
            paraSecond.add(String.valueOf(new Para(fullWeekDays.get(i + 6), schedulePlace.get(i + 6), teachers.get(i + 6), groups.get(i + 6), 1)));
            paraThird.add(String.valueOf(new Para(fullWeekDays.get(i + 12), schedulePlace.get(i + 12), teachers.get(i + 12), groups.get(i + 12), 2)));
            paraFourth.add(String.valueOf(new Para(fullWeekDays.get(i + 18), schedulePlace.get(i + 18), teachers.get(i + 18), groups.get(i + 18), 3)));
            paraFifth.add(String.valueOf(new Para(fullWeekDays.get(i + 24), schedulePlace.get(i + 24), teachers.get(i + 24), groups.get(i + 24), 4)));
            paraSixth.add(String.valueOf(new Para(fullWeekDays.get(i + 30), schedulePlace.get(i + 30), teachers.get(i + 30), groups.get(i + 30), 5)));
            if (fullWeekDays.get(i + 30).equals(Emoji.CROSS.get())) {
                paraSixth.set(0, "");
                if (fullWeekDays.get(i + 24).equals(Emoji.CROSS.get())) {
                    paraFifth.set(0, "");
                    if (fullWeekDays.get(i + 18).equals(Emoji.CROSS.get())) {
                        paraFourth.set(0, "");
                        if (fullWeekDays.get(i + 12).equals(Emoji.CROSS.get())) {
                            paraThird.set(0, "");
                            if (fullWeekDays.get(i + 6).equals(Emoji.CROSS.get())) {
                                paraSecond.set(0, "");
                                if (fullWeekDays.get(i).equals(Emoji.CROSS.get())) {
                                    paraFirst.set(0, "");
                                }
                            }
                        }
                    }
                }
            }
            timeTable.add(new Day(dates.get(i), paraFirst, paraSecond, paraThird, paraFourth, paraFifth, paraSixth));
        }
        return this.timeTable;
    }

    public List<Day> Print() {
        timeTable = getTimeTable();
        return timeTable;
    }
}
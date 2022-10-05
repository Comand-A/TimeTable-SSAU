package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Parser {
    public static Document Page;
    public static Element table;
    public static ArrayList<String> Week,SchedulePlace,Dates,AnotherDays,Monday,TimeTable,helper;
    static {
        try {
            Page = getPage();
            table = Page.select("div[class=schedule__items]").first();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Document getPage() throws IOException {
        String url = "https://ssau.ru/rasp?groupId=755922237&selectedWeek=6&selectedWeekday=1";
        Page = Jsoup.parse(new URL(url), 5000);
        return Page;
    }
    private static ArrayList<String> getSchedulePlace(){
        Elements SchedulePlaces = table.select("div[class=caption-text schedule__place]");
        SchedulePlace = new ArrayList<>();
        for (Element Place : SchedulePlaces){
            SchedulePlace.add(Place.select("div[class=caption-text schedule__place]").text());
        }
        return SchedulePlace;
    }
    public static ArrayList<String> getMonday(){
        Elements Mondays = table.select("div[class=schedule__item schedule__item_show]");
        Week = new ArrayList<>();
        for (Element Monday : Mondays) {
            if (!Monday.select("div[class=schedule__item schedule__item_show]").text().equals("")){
                Week.add(Monday.select("div[class=schedule__item schedule__item_show]").text()+'\n');
            }else {
                Week.add("Нет пары\n");
            }
        }
        return Week;
    }
    private static ArrayList<String> getDate(){
        Elements dates = table.select("div[class=caption-text schedule__head-date]");
        Dates = new ArrayList<>();
        for (Element date : dates){
            Dates.add(date.select("div[class=caption-text schedule__head-date]").text()+'\n');
        }
        return Dates;
    }
    public static ArrayList<String> getAnotherDays(){
        Elements allDay = table.select("div[class=schedule__item]");
        AnotherDays = new ArrayList<>();
        for (Element Day : allDay) {
            if (!Day.select("div[class=schedule__item]").text().equals("")){
            AnotherDays.add(Day.select("div[class=schedule__item]").text()+"\n");
            }else {
                AnotherDays.add("Нет пары\n");
            }
        }
        return AnotherDays;
    }

    public static ArrayList<String> getTimeTable(){
        Monday = getMonday();
        Dates = getDate();
        AnotherDays = getAnotherDays();
        TimeTable = new ArrayList<>();
        helper = new ArrayList<>();
        Monday.add(0,Dates.get(0));
        Dates.remove(0);
        AnotherDays.addAll(0,Dates);
        TimeTable.add(Monday.toString());
        for (int j=0;j<5;j++){
            for (int i=j;i<AnotherDays.size();i+=5){
                helper.add(AnotherDays.get(i));
            }
            TimeTable.add(helper.toString());
            helper.clear();
        }
        return TimeTable;
    }
    public static void main(String[] args){
        SchedulePlace = getSchedulePlace();
        TimeTable = getTimeTable();
        for (String s : TimeTable) {
            System.out.println(s+"\n");
        }
        System.out.println(SchedulePlace);
        }
    }
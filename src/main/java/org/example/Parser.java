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
        Elements SchedulePlaces = table.select("div[class=caption-text scheduleplace]");
        ArrayList<String> SchedulePlace = new ArrayList<>();
        for (Element Place : SchedulePlaces){
            SchedulePlace.add(Place.select("div[class=caption-text scheduleplace]").text());
        }
        return SchedulePlace;
    }
    public static ArrayList<String> getMonday(){
        Elements Mondays = table.select("div[class=schedule__item schedule__item_show]");
        ArrayList<String> Week = new ArrayList<>();
        for (Element Monday : Mondays) {
            if (!Monday.select("div[class=schedule__item schedule__item_show]").text().equals("")){
                Week.add(Monday.select("div[class=schedule__item schedule__item_show]").text());
            }else {
                Week.add("Нет пары");
            }
        }
        return Week;
    }
    private static ArrayList<String> getDate(){
        Elements dates = table.select("div[class=caption-text schedule__head-date]");
        ArrayList<String> Dates = new ArrayList<>();
        for (Element date : dates){
            Dates.add(date.select("div[class=caption-text schedule__head-date]").text());
        }
        return Dates;
    }
    public static ArrayList<String> getAnotherDays(){
        Elements alls = table.select("div[class=schedule__item]");
        ArrayList<String> AnotherDays = new ArrayList<>();
        for (Element all : alls) {
            if (!all.select("div[class=schedule__item]").text().equals("")){
            AnotherDays.add(all.select("div[class=schedule__item]").text());
            }else {
                AnotherDays.add("Нет пары");
            }
        }
        return AnotherDays;
    }

    public static ArrayList<String> getTimeTable(){
        ArrayList<String> Monday = getMonday();
        ArrayList<String> Dates = getDate();
        ArrayList<String> AnotherDays = getAnotherDays();
        ArrayList<String> TimeTable = new ArrayList<>();
        ArrayList<String> helper = new ArrayList<>();
        Monday.add(0,Dates.get(0));
        Dates.remove(0);
        for (int i=0;i<5;i++){
            AnotherDays.add(i,Dates.get(i));
        }
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
        ArrayList<String> SchedulePlace = getSchedulePlace();
        ArrayList<String> TimeTable = getTimeTable();
        for (String s : TimeTable) {
            System.out.println(s);
        }
        System.out.println(SchedulePlace);
        }
    }
package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.SplittableRandom;

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
        String numberWeek = "2";
        String url = "https://ssau.ru/rasp?groupId=755922237&selectedWeek=6&selectedWeekday=1";
        Page = Jsoup.parse(new URL(url), 5000);
        return Page;
    }
    public static ArrayList<String> getMonday() throws IOException {
        Elements Mondays = table.select("div[class=schedule__item schedule__item_show]");
        ArrayList<String> Week = new ArrayList<>();
        for (Element Monday : Mondays) {
            Week.add(Monday.select("div[class=schedule__item schedule__item_show]").text());
        }
        return Week;
    }
    private static ArrayList<String> getDate() throws IOException{
        Elements dates = table.select("div[class=caption-text schedule__head-date]");
        ArrayList<String> Dates = new ArrayList<>();
        for (Element date : dates){
            Dates.add(date.select("div[class=caption-text schedule__head-date]").text());
        }
        return Dates;
    }
    public static ArrayList<String> getAnotherDays() throws IOException {
        Elements alls = table.select("div[class=schedule__item]");
        ArrayList<String> AnotherDays = new ArrayList<>();
        for (Element all : alls) {
            AnotherDays.add(all.select("div[class=schedule__item]").text());
        }
        return AnotherDays;
    }

    public static ArrayList<String> getTimeTable() throws IOException {
        ArrayList<String> Monday = getMonday();
        ArrayList<String> Dates = getDate();
        ArrayList<String> AnotherDays = getAnotherDays();
        ArrayList<String> TimeTable = new ArrayList<String>();
        ArrayList<String> neformal = new ArrayList<String>();
        Monday.add(0,Dates.get(0));
        Dates.remove(0);
        for (int i=0;i<5;i++){
            AnotherDays.add(i,Dates.get(i));
        }
        TimeTable.add(Monday.toString());
        for (int j=0;j<5;j++){
            for (int i=j;i<AnotherDays.size();i+=5){
                neformal.add(AnotherDays.get(i));
            }
            TimeTable.add(neformal.toString());
            neformal.clear();
        }
        return TimeTable;
    }
    public static void main(String[] args) throws IOException {
        ArrayList<String> TimeTable = getTimeTable();
        for (int i = 0; i < TimeTable.size(); i++){
            System.out.println(TimeTable.get(i));
        }
        }
    }
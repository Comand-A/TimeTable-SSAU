package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

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
    public static String[] getMonday() throws IOException {
        Elements Mondays = table.select("div[class=schedule__item schedule__item_show]");
        int n = 0;
        String[] Week = new String[5];
        for (Element Monday : Mondays) {
            Week[n] = Monday.select("div[class=schedule__item schedule__item_show]").text();
            n += 1;
        }
        return Week;
    }
    private static String[] getDate() throws IOException{
        Elements dates = table.select("div[class=caption-text schedule__head-date]");
        String[] Dates = new String[6];
        int i = 0;
        for (Element date : dates){
            Dates[i] = date.select("div[class=caption-text schedule__head-date]").text();
            i+=1;
        }
        return Dates;
    }
    public static String[] getAnotherDays() throws IOException {
        Elements alls = table.select("div[class=schedule__item]");
        int k = 0;
        for (Element all : alls) {
            k += 1;
        }
        String[] AnotherDays = new String[k];
        k = 0;
        for (Element all : alls) {
            AnotherDays[k] = all.select("div[class=schedule__item]").text();
            k += 1;
        }
        return AnotherDays;
    }

    public static String[][] getTimeTable() throws IOException {
        String[] Monday = getMonday();
        String[][] TimeTable = new String[6][6];
        for (int i = 0; i < 5; i++) {
            TimeTable[i+1][0] = Monday[i];
        }
        String[] Dates = getDate();
        for (int i = 0; i < 6; i++) {
            TimeTable[0][i] = Dates[i];
        }
        int a=0;
        String[] AnotherDays = getAnotherDays();
        for (int i = 1; i < 6; i++){
            for(int j = 1; j < 6; j++){
                TimeTable[i][j]=AnotherDays[a];
                a+=1;
            }
        }
        return TimeTable;
    }
    public static void main(String[] args) throws IOException {
        String[][] TimeTable = getTimeTable();
        for (int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                if (TimeTable[j][i].equals("")){
                    System.out.println("-");
                }
                else{
                    System.out.println(TimeTable[j][i]);
                }
            }
            System.out.println("\n");
        }
    }
}
package org.example.Parser;

import java.util.ArrayList;

public class Day {
    public Day(String date, ArrayList<String> paraFirst, ArrayList<String> placeSecond, ArrayList<String> placeThird, ArrayList<String> placeFourth, ArrayList<String> placeFifth) {
        this.date = date;
        this.paraFirst = paraFirst;
        this.placeSecond = placeSecond;
        this.placeThird = placeThird;
        this.placeFourth = placeFourth;
        this.placeFifth = placeFifth;
    }

    private ArrayList<String> paraFirst, placeSecond, placeThird, placeFourth, placeFifth;
    private String date;

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s", this.date, this.paraFirst.get(0), this.placeSecond.get(0), this.placeThird.get(0), this.placeFourth.get(0), this.placeFifth.get(0));
    }
}


package org.example.Parser;

import java.util.ArrayList;

public class Day {
    public Day(String date, ArrayList<String> paraFirst, ArrayList<String> paraSecond, ArrayList<String> paraThird, ArrayList<String> paraFourth, ArrayList<String> paraFifth,ArrayList<String> paraSixth) {
        this.date = date;
        this.paraFirst = paraFirst;
        this.paraSecond = paraSecond;
        this.paraThird = paraThird;
        this.paraFourth = paraFourth;
        this.paraFifth = paraFifth;
        this.paraSixth = paraSixth;
    }

    private ArrayList<String> paraFirst, paraSecond, paraThird, paraFourth, paraFifth, paraSixth;
    private String date;

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s %s", this.date, this.paraFirst.get(0), this.paraSecond.get(0), this.paraThird.get(0), this.paraFourth.get(0), this.paraFifth.get(0), this.paraSixth.get(0));
    }
}


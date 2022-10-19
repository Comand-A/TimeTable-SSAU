package org.example.Parser;

import java.util.ArrayList;

public class Day {
    public Day(String date, String paraFirst, String paraSecond, String paraThird, String paraFourth, String paraFifth, String paraSixth) {
        this.date = date;
        this.paraFirst = paraFirst;
        this.paraSecond = paraSecond;
        this.paraThird = paraThird;
        this.paraFourth = paraFourth;
        this.paraFifth = paraFifth;
        this.paraSixth = paraSixth;
    }

    private String paraFirst, paraSecond, paraThird, paraFourth, paraFifth, paraSixth;
    private String date;

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s %s", this.date, this.paraFirst, this.paraSecond, this.paraThird, this.paraFourth, this.paraFifth, this.paraSixth);
    }
}


package org.example.Parser;

public class Day {
    public Day(String data,
               String paraFirst, String placeFirst, String teacherFirst, String groupFirst,
               String paraSecond, String placeSecond, String teacherSecond, String groupSecond,
               String paraThird, String placeThird, String teacherThird, String groupThird,
               String paraFourth, String placeFourth, String teacherFourth, String groupFourth,
               String paraFifth, String placeFifth, String teacherFifth, String groupFifth)
    {
        this.data = data;
        this.paraFirst = paraFirst;
        this.placeFirst = placeFirst;
        this.teacherFirst = teacherFirst;
        this.groupFirst = groupFirst;
        this.paraSecond = paraSecond;
        this.placeSecond = placeSecond;
        this.teacherSecond = teacherSecond;
        this.groupSecond = groupSecond;
        this.paraThird = paraThird;
        this.placeThird = placeThird;
        this.teacherThird = teacherThird;
        this.groupThird = groupThird;
        this.paraFourth = paraFourth;
        this.placeFourth = placeFourth;
        this.teacherFourth = teacherFourth;
        this.groupFourth = groupFourth;
        this.paraFifth = paraFifth;
        this.placeFifth = placeFifth;
        this.teacherFifth = teacherFifth;
        this.groupFifth = groupFifth;
    }

    private String data, paraFirst, placeFirst, teacherFirst, groupFirst,
            paraSecond, placeSecond, teacherSecond, groupSecond,
            paraThird, placeThird, teacherThird, groupThird,
            paraFourth, placeFourth, teacherFourth, groupFourth,
            paraFifth, placeFifth, teacherFifth, groupFifth;

    @Override
    public String toString() {
        return String.format("Дата:%s\nПара1:%s\nМесто:%s\nУчитель:%s\nГруппа:%s\n\nПара2:%s\nМесто:%s\nУчитель:%s\nГруппа:%s\n\nПара3:%s" +
                        "\nМесто:%s\nУчитель:%s\nГруппа:%s\n\nПара4:%s\nМесто:%s\nУчитель:%s\nГруппа:%s\n\nПара5:%s\nМесто:%s\nУчитель:%s\nГруппа:%s\n\n",
                this.data, this.paraFirst, this.placeFirst, this.teacherFirst,this.groupFirst,
                this.paraSecond, this.placeSecond, this.teacherSecond,this.groupSecond,
                this.paraThird, this.placeThird, this.teacherThird,this.groupThird,
                this.paraFourth, this.placeFourth, this.teacherFourth,this.groupFourth,
                this.paraFifth, this.placeFifth, this.teacherFifth,this.groupFifth);
    }
}


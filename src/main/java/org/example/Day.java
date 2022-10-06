package org.example;

public class Day {
    public Day(String data, String paraFirst, String placeFirst, String teacherFirst,
               String paraSecond, String placeSecond, String teacherSecond,
               String paraThird, String placeThird, String teacherThird,
               String paraFourth, String placeFourth, String teacherFourth,
               String paraFifth, String placeFifth, String teacherFifth) {
        this.data = data;
        this.paraFirst = paraFirst;
        this.placeFirst = placeFirst;
        this.teacherFirst = teacherFirst;
        this.paraSecond = paraSecond;
        this.placeSecond = placeSecond;
        this.teacherSecond = teacherSecond;
        this.paraThird = paraThird;
        this.placeThird = placeThird;
        this.teacherThird = teacherThird;
        this.paraFourth = paraFourth;
        this.placeFourth = placeFourth;
        this.teacherFourth = teacherFourth;
        this.paraFifth = paraFifth;
        this.placeFifth = placeFifth;
        this.teacherFifth = teacherFifth;
    }

    private String data, paraFirst,placeFirst,teacherFirst,
                         paraSecond,placeSecond,teacherSecond,
                         paraThird,placeThird,teacherThird,
                         paraFourth,placeFourth,teacherFourth,
                         paraFifth,placeFifth,teacherFifth;

    @Override
    public String toString() {
        return String.format("Дата:%s\nПара1:%s\nМесто:%s\nУчитель:%s\n\nПара2:%s\nМесто:%s\nУчитель:%s\n\nПара3:%s" +
                        "\nМесто:%s\nУчитель:%s\n\nПара4:%s\nМесто:%s\nУчитель:%s\n\nПара5:%s\nМесто:%s\nУчитель:%s\n\n",
                this.data,this.paraFirst,this.placeFirst ,this.teacherFirst ,
                this.paraSecond ,this.placeSecond ,this.teacherSecond,
                this.paraThird,this.placeThird ,this.teacherThird,
                this.paraFourth ,this.placeFourth ,this.teacherFourth,
                this.paraFifth ,this.placeFifth ,this.teacherFifth);
    }
    }


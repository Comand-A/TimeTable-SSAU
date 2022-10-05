package org.example;

public class Day {
    private String data, para1, para2, para3, para4, para5;

    @Override
    public String toString() {
        return String.format("Дата:%s\nПара1:%s\nПара2:%s\nПара3:%s\nПара4:%s\nПара5:%s\n", this.data,this.para1, this.para2, this.para3, this.para4, this.para5);
    }

    public Day(String data, String para1, String para2, String para3, String para4, String para5) {
        this.data = data;
        this.para1 = para1;
        this.para2 = para2;
        this.para3 = para3;
        this.para4 = para4;
        this.para5 = para5;
    }


}

package org.example.Telegram.Service;

import org.example.Parser.Day;

import java.util.List;

public class Person {
    public int course = 0;
    public List<Day> week;
    public List<Day> weekMemory;
    public long numberOfWeek = 0;
    public String[] idDirection = new String[2];
    public String[] idDirectionMemory;
    public Person(){
        idDirection[0] = "";
        idDirection[1] = "";
    }
    public Person(List<Day> w, String[] m){
        idDirection[0] = "";
        idDirection[1] = "";
        weekMemory = w;
        idDirection = m;
    }
}

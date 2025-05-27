package fr.cs.groupJ.myFoodora.util;

import java.util.Calendar;

public class Date {
    private int day;
    private int month;
    private int year;
    private String time;

    public Date(int day, int month, int year, String time) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.time = time;
    }
    public Date() {
        Calendar calendar = Calendar.getInstance();
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
        this.month = calendar.get(Calendar.MONTH) + 1; 
        this.year = calendar.get(Calendar.YEAR);
        this.time = String.format("%02d:%02d", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
    }

    // ===== Getters and Setters =====

    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;
    }
    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    // ===== Methods =====
    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d %s", day, month, year, time);
    }
}
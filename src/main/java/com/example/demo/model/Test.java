package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Test {
    private String date;
    private String time;

    public Test () {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Test{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}

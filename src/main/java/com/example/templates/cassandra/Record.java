package com.example.templates.cassandra;

import java.util.List;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class Record {
    @PrimaryKey
    private String date;
    private List<String> days;

    public Record(String date, List<String> days) {
        this.date = date;
        this.days = days;
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }
}

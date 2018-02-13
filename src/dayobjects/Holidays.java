/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dayobjects;

/**
 *
 * @author Rolo
 */
public class Holidays {
    private int month;
    private int date;
    private int year;
    private String eventName;
    private String colorName;

    public Holidays(int month, int date, int year, String eventName, String colorName) {
        this.month = month;
        this.date = date;
        this.year = year;
        this.eventName = eventName;
        this.colorName = colorName;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    
    
    
}

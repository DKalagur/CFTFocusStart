package ru.cft.team.Model;

import java.util.ArrayList;
import java.util.Arrays;

public class Item {
    private int id;
    private String brand;
    private String model;
    private int year;
    private String bodyType;
    public static final String[] COLUMNS = {"ID", "BRAND", "MODEL", "BODY TYPE", "YEAR"};
    public static final int START_YEAR = 1900;
    public static final int END_YEAR = getCurrentYear();
    public static ArrayList<String> bodyTypes = new ArrayList<>(Arrays.asList("седан", "хэтчбек", "универсал", "купе", "родстер",
            "кабриолет", "внедорожник", "кроссовер", "пикап", "фургон", "минивэн"));

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public static int getCurrentYear() {
        java.util.Calendar calendar = java.util.Calendar.getInstance(java.util.TimeZone.getDefault(), java.util.Locale.getDefault());
        calendar.setTime(new java.util.Date());
        return calendar.get(java.util.Calendar.YEAR);
    }
}
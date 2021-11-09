package com.example.myapplication.data.model;

public class Ad
{
    private final int id;
    private final String title;
    private final int flat_house;
    private final int sell_rent;
    private final int number_of_rooms;
    private final String description;
    private final float size;
    private final String date_start;
    private final String date_end;
    private final float price;
    private final String location;

    public Ad(int id, String title, int flat_house, int sell_rent, int number_of_rooms, String description, float size, String date_start, String date_end, float price, String location) {
        this.id = id;
        this.title = title;
        this.flat_house = flat_house;
        this.sell_rent = sell_rent;
        this.number_of_rooms = number_of_rooms;
        this.description = description;
        this.size = size;
        this.date_start = date_start;
        this.date_end = date_end;
        this.price = price;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getFlat_house() {
        return flat_house;
    }

    public int getSell_rent() {
        return sell_rent;
    }

    public int getNumber_of_rooms() {
        return number_of_rooms;
    }

    public String getDescription() {
        return description;
    }

    public float getSize() {
        return size;
    }

    public String getDate_start() {
        return date_start;
    }

    public String getDate_end() {
        return date_end;
    }

    public float getPrice() {
        return price;
    }

    public String getLocation() {
        return location;
    }
}

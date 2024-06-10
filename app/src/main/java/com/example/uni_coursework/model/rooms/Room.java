package com.example.uni_coursework.model.rooms;

import java.io.Serializable;
import java.util.List;

public class Room implements Serializable {
    private String title;
    private String beds;
    private Integer cost;
    private String booker;
    private List<String> images;
    private Boolean isBooked;

    public Room(
            String title,
            String beds,
            Integer cost,
            List<String> images,
            Boolean isBooked,
            String booker
    ) {
        this.title = title;
        this.beds = beds;
        this.cost = cost;
        this.images = images;
        this.isBooked = isBooked;
        this.booker = booker;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBeds() {
        return beds;
    }

    public void setBeds(String beds) {
        this.beds = beds;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getBooker() {
        return booker;
    }

    public void setBooker(String booker) {
        this.booker = booker;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Boolean getBooked() {
        return isBooked;
    }

    public void setBooked(Boolean booked) {
        isBooked = booked;
    }
}
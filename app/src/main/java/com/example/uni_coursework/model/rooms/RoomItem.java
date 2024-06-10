package com.example.uni_coursework.model.rooms;

import android.graphics.Bitmap;

import java.util.List;

public class RoomItem {

    private String id;
    private String title;
    private String beds;
    private Integer cost;
    private List<Bitmap> images;
    private String booker;
    private Boolean isBooked;

    public RoomItem(
            String id,
            String title,
            String beds,
            Integer cost,
            List<Bitmap> images,
            String booker,
            Boolean isBooked
    ) {
        this.id = id;
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

    public List<Bitmap> getImages() {
        return images;
    }

    public void setImages(List<Bitmap> images) {
        this.images = images;
    }

    public Boolean getBooked() {
        return isBooked;
    }

    public void setBooked(Boolean booked) {
        isBooked = booked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

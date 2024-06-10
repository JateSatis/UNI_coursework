package com.example.uni_coursework.screens.search;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

public class RoomImages {
    public RoomImages(String roomId, ArrayList<Bitmap> images) {
        this.roomId = roomId;
        this.images = images;
    }

    private String roomId;
    private ArrayList<Bitmap> images;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public ArrayList<Bitmap> getImages() {
        return images;
    }

    public void setImages(ArrayList<Bitmap> images) {
        this.images = images;
    }

    public void addImage(Bitmap bitmap) {
        this.images.add(bitmap);
    }
}

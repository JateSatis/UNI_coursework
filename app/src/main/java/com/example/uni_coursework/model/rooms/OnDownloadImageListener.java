package com.example.uni_coursework.model.rooms;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;

public interface OnDownloadImageListener {
    void onDownloadImage(HashMap<String, ArrayList<Bitmap>> bitmapHashMap);
}

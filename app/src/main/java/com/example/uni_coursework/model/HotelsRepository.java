package com.example.uni_coursework.model;

import com.google.firebase.firestore.FirebaseFirestore;

public class HotelsRepository {

    private final FirebaseFirestore db;

    public HotelsRepository(FirebaseFirestore db) {
        this.db = db;
    }

}

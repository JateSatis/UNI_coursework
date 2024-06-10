package com.example.uni_coursework.model;

import com.google.firebase.firestore.FirebaseFirestore;

public class BookingsRepository {

    private final FirebaseFirestore db;

    public BookingsRepository(FirebaseFirestore db) {
        this.db = db;
    }

}

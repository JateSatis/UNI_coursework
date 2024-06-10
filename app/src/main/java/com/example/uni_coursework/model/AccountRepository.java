package com.example.uni_coursework.model;

import com.google.firebase.firestore.FirebaseFirestore;

public class AccountRepository {

    private final FirebaseFirestore db;

    public AccountRepository(FirebaseFirestore db) {
        this.db = db;
    }

}

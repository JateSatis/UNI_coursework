package com.example.uni_coursework.model;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AccountRepository {

    private final FirebaseFirestore db;

    public AccountRepository(FirebaseFirestore db) {
        this.db = db;
    }

    public void createUser(String uid) {
        User user = new User();
        db.collection("users").document(uid).set(user);
    }

    public void getUser(String uid, OnSuccessListener<DocumentSnapshot> onSuccessListener) {
        db.collection("users").document(uid).get().addOnSuccessListener(onSuccessListener);
    }

}

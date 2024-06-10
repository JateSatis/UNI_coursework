package com.example.uni_coursework.model;

import com.example.uni_coursework.model.rooms.RoomsRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

public class RepositoriesProvider {

    public static AuthRepository getAuthRepository(FirebaseAuth auth) {
        return new AuthRepository(auth);
    }

    public static AccountRepository getAccountRepository(FirebaseFirestore db) {
        return new AccountRepository(db);
    }

    public static HotelsRepository getHotelsRepository(FirebaseFirestore db) {
        return new HotelsRepository(db);
    }

    public static RoomsRepository getRoomsRepository(FirebaseFirestore db, FirebaseStorage storage) {
        return new RoomsRepository(db, storage);
    }

    public static BookingsRepository getBookingsRepository(FirebaseFirestore db) {
        return new BookingsRepository(db);
    }
}
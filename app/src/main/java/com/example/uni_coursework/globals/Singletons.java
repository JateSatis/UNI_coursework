package com.example.uni_coursework.globals;

import android.annotation.SuppressLint;

import com.example.uni_coursework.model.AccountRepository;
import com.example.uni_coursework.model.AuthRepository;
import com.example.uni_coursework.model.BookingsRepository;
import com.example.uni_coursework.model.HotelsRepository;
import com.example.uni_coursework.model.RepositoriesProvider;
import com.example.uni_coursework.model.rooms.RoomsRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

public class Singletons {

    private static final FirebaseAuth auth = FirebaseAuth.getInstance();
    @SuppressLint("StaticFieldLeak")
    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static final FirebaseStorage storage = FirebaseStorage.getInstance();

    public static AuthRepository getAuthRepository() {
        return RepositoriesProvider.getAuthRepository(auth);
    }

    public static AccountRepository getAccountRepository() {
        return RepositoriesProvider.getAccountRepository(db);
    }

    public static HotelsRepository getHotelsRepository() {
        return RepositoriesProvider.getHotelsRepository(db);
    }

    public static RoomsRepository getRoomsRepository() {
        return RepositoriesProvider.getRoomsRepository(db, storage);
    }

    public static BookingsRepository getBookingsRepository() {
        return RepositoriesProvider.getBookingsRepository(db);
    }
}
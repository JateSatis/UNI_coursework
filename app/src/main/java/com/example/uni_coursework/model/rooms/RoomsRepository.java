package com.example.uni_coursework.model.rooms;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.example.uni_coursework.model.rooms.OnUploadCompleteListener;

import java.util.Date;
import java.util.List;

public class RoomsRepository {

    private final FirebaseFirestore db;
    private final FirebaseStorage storage;
    private int imagesCount = 0;

    public RoomsRepository(FirebaseFirestore db, FirebaseStorage storage) {
        this.db = db;
        this.storage = storage;
    }

    public void addRoom(String title, String beds, Integer cost, String booker, List<String> images, Boolean isBooked, OnSuccessListener<DocumentReference> onSuccessListener) {
        Room room = new Room(title, beds, cost, images, isBooked, booker);
        db.collection("rooms")
                .add(room)
                .addOnSuccessListener(onSuccessListener);
    }

    public void saveImages(String roomId, @NonNull List<Uri> imagesUris, List<String> imagesNames, OnUploadCompleteListener onUploadCompleteListener) {
        for (int i = 0; i < imagesUris.size(); i++) {
            Date now = new Date();
            String fileName = imagesNames.get(i);
            StorageReference storageReference = storage.getReference(roomId + "/" + fileName);
            storageReference.putFile(imagesUris.get(i)).addOnSuccessListener(taskSnapshot -> {
                imagesCount += 1;
                if (imagesCount == imagesUris.size()) {
                    imagesCount = 0;
                    onUploadCompleteListener.onUploadComplete();
                }
            });
        }
    }

}

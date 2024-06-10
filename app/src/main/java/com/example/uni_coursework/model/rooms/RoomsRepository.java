package com.example.uni_coursework.model.rooms;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.uni_coursework.screens.search.RoomImages;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RoomsRepository {

    private final FirebaseFirestore db;
    private final FirebaseStorage storage;
    private int imagesCount = 0;
    private int roomCount = 0;

    private int allImageAmount = 0;

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

    public void getRooms(OnDownloadRoomsListener onDownloadRoomsListener) {
        db.collection("rooms")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {


                    // Get all rooms according to their ids
                    HashMap<String, Room> rooms = new HashMap<>();
                    for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                        String id = snapshot.getId();
                        Room room = snapshot.toObject(Room.class);
                        rooms.put(id, room);
                    }

                    onDownloadRoomsListener.onDownloadRooms(rooms);
                });


    }

    public void getPhotos(HashMap<String, Room> rooms, OnDownloadImageListener onDownloadImageListener) throws IOException {
        allImageAmount = 0;
        for (String roomId : rooms.keySet()) {
            allImageAmount += rooms.get(roomId).getImages().size();
        }

        imagesCount = 0;
        HashMap<String, ArrayList<Bitmap>> allImages = new HashMap<>();
        for (String roomId : rooms.keySet()) {
            Room currentRoom = rooms.get(roomId);
            List<String> currentRoomImagesNames = currentRoom.getImages();

            for (String imageName : currentRoomImagesNames) {
                File localFile = File.createTempFile("tempfile", ".jpg");
                StorageReference storageReference = storage.getReference(roomId + "/" + imageName);

                storageReference.getFile(localFile)
                        .addOnSuccessListener(taskSnapshot -> {
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            if (allImages.containsKey(roomId)) {
                                allImages.get(roomId).add(bitmap);
                            } else {
                                ArrayList<Bitmap> newRoomImages = new ArrayList<>();
                                newRoomImages.add(bitmap);
                                allImages.put(roomId, newRoomImages );
                            }
                            imagesCount += 1;
                            if (imagesCount == allImageAmount) {
                                onDownloadImageListener.onDownloadImage(allImages);
                            }
                        });
            }
        }

    }

    public void bookRoom(String roomId, String userId, OnSuccessListener<Void> onSuccessListener) {
        Map<String, Object> update = new HashMap<>();
        update.put("booker", userId);
        update.put("booked", true);
        db.collection("rooms").document(roomId).update(update)
                .addOnSuccessListener(onSuccessListener);
    }

}

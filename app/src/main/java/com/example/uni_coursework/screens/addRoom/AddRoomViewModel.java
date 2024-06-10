package com.example.uni_coursework.screens.addRoom;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

import com.example.uni_coursework.globals.Singletons;
import com.example.uni_coursework.model.AuthRepository;
import com.example.uni_coursework.model.rooms.OnUploadCompleteListener;
import com.example.uni_coursework.model.rooms.RoomsRepository;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import java.util.List;

public class AddRoomViewModel extends ViewModel {

    private RoomsRepository roomsRepository = Singletons.getRoomsRepository();
    private AuthRepository authRepository = Singletons.getAuthRepository();

    void addRoom(String title, String beds, Integer cost, String booker, List<String> images, Boolean isBooked, OnSuccessListener<DocumentReference> onSuccessListener) {
        roomsRepository.addRoom(title, beds, cost, booker, images, isBooked, onSuccessListener);
    }

    void uploadImages(String roomId, List<Uri> imagesUris, List<String> imagesNames, OnUploadCompleteListener onUploadCompleteListener) {
        roomsRepository.saveImages(roomId, imagesUris, imagesNames, onUploadCompleteListener);
    }

    public String getUserUid() {
        return authRepository.getCurrentUserUid();
    }

}

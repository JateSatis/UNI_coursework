package com.example.uni_coursework.screens.bookings;

import android.graphics.Bitmap;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.uni_coursework.globals.Singletons;
import com.example.uni_coursework.model.AccountRepository;
import com.example.uni_coursework.model.AuthRepository;
import com.example.uni_coursework.model.HotelsRepository;
import com.example.uni_coursework.model.User;
import com.example.uni_coursework.model.rooms.OnDownloadImageListener;
import com.example.uni_coursework.model.rooms.OnDownloadRoomsListener;
import com.example.uni_coursework.model.rooms.Room;
import com.example.uni_coursework.model.rooms.RoomsRepository;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class BookingsViewModel extends ViewModel {

    private RoomsRepository roomsRepository = Singletons.getRoomsRepository();
    private AuthRepository authRepository = Singletons.getAuthRepository();
    private AccountRepository accountRepository = Singletons.getAccountRepository();

    private MutableLiveData<HashMap<String, Room>> _rooms = new MutableLiveData<>();
    public MutableLiveData<HashMap<String, Room>> rooms = _rooms;

    private MutableLiveData<HashMap<String, ArrayList<Bitmap>>> _bitmapsHashMap = new MutableLiveData<>();
    public MutableLiveData<HashMap<String, ArrayList<Bitmap>>> bitmapsHashMap = _bitmapsHashMap;

    private MutableLiveData<Boolean> _admin = new MutableLiveData<>();
    public MutableLiveData<Boolean> admin = _admin;

    public void getRooms() {
        roomsRepository.getRooms(onDownloadRoomListener);
    }

    public void getPhotos(HashMap<String, Room> rooms) {
        try {
            roomsRepository.getPhotos(rooms, onDownloadImageListener);
        } catch (Exception e) {

        }
    }

    public void bookRoom(String roomId) {
        roomsRepository.bookRoom(roomId, getCurrentUserUid(), unused -> {
            roomsRepository.getRooms(onDownloadRoomListener);
        });
    }

    public String getCurrentUserUid() {
        return authRepository.getCurrentUserUid();
    }

    public void isAdmin() {
        accountRepository.getUser(authRepository.getCurrentUserUid(), documentSnapshot -> {
            User user = documentSnapshot.toObject(User.class);
            _admin.postValue(user.getAdmin());
        });
    }

    public void logOut() {
        authRepository.signOut();
    }

    private OnDownloadRoomsListener onDownloadRoomListener = roomItems -> {
        _rooms.postValue(roomItems);
    };

    private OnDownloadImageListener onDownloadImageListener = bitmapsHashMap -> {
        _bitmapsHashMap.postValue(bitmapsHashMap);
    };

}

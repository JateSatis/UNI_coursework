package com.example.uni_coursework.screens.search;

import android.graphics.Bitmap;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.uni_coursework.globals.Singletons;
import com.example.uni_coursework.model.AuthRepository;
import com.example.uni_coursework.model.HotelsRepository;
import com.example.uni_coursework.model.rooms.OnDownloadImageListener;
import com.example.uni_coursework.model.rooms.OnDownloadRoomsListener;
import com.example.uni_coursework.model.rooms.Room;
import com.example.uni_coursework.model.rooms.RoomItem;
import com.example.uni_coursework.model.rooms.RoomsRepository;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SearchViewModel extends ViewModel {

    private RoomsRepository roomsRepository = Singletons.getRoomsRepository();
    private AuthRepository authRepository = Singletons.getAuthRepository();

    private MutableLiveData<HashMap<String, Room>> _rooms = new MutableLiveData<>();
    public MutableLiveData<HashMap<String, Room>> rooms = _rooms;

    private MutableLiveData<HashMap<String, ArrayList<Bitmap>>> _bitmapsHashMap = new MutableLiveData<>();
    public MutableLiveData<HashMap<String, ArrayList<Bitmap>>> bitmapsHashMap = _bitmapsHashMap;

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

    private OnDownloadRoomsListener onDownloadRoomListener = roomItems -> {
        _rooms.postValue(roomItems);
    };

    private OnDownloadImageListener onDownloadImageListener = bitmapsHashMap -> {
        _bitmapsHashMap.postValue(bitmapsHashMap);
    };
}

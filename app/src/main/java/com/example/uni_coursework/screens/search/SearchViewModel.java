package com.example.uni_coursework.screens.search;

import androidx.lifecycle.ViewModel;

import com.example.uni_coursework.globals.Singletons;
import com.example.uni_coursework.model.HotelsRepository;
import com.example.uni_coursework.model.rooms.RoomsRepository;

public class SearchViewModel extends ViewModel {

    private HotelsRepository hotelsRepository;
    private RoomsRepository roomsRepository;

    public SearchViewModel() {
        this.hotelsRepository = Singletons.getHotelsRepository();
        this.roomsRepository = Singletons.getRoomsRepository();
    }

}

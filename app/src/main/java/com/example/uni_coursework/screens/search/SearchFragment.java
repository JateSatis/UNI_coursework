package com.example.uni_coursework.screens.search;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.uni_coursework.databinding.FragmentSearchBinding;
import com.example.uni_coursework.model.rooms.Room;
import com.example.uni_coursework.model.rooms.RoomItem;
import com.example.uni_coursework.utils.FragmentWithTopNav;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class SearchFragment extends FragmentWithTopNav {
    private FragmentSearchBinding binding;

    private SearchViewModel viewModel = new SearchViewModel();

    HashMap<String, Room> rooms;
    HashMap<String, ArrayList<Bitmap>> bitmapHashMap;
    ArrayList<RoomItem> roomItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);

        RoomsAdapter roomsAdapter = new RoomsAdapter(onBookRoomListener, viewModel.getCurrentUserUid());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());

        binding.roomItemsRecyclerContainer.setAdapter(roomsAdapter);
        binding.roomItemsRecyclerContainer.setLayoutManager(linearLayoutManager);

        viewModel.getRooms();

        viewModel.rooms.observe(getViewLifecycleOwner(), rooms -> {
            this.rooms = rooms;
            viewModel.getPhotos(rooms);
        });

        binding.searchBarButton.setOnClickListener(view -> {
            String input = binding.searchBar.getText().toString();
            roomsAdapter.setRoomItems(roomItems.stream().filter(roomItem -> roomItem.getTitle().contains(input)).collect(Collectors.toList()));
        });

        viewModel.bitmapsHashMap.observe(getViewLifecycleOwner(), bitmapHashMap -> {
            this.bitmapHashMap = bitmapHashMap;
            roomItems = new ArrayList<>();

            for (String roomId : rooms.keySet()) {
                ArrayList<Bitmap> images = bitmapHashMap.get(roomId);
                Room room = rooms.get(roomId);
                RoomItem roomItem = new RoomItem(
                        roomId,
                        room.getTitle(),
                        room.getBeds(),
                        room.getCost(),
                        images,
                        room.getBooker(),
                        room.getBooked()
                );

                roomItems.add(roomItem);
            }

            binding.searchProgressBar.setVisibility(View.INVISIBLE);

            String currentUserUid = viewModel.getCurrentUserUid();
            roomsAdapter.setRoomItems(roomItems.stream().filter(roomItem -> !roomItem.getBooked()).collect(Collectors.toList()));
        });

        return binding.getRoot();
    }

    private OnBookRoomListener onBookRoomListener = roomId -> {
        viewModel.bookRoom(roomId);
    };
}
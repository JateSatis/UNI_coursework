package com.example.uni_coursework.screens.bookings;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.uni_coursework.R;
import com.example.uni_coursework.databinding.FragmentBookingsBinding;
import com.example.uni_coursework.model.rooms.Room;
import com.example.uni_coursework.model.rooms.RoomItem;
import com.example.uni_coursework.screens.search.OnBookRoomListener;
import com.example.uni_coursework.screens.search.RoomsAdapter;
import com.example.uni_coursework.utils.FragmentWithTopNav;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BookingsFragment extends FragmentWithTopNav {
    private FragmentBookingsBinding binding;

    private BookingsViewModel viewModel = new BookingsViewModel();

    private NavController navController;

    HashMap<String, Room> rooms;
    HashMap<String, ArrayList<Bitmap>> bitmapHashMap;
    ArrayList<RoomItem> roomItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBookingsBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(requireView());

        RoomsAdapter roomsAdapter = new RoomsAdapter(onBookRoomListener, viewModel.getCurrentUserUid());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());

        binding.bookingsRecyclerContainer.setAdapter(roomsAdapter);
        binding.bookingsRecyclerContainer.setLayoutManager(linearLayoutManager);

        viewModel.getRooms();

        viewModel.isAdmin();

        viewModel.admin.observe(getViewLifecycleOwner(), admin -> {
            if (admin) binding.bookingsLaunchAddRoomButton.setVisibility(View.VISIBLE);
        });

        viewModel.rooms.observe(getViewLifecycleOwner(), rooms -> {
            this.rooms = rooms;
            viewModel.getPhotos(rooms);
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

            binding.bookingsProgressBar.setVisibility(View.INVISIBLE);

            String currentUserUid = viewModel.getCurrentUserUid();
            roomsAdapter.setRoomItems(roomItems.stream().filter(roomItem -> Objects.equals(roomItem.getBooker(), currentUserUid)).collect(Collectors.toList()));
        });

        binding.bookingsLaunchAddRoomButton.setOnClickListener(_view -> {
            navController.navigate(R.id.action_bookingsFragment_to_addRoomFragment);
        });

        binding.logoutButton.setOnClickListener(view1 -> {
            viewModel.logOut();
            findTopNavController().navigate(R.id.welcomeFragment);
        });
    }

    private OnBookRoomListener onBookRoomListener = roomId -> {
        viewModel.bookRoom(roomId);
    };
}
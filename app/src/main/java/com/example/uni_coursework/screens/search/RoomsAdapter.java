package com.example.uni_coursework.screens.search;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uni_coursework.databinding.ItemRoomBinding;
import com.example.uni_coursework.model.rooms.RoomItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RoomsAdapter extends RecyclerView.Adapter<RoomHolder> {

    private List<RoomItem> roomItems = new ArrayList<>();
    private OnBookRoomListener onBookRoomListener;
    private String currentUserUid;

    public RoomsAdapter(OnBookRoomListener onBookRoomListener, String currentUserUid) {
        this.onBookRoomListener = onBookRoomListener;
        this.currentUserUid = currentUserUid;
    }

    public void setRoomItems(List<RoomItem> rooms) {
        this.roomItems = rooms;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RoomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemRoomBinding binding = ItemRoomBinding.inflate(inflater, parent, false);

        return new RoomHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomHolder holder, int position) {
        ItemRoomBinding binding = holder.binding;

        binding.roomItemProgressBar.setVisibility(View.INVISIBLE);
        binding.roomItemBookButton.setVisibility(View.VISIBLE);

        RoomItem roomItem = roomItems.get(position);
        List<Bitmap> imagesBitmaps = roomItem.getImages();

        RoomItemImageViewPager roomItemImageViewPager = new RoomItemImageViewPager(holder.itemView.getContext(), imagesBitmaps);

        binding.roomItemTitle.setText(roomItem.getTitle());
        binding.roomItemBeds.setText(roomItem.getBeds());
        binding.roomItemPrice.setText(roomItem.getCost().toString());

        if (Objects.equals(currentUserUid, roomItem.getBooker())) {
            binding.roomItemBookButton.setVisibility(ViewGroup.INVISIBLE);
        }

        binding.roomItemBookButton.setOnClickListener(view -> {
            onBookRoomListener.onBookListener(roomItem.getId());
            view.setVisibility(ViewGroup.INVISIBLE);
            binding.roomItemProgressBar.setVisibility(View.VISIBLE);
        });

        binding.roomItemViewPager.setAdapter(roomItemImageViewPager);
    }

    @Override
    public int getItemCount() {
        return roomItems.size();
    }
}

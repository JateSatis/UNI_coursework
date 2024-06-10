package com.example.uni_coursework.screens.search;

import androidx.recyclerview.widget.RecyclerView;

import com.example.uni_coursework.databinding.ItemRoomBinding;

public class RoomHolder extends RecyclerView.ViewHolder {

    public ItemRoomBinding binding;

    public RoomHolder(ItemRoomBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
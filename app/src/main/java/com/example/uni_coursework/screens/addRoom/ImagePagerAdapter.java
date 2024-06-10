package com.example.uni_coursework.screens.addRoom;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.uni_coursework.R;
import com.example.uni_coursework.databinding.ItemRoomImageBinding;

import java.util.List;

interface OnRoomImageListener {
    void onClick();
}

public class ImagePagerAdapter extends PagerAdapter {

    private List<Uri> images;
    private LayoutInflater inflater;
    private Context context;
    private OnRoomImageListener onRoomImageListener;

    public ImagePagerAdapter(Context context, List<Uri> images, OnRoomImageListener onRoomImageListener) {
        this.context = context;
        this.images = images;
        this.onRoomImageListener = onRoomImageListener;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ItemRoomImageBinding binding = ItemRoomImageBinding.inflate(inflater, container, false);
        Glide.with(context)
                .load(images.get(position))
                .into(binding.itemRoomImage);
        container.addView(binding.getRoot());

        binding.itemRoomImage.setOnClickListener(view -> {
            onRoomImageListener.onClick();
        });

        return binding.getRoot();
    }

    void addImage(Uri imageUri) {
        images.add(imageUri);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
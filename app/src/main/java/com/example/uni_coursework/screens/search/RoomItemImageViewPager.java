package com.example.uni_coursework.screens.search;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.uni_coursework.R;
import com.example.uni_coursework.databinding.ItemRoomImageBinding;

import java.util.List;

public class RoomItemImageViewPager extends PagerAdapter {

    private List<Bitmap> images;
    private LayoutInflater inflater;
    private Context context;

    public RoomItemImageViewPager(Context context, List<Bitmap> images) {
        this.context = context;
        this.images = images;
        Log.d("pager", images.size() + "");
        inflater = LayoutInflater.from(context);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ItemRoomImageBinding binding = ItemRoomImageBinding.inflate(inflater, container, false);
        Glide.with(context)
                .load(images.get(position))
                .into(binding.itemRoomImage);
        container.addView(binding.getRoot());

        return binding.getRoot();
    }

    void addImage(Bitmap imageBitmap) {
        images.add(imageBitmap);
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
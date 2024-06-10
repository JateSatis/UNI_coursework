package com.example.uni_coursework.screens.bookings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.uni_coursework.R;
import com.example.uni_coursework.databinding.FragmentBookingsBinding;

public class BookingsFragment extends Fragment {
    private FragmentBookingsBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBookingsBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(requireView());

        binding.bookingsLaunchAddRoomButton.setOnClickListener(_view -> {
            navController.navigate(R.id.action_bookingsFragment_to_addRoomFragment);
        });
    }
}
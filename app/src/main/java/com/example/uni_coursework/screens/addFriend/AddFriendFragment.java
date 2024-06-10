package com.example.uni_coursework.screens.addFriend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.example.uni_coursework.R;
import com.example.uni_coursework.databinding.FragmentAddFriendBinding;

public class AddFriendFragment extends Fragment {
    private FragmentAddFriendBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddFriendBinding.inflate(inflater, container, false);

        binding.goBackToTimerScreen.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.searchFragment));

        return binding.getRoot();
    }
}
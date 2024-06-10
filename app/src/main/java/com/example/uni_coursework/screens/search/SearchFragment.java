package com.example.uni_coursework.screens.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.fragment.NavHostFragment;

import com.example.uni_coursework.R;
import com.example.uni_coursework.databinding.FragmentSearchBinding;
import com.example.uni_coursework.utils.FragmentWithTopNav;

public class SearchFragment extends FragmentWithTopNav {
    private FragmentSearchBinding binding;

    private SearchViewModel viewModel = new SearchViewModel();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);

        binding.launchAddFriendScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SearchFragment.this).navigate(R.id.action_timerFragment_to_addFriendFragment);
            }
        });

        binding.launchSettingsScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findTopNavController().navigate(R.id.action_tabsFragment_to_settingsFragment);
            }
        });

        binding.launchAccountScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findTopNavController().navigate(R.id.action_tabsFragment_to_accountFragment);
            }
        });

        return binding.getRoot();
    }
}
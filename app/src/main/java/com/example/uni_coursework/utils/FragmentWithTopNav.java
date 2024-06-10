package com.example.uni_coursework.utils;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.uni_coursework.R;

public class FragmentWithTopNav extends Fragment {

    protected NavController findTopNavController() {
        NavHostFragment topLevelHost = (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.mainGraphContainer);
        return topLevelHost.getNavController();
    }

}

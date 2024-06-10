package com.example.uni_coursework.screens.welcome;

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
import com.example.uni_coursework.databinding.FragmentWelcomeBinding;

public class WelcomeFragment extends Fragment {
    private FragmentWelcomeBinding binding;
    private NavController navController;
    private WelcomeViewModel viewModel = new WelcomeViewModel();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentWelcomeBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        navController = Navigation.findNavController(view);

        binding.launchSignIn.setOnClickListener(_view -> {
            navController.navigate(R.id.action_welcomeFragment_to_signInFragment);
        });

        binding.launchSignUp.setOnClickListener(_view -> {
            navController.navigate(R.id.action_welcomeFragment_to_signUpFragment);
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (viewModel.isSigneIn()) {
            navController.navigate(R.id.tabsFragment);
        }
    }
}

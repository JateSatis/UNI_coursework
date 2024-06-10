package com.example.uni_coursework.screens.signUp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.uni_coursework.R;
import com.example.uni_coursework.databinding.FragmentSignUpBinding;
import com.example.uni_coursework.utils.HasProgressBar;
import com.example.uni_coursework.utils.ResultsEnum;

public class SignUpFragment extends Fragment implements HasProgressBar {

    private FragmentSignUpBinding binding;
    private SignUpViewModel viewModel = new SignUpViewModel();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(inflater, container, false);

        viewModel.getSignUpResult().observe(getViewLifecycleOwner(), result -> {
            updateProgressBar(result);
            if (result == ResultsEnum.SUCCESS) {
                NavHostFragment.findNavController(SignUpFragment.this).navigate(R.id.action_signUpFragment_to_tabsFragment);
            }
        });

        binding.launchSignInScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(SignUpFragment.this).navigate(R.id.action_signUpFragment_to_signInFragment);
            }
        });

        binding.launchCreateAccountScreen.setOnClickListener(view -> {
            String email = binding.emailInput.getText().toString();
            String password = binding.passwordInput.getText().toString();
            viewModel.signUp(email, password);
        });

        return binding.getRoot();
    }

    @Override
    public void updateProgressBar(ResultsEnum result) {
        int progressBarId = R.id.signUpProgressBar;
        for (int i = 0; i < binding.getRoot().getChildCount(); i++) {
            View child = binding.getRoot().getChildAt(i);
            child.setVisibility(View.INVISIBLE);
        }
        if (result == ResultsEnum.SUCCESS) {
            for (int i = 0; i < binding.getRoot().getChildCount(); i++) {
                View child = binding.getRoot().getChildAt(i);
                if (child.getId() != progressBarId) {
                    child.setVisibility(View.INVISIBLE);
                }
            }
        } else if (result == ResultsEnum.PENDING) {
            ProgressBar progressBar = binding.getRoot().findViewById(progressBarId);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            for (int i = 0; i < binding.getRoot().getChildCount(); i++) {
                View child = binding.getRoot().getChildAt(i);
                if (child.getId() != progressBarId) {
                    child.setVisibility(View.INVISIBLE);
                }
            }
        }
    }
}
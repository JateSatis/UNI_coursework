package com.example.uni_coursework.screens.signIn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.uni_coursework.R;
import com.example.uni_coursework.databinding.FragmentSignInBinding;
import com.example.uni_coursework.utils.HasProgressBar;
import com.example.uni_coursework.utils.ResultsEnum;

public class SignInFragment extends Fragment implements HasProgressBar {

    private FragmentSignInBinding binding;
    private SignInViewModel viewModel = new SignInViewModel();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSignInBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.getSignInResult().observe(getViewLifecycleOwner(), result -> {
            updateProgressBar(result);
            if (result == ResultsEnum.SUCCESS) {
                NavHostFragment.findNavController(this).navigate(R.id.action_signInFragment_to_tabsFragment);
            }
        });

        binding.goBackToSignUpScreen.setOnClickListener(view1 -> {
            NavHostFragment.findNavController(SignInFragment.this).navigate(R.id.action_signInFragment_to_signUpFragment);
        });

        binding.signIn.setOnClickListener(v -> {
            String email = binding.emailInput.getText().toString();
            String password = binding.passwordInput.getText().toString();
            viewModel.signIn(email, password);
        });
    }

    @Override
    public void updateProgressBar(ResultsEnum result) {
        int progressBarId = R.id.signInProgressBar;
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
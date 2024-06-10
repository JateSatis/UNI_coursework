package com.example.uni_coursework.screens.welcome;

import androidx.lifecycle.ViewModel;

import com.example.uni_coursework.globals.Singletons;
import com.example.uni_coursework.model.AuthRepository;

public class WelcomeViewModel extends ViewModel {

    private AuthRepository authRepository = Singletons.getAuthRepository();

    Boolean isSigneIn() {
        return authRepository.isSignedIn();
    }

}

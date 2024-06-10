package com.example.uni_coursework.screens.signIn;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.uni_coursework.globals.Singletons;
import com.example.uni_coursework.model.AuthRepository;
import com.example.uni_coursework.utils.ResultsEnum;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SignInViewModel extends ViewModel {
    private final AuthRepository authRepository;
    private final MutableLiveData<ResultsEnum> _signInResult = new MutableLiveData<>();

    public MutableLiveData<ResultsEnum> getSignInResult() {
        return _signInResult;
    }
    public SignInViewModel() {
        this.authRepository = Singletons.getAuthRepository();
    }

    public void signIn(String email, String password) {
        _signInResult.postValue(ResultsEnum.PENDING);
        authRepository.signIn(email, password, task -> {
            if (task.isComplete()) {
                _signInResult.postValue(ResultsEnum.SUCCESS);
            } else if (task.isCanceled()) {
                _signInResult.postValue(ResultsEnum.FAILURE);
            }
        });
    }
}
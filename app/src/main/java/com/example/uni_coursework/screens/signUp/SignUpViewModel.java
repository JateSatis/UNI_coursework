package com.example.uni_coursework.screens.signUp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.uni_coursework.globals.Singletons;
import com.example.uni_coursework.model.AccountRepository;
import com.example.uni_coursework.model.AuthRepository;
import com.example.uni_coursework.utils.ResultsEnum;

public class SignUpViewModel extends ViewModel {
    private final AuthRepository authRepository;
    private final AccountRepository accountRepository = Singletons.getAccountRepository();
    private final MutableLiveData<ResultsEnum> _signUpResult = new MutableLiveData<>();

    public MutableLiveData<ResultsEnum> getSignUpResult() {
        return _signUpResult;
    }
    public SignUpViewModel() {
        this.authRepository = Singletons.getAuthRepository();
    }

    public void signUp(String email, String password) {
        _signUpResult.postValue(ResultsEnum.PENDING);
        authRepository.signUp(email, password, task -> {
            if (task.isComplete()) {
                accountRepository.createUser(authRepository.getCurrentUserUid());
                _signUpResult.postValue(ResultsEnum.SUCCESS);
            } else if (task.isCanceled()) {
                _signUpResult.postValue(ResultsEnum.FAILURE);
            }
        });
    }
}

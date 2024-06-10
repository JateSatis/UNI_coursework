package com.example.uni_coursework.model;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthRepository {

    private FirebaseAuth auth;
    public AuthRepository(FirebaseAuth auth) {
        this.auth = auth;
    }


    public String getCurrentUserUid() {
        return auth.getCurrentUser().getUid();
    }

    public void signIn(String email, String password, OnCompleteListener<AuthResult> onCompletionListener) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(onCompletionListener);
    }

    public void signUp(String email, String password, OnCompleteListener<AuthResult> onCompletionListener) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(onCompletionListener);
    }

    public void signOut() {
        auth.signOut();
    }

    public boolean isSignedIn() {
        return auth.getCurrentUser() != null;
    }
}

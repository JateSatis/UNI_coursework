package com.example.uni_coursework.model;

import java.io.Serializable;

public class User implements Serializable {

    private Boolean admin;

    public User() {
        this.admin = false;
    }

    public User(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}

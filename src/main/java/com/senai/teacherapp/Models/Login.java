package com.senai.teacherapp.Models;

import java.util.ArrayList;

public class Login {
    String user;
    String password;

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public Login(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public Login() {
    }
}

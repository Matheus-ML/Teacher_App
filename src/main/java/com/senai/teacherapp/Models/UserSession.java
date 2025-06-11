package com.senai.teacherapp.Models;

public class UserSession {
    private int id;
    private String name;

    public UserSession(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
}

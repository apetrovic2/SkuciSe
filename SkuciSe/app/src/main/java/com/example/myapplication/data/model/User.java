package com.example.myapplication.data.model;

public class User
{
    private final int id;
    private final String name;
    private final String username;
    private final String password;
    private final String email;
    public User(int id, String username, String password, String name, String email)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}

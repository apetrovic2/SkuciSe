package com.example.myapplication.data.model;

public class User
{
    private final int id;
    private final String name;
    private final String username;
    private final String hash;
    private final String salt;
    private final String email;
    public User(int id, String username, String hash, String salt, String name, String email)
    {
        this.id = id;
        this.username = username;
        this.hash = hash;
        this.salt = salt;
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

    public String getHash() { return hash; }

    public String getSalt() { return salt; }

    public String getEmail() {
        return email;
    }
}

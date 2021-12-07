package com.example.myapplication.data.repository;

public class UsersResponse
{
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String email;
    private UserImageResponse image;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
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

    public UserImageResponse getImage() {
        return image;
    }

    public void setImage(UserImageResponse image) {
        this.image = image;
    }
}

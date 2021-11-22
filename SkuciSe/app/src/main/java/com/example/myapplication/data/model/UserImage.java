package com.example.myapplication.data.model;

public class UserImage
{
    private final int id;
    private final int user_id;
    private final String image;

    public UserImage(int id, int user_id, String image) {
        this.id = id;
        this.user_id = user_id;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getImage() {
        return image;
    }
}

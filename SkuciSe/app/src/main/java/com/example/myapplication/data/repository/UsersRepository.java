package com.example.myapplication.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.data.model.User;
import com.example.myapplication.data.remote.UsersApiManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersRepository
{
    private static volatile UsersRepository instance;
    private final UsersApiManager usersApiManager;
    private final MutableLiveData<List<User>> users = new MutableLiveData<>();

    private UsersRepository(UsersApiManager usersApiManager)
    {
        this.usersApiManager = usersApiManager;
    }

    public static UsersRepository getInstance(UsersApiManager usersApiManager)
    {
        if(instance == null)
        {
            instance = new UsersRepository(usersApiManager);
        }
        return instance;
    }

    public MutableLiveData<List<User>> getUsers()
    {
        usersApiManager.getUsers(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful())
                {
                    List<User> body = response.body();
                    users.setValue(body);
                } else{
                    users.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                users.postValue(null);
            }
        });
        return users;
    }

}

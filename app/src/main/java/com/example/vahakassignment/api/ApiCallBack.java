package com.example.vahakassignment.api;

import android.content.Context;
import android.util.Log;

import com.example.vahakassignment.models.Example;


import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ApiCallBack<T> implements Callback<Example> {


    Context context;

    public ApiCallBack(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(Call<Example> call, Response<Example> response) {

        if (response.body() != null) {
            onSuccess((T) response.body());
        }


    }

    @Override
    public void onFailure(Call<Example> call, Throwable t) {

    }

    public abstract void onSuccess(T t);
    public abstract void onError(Error error);

}

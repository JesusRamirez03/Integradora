package com.example.integradora.Retrofit;

import com.example.integradora.Request.LoginRequest;
import com.example.integradora.Request.RegisterRequest;
import com.example.integradora.Response.LoginResponse;
import com.example.integradora.Response.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServices {


    @POST("/api/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("/api/usuarios")
    Call<RegisterResponse> register(@Body RegisterRequest RegisterRequest);


}


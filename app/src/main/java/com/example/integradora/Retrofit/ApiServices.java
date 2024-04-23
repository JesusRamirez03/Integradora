package com.example.integradora.Retrofit;

import com.example.integradora.Request.LoginRequest;
import com.example.integradora.Request.RecuperarContraRequest;
import com.example.integradora.Request.RegisterRequest;
import com.example.integradora.Response.GasResponse;
import com.example.integradora.Response.GiroscopioResponse;
import com.example.integradora.Response.HumedadResponse;
import com.example.integradora.Response.LoginResponse;
import com.example.integradora.Response.LuzResponse;
import com.example.integradora.Response.MovimientoResponse;
import com.example.integradora.Response.RecuperarContraResponse;
import com.example.integradora.Response.RegisterResponse;
import com.example.integradora.Response.TemperaturaResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServices {


    @POST("/api/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("/api/usuarios")
    Call<RegisterResponse> register(@Body RegisterRequest RegisterRequest);

    @POST("/api/password-recovery/request")
    Call<RecuperarContraResponse> recuperarcontra(@Body RecuperarContraRequest RecuperarContraRequest);
    @GET("/api/emqx/topic-gas")
    Call<GasResponse> getSensorData();
    @GET("/api/emqx/topic-humedad")
    Call<HumedadResponse> getSensorDataHumedad();
    @GET("/api/emqx/topic-temperatura")
    Call<TemperaturaResponse> getSensorDataTemperatura();
    @GET("/api/emqx/topic-luz")
    Call<LuzResponse> getSensorDataLuz();
    @GET("/api/emqx/topic-movimiento")
    Call<MovimientoResponse> getSensorDataMovimiento();
    @GET("/api/emqx/topic-giroscopio")
    Call<GiroscopioResponse> getSensorDataGiroscopio();





}


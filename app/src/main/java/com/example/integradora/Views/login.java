package com.example.integradora.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.integradora.R;
import com.example.integradora.Request.LoginRequest;
import com.example.integradora.Response.LoginResponse;
import com.example.integradora.Retrofit.ApiClient;
import com.example.integradora.ViewModels.LoginVM;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        EditText usuario = findViewById(R.id.editTextUsuario);
        EditText contrasena = findViewById(R.id.editTextPassword);
        Button iniciarsesion = findViewById(R.id.btnIniciarSesion);
        Button crearcuenta = findViewById(R.id.btnRegistrar);

        crearcuenta.setOnClickListener(v -> {
            Intent intent = new Intent(this, register.class);
            startActivity(intent);
        });

        iniciarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usuario.getText().toString().isEmpty() || contrasena.getText().toString().isEmpty()){
                    Toast.makeText(login.this, "Llena todos los campos", Toast.LENGTH_SHORT).show();
                }
                else {
                    LoginRequest loginRequest = new LoginRequest();
                    loginRequest.setEmail(usuario.getText().toString());
                    loginRequest.setPassword(contrasena.getText().toString());
                    loginUsers(loginRequest);
                }
            }
        });
    }
    public void loginUsers(LoginRequest loginRequest){
        Call<LoginResponse> loginResponseCall = ApiClient.getService().login(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(login.this, "Inicio de sesion exitoso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login.this, principal    .class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(login.this, "Error al iniciar sesion", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(login.this, "Error al iniciar sesion", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
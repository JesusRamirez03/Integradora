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

import com.example.integradora.R;
import com.example.integradora.Request.RegisterRequest;
import com.example.integradora.Response.RegisterResponse;
import com.example.integradora.Retrofit.ApiClient;
import com.example.integradora.Retrofit.ApiServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        EditText usuario = findViewById(R.id.editTextUsuario);
        EditText correo = findViewById(R.id.editTextCorreo);
        EditText numero = findViewById(R.id.editTextNumero);
        EditText contrasena = findViewById(R.id.editTextPassword);
        Button Registrarse = findViewById(R.id.btnregister);

        Registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usuario.getText().toString().isEmpty() || correo.getText().toString().isEmpty() || numero.getText().toString().isEmpty() || contrasena.getText().toString().isEmpty()){
                    Toast.makeText(register.this, "Llena todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }
                RegisterRequest registerRequest = new RegisterRequest();
                registerRequest.setNombre(usuario.getText().toString());
                registerRequest.setEmail(correo.getText().toString());
                registerRequest.setTelefono(numero.getText().toString());
                registerRequest.setPassword(contrasena.getText().toString());
                registerUsers(registerRequest);
            }
        });

    }
    public void registerUsers(RegisterRequest registerRequest){

        Call<RegisterResponse> registerResponseCall = ApiClient.getService().register(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(register.this, "Usuario registrado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(register.this, login.class);
                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(register.this, "Error al registrar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(register.this, "Error al registrar", Toast.LENGTH_SHORT).show();
            }
        });
    }


}

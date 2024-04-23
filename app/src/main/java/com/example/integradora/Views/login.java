package com.example.integradora.Views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.example.integradora.contraolvidada;

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
        TextView crearcuenta = findViewById(R.id.goToRegister);
        TextView olvidecontraseña = findViewById(R.id.forgot);
        crearcuenta.setOnClickListener(v -> {
            Intent intent = new Intent(this, register.class);
            startActivity(intent);
        });

        olvidecontraseña.setOnClickListener(v -> {
            Intent intent = new Intent(this, contraolvidada.class);
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
                    LoginResponse loginResponse = response.body();
                    if (loginResponse != null && "success".equals(loginResponse.getType())) {
                        // Acceder al token dentro del objeto data.token
                        String token = loginResponse.getData().getToken().getToken();
                        // Utilizar el token como sea necesario
                        Toast.makeText(login.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(login.this, principal.class);
                        startActivity(intent);
                    } else {
                        // Manejar caso de error de autenticación
                        Toast.makeText(login.this, "Error al iniciar sesión: " + loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("LoginActivity", "Error al iniciar sesión: " + loginResponse.getMessage());
                    }
                } else {
                    // Manejar caso de respuesta no exitosa
                    Toast.makeText(login.this, "Error al iniciar sesión: " + response.message(), Toast.LENGTH_SHORT).show();
                    Log.e("LoginActivity", "Error al iniciar sesión: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Manejar caso de fallo en la solicitud
                Toast.makeText(login.this, "Error al iniciar sesión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("LoginActivity", "Error al iniciar sesión: " + t.getMessage());
            }
        });
    }

}

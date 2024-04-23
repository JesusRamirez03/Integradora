package com.example.integradora.Views;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.integradora.R;
import com.example.integradora.Response.TemperaturaResponse;
import com.example.integradora.Retrofit.ApiClient;
import com.example.integradora.Retrofit.ApiServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class sensor_temperatura extends AppCompatActivity {

    private TextView temperaturaTextView;
    private Handler handler;
    private Runnable periodicUpdateRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_temperatura);

        temperaturaTextView = findViewById(R.id.temperatura);

        // Llamar al método para obtener el valor del sensor de temperatura al abrir la vista
        obtenerValorSensorTemperatura();

        // Configurar el handler y el runnable para la actualización periódica
        handler = new Handler();
        periodicUpdateRunnable = new Runnable() {
            @Override
            public void run() {
                obtenerValorSensorTemperatura();
                // Llamar a este método cada 5 segundos (5000 milisegundos)
                handler.postDelayed(this, 5000);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Comenzar la actualización periódica cuando la actividad se reanude
        handler.postDelayed(periodicUpdateRunnable, 0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Detener la actualización periódica cuando la actividad se pausa
        handler.removeCallbacks(periodicUpdateRunnable);
    }

    private void obtenerValorSensorTemperatura() {
        ApiServices apiServices = ApiClient.getService();
        Call<TemperaturaResponse> call = apiServices.getSensorDataTemperatura();
        call.enqueue(new Callback<TemperaturaResponse>() {
            @Override
            public void onResponse(Call<TemperaturaResponse> call, Response<TemperaturaResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Mostrar el valor del sensor de temperatura en el TextView
                    TemperaturaResponse sensorResponse = response.body();
                    double temperatura = sensorResponse.getValue();
                    temperaturaTextView.setText(String.valueOf(temperatura) + "°C");
                } else {
                    Toast.makeText(sensor_temperatura.this, "Error al obtener el valor del sensor de temperatura", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TemperaturaResponse> call, Throwable t) {
                Toast.makeText(sensor_temperatura.this, "Error al obtener el valor del sensor de temperatura: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}



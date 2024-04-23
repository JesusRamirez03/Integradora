package com.example.integradora.Views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.integradora.R;
import com.example.integradora.Response.HumedadResponse;
import com.example.integradora.Retrofit.ApiClient;
import com.example.integradora.Retrofit.ApiServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class sensor_humedad extends AppCompatActivity {

    private TextView humedadTextView;
    private Handler handler;
    private Runnable periodicUpdateRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_humedad);

        humedadTextView = findViewById(R.id.humedar);

        handler = new Handler();
        periodicUpdateRunnable = new Runnable() {
            @Override
            public void run() {
                obtenerValorSensorHumedad();
                // Actualizar cada 5 segundos (5000 milisegundos)
                handler.postDelayed(this, 5000);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Iniciar la actualización periódica cuando se reanude la actividad
        handler.postDelayed(periodicUpdateRunnable, 0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Detener la actualización periódica cuando la actividad se pause
        handler.removeCallbacks(periodicUpdateRunnable);
    }

    private void obtenerValorSensorHumedad() {
        ApiServices apiServices = ApiClient.getService();
        Call<HumedadResponse> call = apiServices.getSensorDataHumedad();
        call.enqueue(new Callback<HumedadResponse>() {
            @Override
            public void onResponse(Call<HumedadResponse> call, Response<HumedadResponse> response) {
                if (response.isSuccessful()) {
                    HumedadResponse humidityResponse = response.body();
                    // Mostrar el valor del sensor de humedad en el TextView
                    int humidityValue = humidityResponse != null ? humidityResponse.getHumidityValue() : 0;
                    humedadTextView.setText(humidityValue + "%");
                } else {
                    Toast.makeText(sensor_humedad.this, "Error al obtener datos de humedad", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HumedadResponse> call, Throwable t) {
                Toast.makeText(sensor_humedad.this, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
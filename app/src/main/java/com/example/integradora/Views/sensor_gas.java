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
import com.example.integradora.Response.GasResponse;
import com.example.integradora.Retrofit.ApiClient;
import com.example.integradora.Retrofit.ApiServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class sensor_gas extends AppCompatActivity {
    private TextView sensorValueTextView;
    private Handler handler;
    private Runnable periodicUpdateRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_gas);

        // Obtener referencia al TextView donde deseas mostrar el valor del sensor
        sensorValueTextView = findViewById(R.id.GasValue);

        // Configurar el handler y el runnable para la actualización periódica
        handler = new Handler();
        periodicUpdateRunnable = new Runnable() {
            @Override
            public void run() {
                obtenerValorSensorGas();
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

    private void obtenerValorSensorGas() {
        ApiServices apiServices = ApiClient.getService();
        Call<GasResponse> call = apiServices.getSensorData();
        call.enqueue(new Callback<GasResponse>() {
            @Override
            public void onResponse(Call<GasResponse> call, Response<GasResponse> response) {
                if (response.isSuccessful()) {
                    GasResponse sensorResponse = response.body();
                    // Mostrar el valor del sensor en el TextView
                    int sensorValue = sensorResponse != null ? sensorResponse.getValue() : 0;
                    sensorValueTextView.setText(String.valueOf(sensorValue));
                } else {
                    Toast.makeText(sensor_gas.this, "Error al obtener datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GasResponse> call, Throwable t) {
                Toast.makeText(sensor_gas.this, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
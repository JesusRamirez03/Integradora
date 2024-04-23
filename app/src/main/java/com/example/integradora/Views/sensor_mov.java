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
import com.example.integradora.Response.MovimientoResponse;
import com.example.integradora.Retrofit.ApiClient;
import com.example.integradora.Retrofit.ApiServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class sensor_mov extends AppCompatActivity {

    private TextView estadoTextView;
    private TextView valueTextView;
    private Handler handler;
    private Runnable periodicUpdateRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_mov);

        estadoTextView = findViewById(R.id.textVie);
        valueTextView = findViewById(R.id.value);

        // Llamar al método para obtener el valor del sensor de movimiento al abrir la vista
        obtenerValorSensorMovimiento();

        // Configurar el handler y el runnable para la actualización periódica
        handler = new Handler();
        periodicUpdateRunnable = new Runnable() {
            @Override
            public void run() {
                obtenerValorSensorMovimiento();
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

    private void obtenerValorSensorMovimiento() {
        ApiServices apiServices = ApiClient.getService();
        Call<MovimientoResponse> call = apiServices.getSensorDataMovimiento();
        call.enqueue(new Callback<MovimientoResponse>() {
            @Override
            public void onResponse(Call<MovimientoResponse> call, Response<MovimientoResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Mostrar el valor y estado del sensor de movimiento en los TextViews
                    MovimientoResponse sensorResponse = response.body();
                    int value = sensorResponse.getValue();
                    int movimiento = sensorResponse.getMovimiento();
                    valueTextView.setText(String.valueOf(value));
                    estadoTextView.setText(movimiento == 1 ? "Estado: Activo" : "Estado: Inactivo");
                } else {
                    Toast.makeText(sensor_mov.this, "Error al obtener el valor del sensor de movimiento", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovimientoResponse> call, Throwable t) {
                Toast.makeText(sensor_mov.this, "Error al obtener el valor del sensor de movimiento: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
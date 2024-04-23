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
import com.example.integradora.Response.LuzResponse;
import com.example.integradora.Retrofit.ApiClient;
import com.example.integradora.Retrofit.ApiServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class sensor_luz extends AppCompatActivity {

    private TextView luzTextView;
    private Handler handler;
    private Runnable periodicUpdateRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_luz);

        luzTextView = findViewById(R.id.valorluz);

        // Llamar al método para obtener el nivel de luz al abrir la vista
        obtenerNivelDeLuz();

        // Configurar el handler y el runnable para la actualización periódica
        handler = new Handler();
        periodicUpdateRunnable = new Runnable() {
            @Override
            public void run() {
                obtenerNivelDeLuz();
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

    private void obtenerNivelDeLuz() {
        ApiServices apiServices = ApiClient.getService();
        Call<LuzResponse> call = apiServices.getSensorDataLuz();
        call.enqueue(new Callback<LuzResponse>() {
            @Override
            public void onResponse(Call<LuzResponse> call, Response<LuzResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Mostrar el nivel de luz en el TextView
                    LuzResponse luzResponse = response.body();
                    int nivelDeLuz = luzResponse.getValue();
                    luzTextView.setText(String.valueOf(nivelDeLuz));
                } else {
                    Toast.makeText(sensor_luz.this, "Error al obtener el nivel de luz", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LuzResponse> call, Throwable t) {
                Toast.makeText(sensor_luz.this, "Error al obtener el nivel de luz: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
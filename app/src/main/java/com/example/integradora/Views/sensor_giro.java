package com.example.integradora.Views;
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
import com.example.integradora.Response.GiroscopioResponse;
import com.example.integradora.Retrofit.ApiClient;
import com.example.integradora.Retrofit.ApiServices;


import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class sensor_giro extends AppCompatActivity {

    private TextView pitchTextView, rollTextView;
    private Handler handler;
    private Runnable periodicUpdateRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_giro);

        pitchTextView = findViewById(R.id.pitchvalor);
        rollTextView = findViewById(R.id.rollvalor);

        // Llamar al método para obtener el valor del sensor de giroscopio al abrir la vista
        obtenerValorSensorGiroscopio();

        // Configurar el handler y el runnable para la actualización periódica
        handler = new Handler();
        periodicUpdateRunnable = new Runnable() {
            @Override
            public void run() {
                obtenerValorSensorGiroscopio();
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

    private void obtenerValorSensorGiroscopio() {
        ApiServices apiServices = ApiClient.getService();
        Call<GiroscopioResponse> call = apiServices.getSensorDataGiroscopio();
        call.enqueue(new Callback<GiroscopioResponse>() {
            @Override
            public void onResponse(Call<GiroscopioResponse> call, Response<GiroscopioResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Mostrar el valor del sensor de giroscopio en los TextViews
                    GiroscopioResponse sensorResponse = response.body();
                    double pitch = sensorResponse.getData().getMessageDecoded().getPitch();
                    double roll = sensorResponse.getData().getMessageDecoded().getRoll();
                    pitchTextView.setText(String.valueOf(pitch));
                    rollTextView.setText(String.valueOf(roll));
                } else {
                    Toast.makeText(sensor_giro.this, "Error al obtener el valor del sensor de giroscopio", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GiroscopioResponse> call, Throwable t) {
                Toast.makeText(sensor_giro.this, "Error al obtener el valor del sensor de giroscopio: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
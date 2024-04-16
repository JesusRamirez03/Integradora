package com.example.integradora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


public class inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Avanzar(View view) {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }

    Button buttonTriggers = findViewById(R.id.trigger);
    Button buttonSensors = findViewById(R.id.Sensor);
    LinearLayout ultrasonic = findViewById(R.id.ultrasonic);
    LinearLayout temperatura = findViewById(R.id.temperatura);
    LinearLayout humedad = findViewById(R.id.humedad);
    LinearLayout impact = findViewById(R.id.Impact);
    LinearLayout gas = findViewById(R.id.gas);
    LinearLayout luz = findViewById(R.id.luz);


    // botones de sensores
    public void onLinear(View view) {
        Intent intent = new Intent(this, sensor_mov.class);
        startActivity(intent);
    }

    public void onLineartemp(View view) {
        Intent intent = new Intent(this, sensor_temperatura.class);
        startActivity(intent);
    }

    public void onLinearhum(View view) {
        Intent intent = new Intent(this, sensor_humedad.class);
        startActivity(intent);
    }

    public void onLineargas(View view) {
        Intent intent = new Intent(this, sensor_gas.class);
        startActivity(intent);
    }

    public void onLinearluz(View view) {
        Intent intent = new Intent(this, sensor_luz.class);
        startActivity(intent);
    }

    public void onLinearimp(View view) {
        Intent intent = new Intent(this, sensor_giro.class);
        startActivity(intent);
    }
}
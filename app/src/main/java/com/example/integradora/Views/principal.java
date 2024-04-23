package com.example.integradora.Views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.integradora.AgregarIncubadora;
import com.example.integradora.AgregarSensor;
import com.example.integradora.R;
import com.example.integradora.Response.LoginResponse;
import com.google.android.material.navigation.NavigationView;

public class principal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    LoginResponse loginResponse;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if (drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            loginResponse = (LoginResponse) intent.getSerializableExtra("loginResponse");
            Log.e("loginResponse", loginResponse.getMessage());
        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menuPorfile) {
                    // Inicia la Activity Profile
                    Intent intent = new Intent(principal.this, cuenta.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                }
                else if (item.getItemId()==R.id.menuAgregarIncubadora) {
                    Intent intent = new Intent(principal.this, AgregarIncubadora.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                }
                else if (item.getItemId()==R.id.menuAgregarSensor) {
                    Intent intent = new Intent(principal.this, AgregarSensor.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                }
                else if (item.getItemId()==R.id.movimiento) {
                    Intent intent = new Intent(principal.this, sensor_mov.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                }
                else if (item.getItemId()==R.id.gas1) {
                    Intent intent = new Intent(principal.this, sensor_gas.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                }
                else if (item.getItemId()==R.id.luz1) {
                    Intent intent = new Intent(principal.this, sensor_luz.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                }
                else if (item.getItemId()==R.id.humedad1) {
                    Intent intent = new Intent(principal.this, sensor_humedad.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                }
                else if (item.getItemId()==R.id.temperatura1) {
                    Intent intent = new Intent(principal.this, sensor_temperatura.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                }
                else if (item.getItemId()==R.id.giroscopio) {
                    Intent intent = new Intent(principal.this, sensor_giro.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                }

                return false; // Si no es el ítem deseado, no hacer nada
            }

        });
        Button buttonSensors = findViewById(R.id.Sensor);
        LinearLayout ultrasonic = findViewById(R.id.ultrasonic);
        LinearLayout temperatura = findViewById(R.id.temperatura);
        LinearLayout humedad = findViewById(R.id.humedad);
        LinearLayout giroscopio = findViewById(R.id.Impact);
        LinearLayout gas = findViewById(R.id.gas);
        LinearLayout luz = findViewById(R.id.luz);



       buttonSensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(principal.this, principal.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.imagMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


    }


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

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}
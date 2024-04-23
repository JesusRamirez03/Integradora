package com.example.integradora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.integradora.Request.RecuperarContraRequest;
import com.example.integradora.Response.RecuperarContraResponse;
import com.example.integradora.Retrofit.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class contraolvidada extends AppCompatActivity {
    Button btnenviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contraolvidada);
        btnenviar = findViewById(R.id.send);
        btnenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecuperarContraRequest recuperarContraRequest = new RecuperarContraRequest();
                recuperarContraRequest.setEmail("correo");
                forgotpass(recuperarContraRequest);
            }
        });


    }



    public void forgotpass(RecuperarContraRequest recuperarContraRequest){
        Call<RecuperarContraResponse> recuperarContraResponseCall = ApiClient.getService().recuperarcontra(recuperarContraRequest);
        recuperarContraResponseCall.enqueue(new Callback<RecuperarContraResponse>() {
            @Override
            public void onResponse(Call<RecuperarContraResponse> call, Response<RecuperarContraResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(contraolvidada.this, "Se ha enviado un correo con tu nueva contraseña", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(contraolvidada.this, "No se ha podido enviar el correo", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RecuperarContraResponse> call, Throwable t) {
                Toast.makeText(contraolvidada.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
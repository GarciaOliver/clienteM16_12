package com.example.figuras;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Rombo extends AppCompatActivity {

    TextView rest;
    EditText lado;
    EditText Dmayor;
    EditText Dmenor;
    Button cargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rombo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cargar=findViewById(R.id.btnRomboR);
        rest=findViewById(R.id.txtRombo);
        lado=findViewById(R.id.txtLadoR);
        Dmayor=findViewById(R.id.txtDMayor);
        Dmenor=findViewById(R.id.txtDMenor);

        cargar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String valorLado = lado.getText().toString().trim();
                String valorDmayor = Dmayor.getText().toString().trim();
                String valorDmenor = Dmenor.getText().toString().trim();

                if(!valorLado.isEmpty() && !valorDmayor.isEmpty() && !valorDmenor.isEmpty()){
                    new ConsumeServerTask().execute("http://10.0.2.2:3000/rombo?lado="
                            +valorLado+"&Dmayor="+valorDmayor+"&Dmenor="+valorDmenor);
                }else{
                    rest.setText("Por favor, ingrese ambos valores.");
                }

            }
        });
    }

    private class ConsumeServerTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            try {
                // Configuración de la conexión
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                // Leer la respuesta
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                reader.close();
                response = result.toString();

            } catch (Exception e) {
                response = "Error: " + e.getMessage();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            // Mostrar la respuesta en el TextView
            rest.setText(result);
        }
    }
}
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

public class Trapecio extends AppCompatActivity {

    TextView rest;
    EditText lado;
    EditText B1;
    EditText B2;
    EditText altura;
    Button cargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_trapecio);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cargar=findViewById(R.id.btnTrapecioT);
        rest=findViewById(R.id.txtTrap);
        lado=findViewById(R.id.txtLadoT);
        B1=findViewById(R.id.txtB1);
        B2=findViewById(R.id.txtB2);
        altura=findViewById(R.id.txtAltura);

        cargar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String valorLado = lado.getText().toString().trim();
                String valorB1 = B1.getText().toString().trim();
                String valorB2 = B2.getText().toString().trim();
                String valorAltura = altura.getText().toString().trim();

                if(!valorLado.isEmpty() && !valorB1.isEmpty() && !valorB2.isEmpty() && !valorAltura.isEmpty()){
                    new ConsumeServerTask().execute("http://10.0.2.2:3000/trapecio?lado="
                            +valorLado+"&base1="+valorB1+"&altura="+ valorAltura+"&base2="+valorB2);
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
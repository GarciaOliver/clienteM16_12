package com.example.figuras;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button1 = findViewById(R.id.btnNombre);
        Button button2 = findViewById(R.id.btnSuma);
        Button button3 = findViewById(R.id.btnSumaP);
        Button button4 = findViewById(R.id.btnCuadrado);
        Button button5 = findViewById(R.id.btnRombo);
        Button button6 = findViewById(R.id.btnTrapecio);

        button1.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Nombre.class)));
        button2.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Suma.class)));
        button3.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SumaP.class)));
        button4.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Cuadrado.class)));
        button5.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Rombo.class)));
        button6.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Trapecio.class)));
    }
}
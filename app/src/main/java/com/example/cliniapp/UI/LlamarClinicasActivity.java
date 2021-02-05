package com.example.cliniapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.cliniapp.R;

public class LlamarClinicasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llamar_clinicas);

        //Creando las variables;
        ImageButton btnLlamarClinicaInternacional;
        ImageButton btnLlamarJockeySalud;
        ImageButton btmLlamarGoodHope;
        ImageButton btnLlamarDelgado;

        //Instanciando las variables;
        btnLlamarClinicaInternacional=findViewById(R.id.btnLlamarClinicaInternacional);
        btnLlamarJockeySalud=findViewById(R.id.btnLlamarJockeySalud);
        btmLlamarGoodHope=findViewById(R.id.btmLlamarGoodHope);
        btnLlamarDelgado=findViewById(R.id.btnLlamarDelgado);


        //Telefonos a llamar:
        //Clinica Internacional:01 6196161
        //Clinica Aurea:988 038 208
        //Clinica Jockey:(01) 7123456
        //Clinica GoodHope:(01) 6107300

        //Metodo onClick();
        btnLlamarClinicaInternacional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri number = Uri.parse("tel:01 6196161");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callIntent);

            }
        });


        //Metodo onClick();
        btnLlamarJockeySalud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri number = Uri.parse("tel:01 7123456");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callIntent);

            }
        });


        //Metodo onClick();
        btmLlamarGoodHope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri number = Uri.parse("tel:01 6107300");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callIntent);

            }
        });


        //Metodo onClick();
        btnLlamarDelgado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri number = Uri.parse("tel:988 038 208");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callIntent);

            }
        });













    }
}
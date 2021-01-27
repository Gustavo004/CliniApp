package com.example.cliniapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.cliniapp.Inteface.JsonPlaceHolderApi;
import com.example.cliniapp.Models.HistoriaMedica;
import com.example.cliniapp.R;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HMedicaActivity extends AppCompatActivity {

    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h_medica);

        textViewResult=findViewById(R.id.textViewResult);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.103:8087/WebServicesCliniApp/")
                .addConverterFactory(GsonConverterFactory.create() )
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);


        Call<List<HistoriaMedica>> call = jsonPlaceHolderApi.getHistoriasMedicas();


        call.enqueue(new Callback<List<HistoriaMedica>>() {
            @Override
            public void onResponse(Call<List<HistoriaMedica>> call, Response<List<HistoriaMedica>> response) {

                if (!response.isSuccessful() )
                {
                    textViewResult.setText("Code :"+response.code() );
                    return;
                }
                List<HistoriaMedica> historiaMedicas =response.body();


                for (HistoriaMedica historiaMedica : historiaMedicas)
                {
                    String content ="";

                    content += "tipoSangre: "+historiaMedica.getTipoSangre() + "\n";
                    content += "peso: "+historiaMedica.getPeso()+ "\n";
                    content += "pulso:"+historiaMedica.getPulso()+ "\n";
                    content += "presion: "+historiaMedica.getPresion() + "\n";
                    content += "dni: "+historiaMedica.getDni()+ "\n";
                    content += "idDiagnostico: "+historiaMedica.getIdDiagnostico() + "\n";
                    content += "idHistoriaMedica: "+historiaMedica.getIdHistoriaMedica() + "\n";
                    content += "temperaturaCorporal: "+historiaMedica.getTemperaturaCorporal() + "\n";
                    content += "idMedicamentos: "+historiaMedica.getIdMedicamentos() + "\n";
                    content += "fecha_De_La_Historia_Medica: "+historiaMedica.getFecha_De_La_Historia_Medica() + "\n\n";

                    textViewResult.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<HistoriaMedica>> call, Throwable t) {

                    textViewResult.setText(t.getMessage() );
            }
        });







    }
}
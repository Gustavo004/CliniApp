package com.example.cliniapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import java.util.List;

import com.example.cliniapp.Models.HistoriaMedica;
import com.example.cliniapp.R;
import com.example.cliniapp.UI.Adapters.HistoriaMedicaAdapter;
import com.example.cliniapp.ViewModel.HistoriaMedicaViewModel;



public class HistoriaMedicaActivity extends AppCompatActivity {


    //Mapea los objetos definidos;
    HistoriaMedicaViewModel historiaMedicaViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historia_medica);

        historiaMedicaViewModel = new ViewModelProvider(this).get(HistoriaMedicaViewModel.class);
        historiaMedicaViewModel.getHistoriaMedica();

        //Definiendo al recycler;
        RecyclerView rvHistoriaMedicas=findViewById(R.id.rvHistoriaMedicas);
        final HistoriaMedicaAdapter adapter = new HistoriaMedicaAdapter();

        //Setear el layaoutMananger;
        rvHistoriaMedicas.setLayoutManager(new LinearLayoutManager(this));

        //Setear el adaptador;
        rvHistoriaMedicas.setAdapter(adapter);

        //Necesita de un observador para visualizar la data;
        historiaMedicaViewModel.historiaMedicaListMutableLiveData.observe(this,

                 new Observer<List<HistoriaMedica>>() {
                    @Override
                    public void onChanged(List<HistoriaMedica> list) {

                        adapter.AgregarElementosHistoriaMedica(list);

                    }
                }

        );


    }

}
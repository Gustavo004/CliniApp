package com.example.cliniapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.cliniapp.APIRest.HistoriaMedicaClient;
import com.example.cliniapp.Models.HistoriaMedica;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoriaMedicaViewModel extends AndroidViewModel {

    ///Encargado de ejecutar ordenes;logica de programacion;
        public MutableLiveData <List<HistoriaMedica>> historiaMedicaListMutableLiveData = new MutableLiveData<>();

        public MutableLiveData<String>historiaMedicaError=new MutableLiveData<>();

    public HistoriaMedicaViewModel(@NonNull Application application) {
        super(application);
    }

    public void getHistoriaMedica()
    {
        HistoriaMedicaClient.getINSTANCE().getHistoriaMedica().enqueue(
                new Callback<List<HistoriaMedica>>() {

            //En caso de exito;
            @Override
            public void onResponse(Call<List<HistoriaMedica>> call, Response<List<HistoriaMedica>> response) {

                historiaMedicaListMutableLiveData.setValue(response.body() );

            }


            //Si falla el proceso;
            @Override
            public void onFailure(Call<List<HistoriaMedica>> call, Throwable t) {

                 historiaMedicaError.setValue(t.getMessage() );
            }



        });


    }

}

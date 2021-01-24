package com.example.cliniapp.APIRest;

import com.example.cliniapp.Models.HistoriaMedica;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HistoriaMedicaInterface {

    //Para que pueda usar get o post,etc

    @GET("historiamedica")
    public Call<List<HistoriaMedica>> getHistoriaMedica();
}

package com.example.cliniapp.Inteface;

import com.example.cliniapp.Models.HistoriaMedica;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

//Declarar el metodo;
@GET("HistoriasMedicas")
Call<List<HistoriaMedica>>getHistoriasMedicas();






}

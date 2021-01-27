package com.example.cliniapp.Models;


import com.google.gson.annotations.SerializedName;

public class HistoriaMedica  {

    @SerializedName("temperaturaCorporal")
    private String temperaturaCorporal;

    @SerializedName("idHistoriaMedica")
    private int idHistoriaMedica;

    @SerializedName("idMedicamentos")
    private int idMedicamentos;

    @SerializedName("fecha_De_La_Historia_Medica")
    private String fecha_De_La_Historia_Medica;

    @SerializedName("tipoSangre")
    private String tipoSangre;

    @SerializedName("peso")
    private int peso;

    @SerializedName("pulso")
    private String pulso;

    @SerializedName("presion")
    private String presion;

    @SerializedName("idDiagnostico")
    private int idDiagnostico;

    @SerializedName("dni")
    private int dni;

    public String getTemperaturaCorporal() {
        return temperaturaCorporal;
    }

    public int getIdHistoriaMedica() {
        return idHistoriaMedica;
    }

    public int getIdMedicamentos() {
        return idMedicamentos;
    }

    public String getFecha_De_La_Historia_Medica() {
        return fecha_De_La_Historia_Medica;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public int getPeso() {
        return peso;
    }

    public String getPulso() {
        return pulso;
    }

    public String getPresion() {
        return presion;
    }

    public int getIdDiagnostico() {
        return idDiagnostico;
    }

    public int getDni() {
        return dni;
    }

}

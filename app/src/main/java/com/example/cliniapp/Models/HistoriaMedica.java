package com.example.cliniapp.Models;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;

public class HistoriaMedica  {

    private String temperaturaCorporal;
    private Integer idHistoriaMedica;
    private Integer idMedicamentos;
    private  Date fecha_De_La_Historia_Medica;
    private  String tipoSangre;
    private Integer peso;
    private  String pulso;
    private String presion;
    private Integer idDiagnostico;
    private Integer dni;


    public HistoriaMedica(String temperaturaCorporal, Integer idHistoriaMedica, Integer idMedicamentos, Date fecha_De_La_Historia_Medica, String tipoSangre, Integer peso, String pulso, String presion, Integer idDiagnostico, Integer dni) {
        this.temperaturaCorporal = temperaturaCorporal;
        this.idHistoriaMedica = idHistoriaMedica;
        this.idMedicamentos = idMedicamentos;
        this.fecha_De_La_Historia_Medica = fecha_De_La_Historia_Medica;
        this.tipoSangre = tipoSangre;
        this.peso = peso;
        this.pulso = pulso;
        this.presion = presion;
        this.idDiagnostico = idDiagnostico;
        this.dni = dni;
    }


    public String getTemperaturaCorporal() {
        return temperaturaCorporal;
    }

    public Integer getIdHistoriaMedica() {
        return idHistoriaMedica;
    }

    public Integer getIdMedicamentos() {
        return idMedicamentos;
    }

    public Date getFecha_De_La_Historia_Medica() {
        return fecha_De_La_Historia_Medica;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public Integer getPeso() {
        return peso;
    }

    public String getPulso() {
        return pulso;
    }

    public String getPresion() {
        return presion;
    }

    public Integer getIdDiagnostico() {
        return idDiagnostico;
    }

    public Integer getDni() {
        return dni;
    }
}

package com.example.cliniapp.Models;

import java.util.Date;

public class Diagnostico {

    private int IdDiagnostico;
    private String Observaciones;
    private String Estado;
    private Date FechaDiagnosticada;
    private int DNI;
    private int IdUsuario;

    public Diagnostico(int idDiagnostico, String observaciones, String estado, Date fechaDiagnosticada, int dNI,
                       int idUsuario) {
        super();
        IdDiagnostico = idDiagnostico;
        Observaciones = observaciones;
        Estado = estado;
        FechaDiagnosticada = fechaDiagnosticada;
        DNI = dNI;
        IdUsuario = idUsuario;
    }
    public Diagnostico()
    {

    }


    public int getIdDiagnostico() {
        return IdDiagnostico;
    }

    public void setIdDiagnostico(int idDiagnostico) {
        IdDiagnostico = idDiagnostico;
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String observaciones) {
        Observaciones = observaciones;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public Date getFechaDiagnosticada() {
        return FechaDiagnosticada;
    }

    public void setFechaDiagnosticada(Date fechaDiagnosticada) {
        FechaDiagnosticada = fechaDiagnosticada;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int dNI) {
        DNI = dNI;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }


}

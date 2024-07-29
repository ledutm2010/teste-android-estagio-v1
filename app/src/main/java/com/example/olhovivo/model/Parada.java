package com.example.olhovivo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Parada {
    //Código identificador da parada
    @SerializedName("cp")
    private int codParada;
    //Nome da parada
    @SerializedName("np")
    private String nomeParada;
    //Endereço de localização da parada
    @SerializedName("ed")
    private String enderecoLocParada;
    //Latitude da localização da parada
    @SerializedName("py")
    private double latLocParada;
    //Longetude da localização da parada
    @SerializedName("px")
    private double logLocParada;
    // Relação das linhas por parada

    Parada(int codParada, String nomeParada, String enderecoLocParada, double latLocParada, double logLocParada){
        this.codParada = codParada;
        this.nomeParada = nomeParada;
        this.enderecoLocParada = enderecoLocParada;
        this.latLocParada = latLocParada;
        this.logLocParada = logLocParada;
    }

    public int getCodParada() {
        return codParada;
    }

    public String getNomeParada() {
        return nomeParada;
    }

    public String getEnderecoLocParada() {
        return enderecoLocParada;
    }

    public double getLatLocParada() {
        return latLocParada;
    }

    public double getLogLocParada() {
        return logLocParada;
    }
}

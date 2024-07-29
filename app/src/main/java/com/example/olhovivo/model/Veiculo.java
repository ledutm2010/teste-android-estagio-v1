package com.example.olhovivo.model;

import com.google.gson.annotations.SerializedName;

public class Veiculo {
    //codigo da linha que o veiculo pertence
    private int codLinha;
    //Prefixo do veículo
    @SerializedName("p")
    private int prefVeiculo;
    //Indica se o veiculo é acessivel para deficiente
    @SerializedName("a")
    private boolean eAcessivel;
    //Horário previsto para chegada do veículo no ponto de parada relacionado
    @SerializedName("t")
    private String horaChegadaVeiculo;
    //Horario da captura da localização do veiculo
    @SerializedName("ta")
    private String horaLocVeiculo;
    //Latitude da localização do veiculo
    @SerializedName("py")
    private double latLocVeiculo;
    //Longetude da localização do veiculo
    @SerializedName("px")
    private double logLocVeiculo;

    Veiculo(int prefVeiculo, boolean eAcessivel, String horaLocVeiculo, String horaChegadaVeiculo,
            double latLocVeiculo, double logLocVeiculo){
        this.prefVeiculo = prefVeiculo;
        this.eAcessivel = eAcessivel;
        this.horaLocVeiculo = horaLocVeiculo;
        this.horaChegadaVeiculo = horaChegadaVeiculo;
        this.latLocVeiculo = latLocVeiculo;
        this.logLocVeiculo = logLocVeiculo;
    }

    public void setCodLinha(int codLinha) {
        this.codLinha = codLinha;
    }

    public int getCodLinha() {
        return codLinha;
    }

    public int getPrefVeiculo() {
        return prefVeiculo;
    }

    public boolean iseAcessivel() {
        return eAcessivel;
    }

    public String getHoraChegadaVeiculo() {
        return horaChegadaVeiculo;
    }

    public String getHoraLocVeiculo() {
        return horaLocVeiculo;
    }

    public double getLatLocVeiculo() {
        return latLocVeiculo;
    }

    public double getLogLocVeiculo() {
        return logLocVeiculo;
    }
}

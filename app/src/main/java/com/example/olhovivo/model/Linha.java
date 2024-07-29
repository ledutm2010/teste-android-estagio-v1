package com.example.olhovivo.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Linha {
    //codigo da parada que a linha passa
    private int codParada;
    //Letreiro completo
    @SerializedName("c")
    private String letreiroCompleto;
    // Código identificador da linha
    @SerializedName("cl")
    private int codIdentLinha;
    //Sentido de operação
    @SerializedName("sl")
    private int sentidoDeOperacao;
    //Letreiro de destino da linha
    @SerializedName("lt0")
    private String letreiroDest;
    // Letreiro de origem da linha
    @SerializedName("lt1")
    private String letreiroOrig;
    //Quantidade de veículos localizados
    @SerializedName("qv")
    private int qtdVeiculosLocalizados;
    // Relação de veículos localizados
    @SerializedName("vs")
    private ArrayList<Veiculo> listaVeiculosPorLinha;

    Linha(String letreiroCompleto, int codIdentLinha, int sentidoDeOperacao, String letreiroDest,
          String letreiroOrig, int qtdVeiculosLocalizados, ArrayList<Veiculo> listaVeiculosPorLinha){
        this.letreiroCompleto = letreiroCompleto;
        this.codIdentLinha = codIdentLinha;
        this.sentidoDeOperacao = sentidoDeOperacao;
        this.letreiroDest = letreiroDest;
        this.letreiroOrig = letreiroOrig;
        this.qtdVeiculosLocalizados = qtdVeiculosLocalizados;
        this.listaVeiculosPorLinha = listaVeiculosPorLinha;
    }

    public void setCodParada(int codParada) {
        this.codParada = codParada;
    }

    public int getCodLinha() {
        return codParada;
    }

    public String getLetreiroCompleto() {
        return letreiroCompleto;
    }

    public int getCodIdentLinha() {
        return codIdentLinha;
    }

    public int getSentidoDeOperacao() {
        return sentidoDeOperacao;
    }

    public String getLetreiroDest() {
        return letreiroDest;
    }

    public String getLetreiroOrig() {
        return letreiroOrig;
    }

    public int getQtdVeiculosLocalizados() {
        return qtdVeiculosLocalizados;
    }

    public ArrayList<Veiculo> getListaVeiculosPorLinha() {
        return listaVeiculosPorLinha;
    }
}

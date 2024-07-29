package com.example.olhovivo.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ParadaLinhaVeiculo {
    // Relação de veículos localizados
    @SerializedName("p")
    private Parada parada;
    // Relação de linhas localizadas
    @SerializedName("l")
    private ArrayList<Linha> listaLinhasPorParada;
    // Relação de veículos localizados
    @SerializedName("vs")
    private ArrayList<Veiculo> listaVeiculosPorLinha;

    ParadaLinhaVeiculo(Parada parada, ArrayList<Linha> listaLinhasPorParada, ArrayList<Veiculo> listaVeiculosPorLinha){
        this.parada = parada;
        this.listaLinhasPorParada = listaLinhasPorParada;
        this.listaVeiculosPorLinha = listaVeiculosPorLinha;
    }

    public Parada getParada() {
        return parada;
    }

    public ArrayList<Linha> getListaLinhasPorParada() {
        return listaLinhasPorParada;
    }

    public ArrayList<Veiculo> getListaVeiculosPorLinha() {
        return listaVeiculosPorLinha;
    }
}

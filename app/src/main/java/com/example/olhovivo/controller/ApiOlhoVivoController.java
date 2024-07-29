package com.example.olhovivo.controller;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.olhovivo.model.Linha;
import com.example.olhovivo.model.Parada;
import com.example.olhovivo.model.ParadaLinhaVeiculo;
import com.example.olhovivo.model.Veiculo;

public class ApiOlhoVivoController {
    private ApiOlhoVivo apiOlhoVivo;

    public ArrayList<Linha> listaLinhas;
    public ArrayList<Parada> listaParadas;
    public ArrayList<Veiculo> listaVeiculos;
    public ParadaLinhaVeiculo paradaLinhasVeiculos;
    public ParadaLinhaVeiculo paradaLinhaVeiculoEspecifico;
    public Parada parada;

    public void conectarApiOlhoVivo(){
        apiOlhoVivo = ConexaoApiOlhoVivo.criarConexao().create(ApiOlhoVivo.class);
        realizarLogin();
    }

    private void realizarLogin() {
        apiOlhoVivo.login();
    }

    //1 e 2.
    public void pegarTodasLinhasEVeiculos(){
        Call<ArrayList<Linha>> callAsync = apiOlhoVivo.pegarTodosVeiculosPorLinhas();
        callAsync.enqueue(new Callback<ArrayList<Linha>>() {
            @Override
            public void onResponse(Call<ArrayList<Linha>> call, Response<ArrayList<Linha>> response) {
                listaLinhas = response.body();
                pegarTodosVeiculosPorLinha(listaLinhas);
            }

            @Override
            public void onFailure(Call<ArrayList<Linha>> call, Throwable t) {
                System.out.println("Algo deu errado. Por favor, tentar novamente.");
            }
        });
    }

    private void pegarTodosVeiculosPorLinha(ArrayList<Linha> listaLinhas) {
        for (Linha linha: listaLinhas){
            ArrayList<Veiculo> veiculos = linha.getListaVeiculosPorLinha();
            for (Veiculo veiculo: veiculos){
                veiculo.setCodLinha(linha.getCodIdentLinha());
                listaVeiculos.add(veiculo);
            }
        }
    }

    //3.
    public void pegarParadaEspecifica(int codParada){
        Call<Parada> callAsync = apiOlhoVivo.pegarParadaEspecifica(codParada);
        callAsync.enqueue(new Callback<Parada>() {
            @Override
            public void onResponse(Call<Parada> call, Response<Parada> response) {
                parada = response.body();
            }

            @Override
            public void onFailure(Call<Parada> call, Throwable t) {
                System.out.println("Algo deu errado. Por favor, tentar novamente.");
            }
        });
    }

    //4.
    public void pegarTodosVeiculosDaLinhaPorParadaEHorarioDeChegada(int codParada, int codLinha){
        codLinha = 0;
        Call<ParadaLinhaVeiculo> callAsync = apiOlhoVivo.pegarHorarioChegadaVeiculosDaLinhaPorParada(codParada, codLinha);
        callAsync.enqueue(new Callback<ParadaLinhaVeiculo>() {
            @Override
            public void onResponse(Call<ParadaLinhaVeiculo> call, Response<ParadaLinhaVeiculo> response) {
                paradaLinhasVeiculos = response.body();
                pegarTodasParadas(paradaLinhasVeiculos);
                pegarTodaslinhasPorParada(parada, paradaLinhasVeiculos);
                pegarTodosVeiculosPorLinha(listaLinhas);
            }

            @Override
            public void onFailure(Call<ParadaLinhaVeiculo> call, Throwable t) {
                System.out.println("Algo deu errado. Por favor, tentar novamente.");
            }
        });
    }

    private void pegarTodasParadas(ParadaLinhaVeiculo paradaLinhasVeiculos) {
        parada = paradaLinhasVeiculos.getParada();
    }

    private void pegarTodaslinhasPorParada(Parada parada, ParadaLinhaVeiculo paradaLinhasVeiculos) {
        listaLinhas = paradaLinhasVeiculos.getListaLinhasPorParada();
        for (Linha linha: listaLinhas){
            linha.setCodParada(parada.getCodParada());
        }
    }
}

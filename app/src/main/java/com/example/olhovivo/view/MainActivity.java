package com.example.olhovivo.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.olhovivo.R;
import com.example.olhovivo.controller.ApiOlhoVivoController;
import com.example.olhovivo.model.Linha;
import com.example.olhovivo.model.Parada;
import com.example.olhovivo.model.ParadaLinhaVeiculo;
import com.example.olhovivo.model.Veiculo;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private ApiOlhoVivoController apiOlhoVivoController;
    private ArrayList<Linha> arrayListaLinhas;
    private ArrayList<Parada> arrayListaParadas;
    private ArrayList<Veiculo> arrayListaVeiculos;
    private ParadaLinhaVeiculo paradaLinhasVeiculos;
    private Linha linha;
    private Veiculo veiculo;
    private Parada parada;

    private EditText edtCodParada;
    private EditText edtCodLinha;
    private RadioGroup radioGroup;
    private RadioButton edtTodosVeiculos;
    private RadioButton edtParada;
    private RadioButton edtChegada;
    private Button btnFiltrar;

    private int pesquisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtCodParada = findViewById(R.id.edt_codParada);
        edtCodLinha = findViewById(R.id.edt_codLinha);
        radioGroup = findViewById(R.id.radiogroup);
        edtTodosVeiculos = findViewById(R.id.radio_todos_veiculos);
        edtParada = findViewById(R.id.radio_parada);
        edtChegada = findViewById(R.id.radio_chegada);
        btnFiltrar = findViewById(R.id.btn_filtrar);

        pesquisa = 0;

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // on below line we are getting radio button from our group.
                RadioButton radioButton = findViewById(checkedId);
                String tipo = (String) radioButton.getText();

                if (tipo.equals("Todos Veículos")){
                    edtCodParada.setVisibility(View.INVISIBLE);
                    edtCodLinha.setVisibility(View.INVISIBLE);
                    pesquisa = 1;
                } else if (tipo.equals("Parada")){
                    edtCodParada.setVisibility(View.VISIBLE);
                    edtCodLinha.setVisibility(View.INVISIBLE);
                    pesquisa = 2;
                } else if (tipo.equals("Chegada")){
                    edtCodParada.setVisibility(View.VISIBLE);
                    edtCodLinha.setVisibility(View.VISIBLE);
                    pesquisa = 3;
                }
            }
        });

        // Get the SupportMapFragment and request notification when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.maps);

        mapFragment.getMapAsync(this);

        //TODO: programar para filtrar pelo click do botão após selecionar
        btnFiltrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        apiOlhoVivoController.conectarApiOlhoVivo();

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        if (pesquisa == 1){
            //pega todas os veiculos por linha
            //mostra as informações de todas as linhas
            //mostra as posicões dos veiculos
            apiOlhoVivoController.pegarTodasLinhasEVeiculos();
            arrayListaLinhas = apiOlhoVivoController.listaLinhas;
            arrayListaVeiculos = apiOlhoVivoController.listaVeiculos;
            pesquisaLinhaEVeiculo(arrayListaLinhas, arrayListaVeiculos, googleMap);
        } else if (pesquisa == 2){
            //mostra as informações de uma parada especifica
            int codParada = Integer.parseInt(edtCodParada.getText().toString());
            apiOlhoVivoController.pegarParadaEspecifica(codParada);
            parada = apiOlhoVivoController.parada;
            pesquisaParada(parada, googleMap);
        } else if (pesquisa == 3){
            //mostra todos os veiculos por linha e horario de chegada na parada
            int codParada = Integer.parseInt(edtCodParada.getText().toString());
            int codLinha = Integer.parseInt(edtCodLinha.getText().toString());
            apiOlhoVivoController.pegarTodosVeiculosDaLinhaPorParadaEHorarioDeChegada(codParada,codLinha);
            arrayListaLinhas = apiOlhoVivoController.listaLinhas;
            arrayListaVeiculos = apiOlhoVivoController.listaVeiculos;
            pesquisaChegada(arrayListaLinhas, arrayListaVeiculos, googleMap);
        }
    }

    //TODO: finalizar o funcionamento do metodo
    private void pesquisaLinhaEVeiculo(ArrayList<Linha> arrayListaLinhas, ArrayList<Veiculo> arrayListaVeiculos, GoogleMap googleMap) {
        googleMap.clear();

        for (int i = 0; i < arrayListaLinhas.size(); i++) {
            googleMap.addMarker(new MarkerOptions()
                    .position()
                    .title());
        }

        // below line is use to zoom our camera on map.
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng());
    }

    private void pesquisaParada(Parada parada, GoogleMap googleMap) {
        googleMap.clear();

        LatLng posParada = new LatLng(parada.getLatLocParada(), parada.getLogLocParada());

        googleMap.addMarker(new MarkerOptions()
                .position(posParada)
                .title(parada.getCodParada() + " - " + parada.getNomeParada() + " - " + parada.getEnderecoLocParada()));

        // below line is use to zoom our camera on map.
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(posParada));
    }

    //TODO: finalizar o funcionamento do metodo
    private void pesquisaChegada(ArrayList<Linha> arrayListaLinhas, ArrayList<Veiculo> arrayListaVeiculos, GoogleMap googleMap) {
        googleMap.clear();

        for (int i = 0; i < arrayListaLinhas.size(); i++) {
            googleMap.addMarker(new MarkerOptions()
                    .position()
                    .title());
        }

        // below line is use to zoom our camera on map.
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng());
    }
}
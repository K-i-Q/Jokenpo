package com.example.jokenpo;

public class Jogada {
    public Integer jogador;
    public String escolha;
    public String latitude;
    public String longitude;
    public Integer getJogador() {
        return jogador;
    }

    public String getEscolha() {
        return escolha;
    }

    public void setEscolha(String escolha) {
        this.escolha = escolha;
    }




    public void setJogador(Integer jogador){
        this.jogador = jogador;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}

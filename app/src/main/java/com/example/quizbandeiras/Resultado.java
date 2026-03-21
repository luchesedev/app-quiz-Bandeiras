package com.example.quizbandeiras;

public class Resultado {
    private int Acertos;
    private String nome;

    public Resultado(int acertos, String nome) {
        this.Acertos = acertos;
        this.nome = nome;
    }
    public String getNome() { return nome; }
    public int getAcertos() { return Acertos; }


}

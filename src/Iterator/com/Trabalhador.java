package com;

public class Trabalhador {
    private String nome;
    
    public Trabalhador(String nome) {
        this.nome = nome;
    }

    public void imprimir() {
        System.out.println("Trabalhador: " + nome);
    }
        public String getNome() {
        return nome;
    }
}

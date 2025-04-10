package com;

import java.util.*;

public class IteradorDeBolinhas implements BolinhaIterator {
    private final CopoComBolinhas copo;
    private final List<Integer> indicesAleatorios;
    private int posicaoAtual = 0;

    public IteradorDeBolinhas(CopoComBolinhas copo) {
        this.copo = copo;
        this.indicesAleatorios = new ArrayList<>();

        for (int i = 0; i < copo.tamanho(); i++) {
            indicesAleatorios.add(i);
        }

        Collections.shuffle(indicesAleatorios);
        System.out.println("Ordem aleatoria definida: " + indicesAleatorios);
    }

    @Override
    public boolean temProxima() {
        boolean temMais = posicaoAtual < indicesAleatorios.size();
        System.out.println("Verificando se tem proxima bolinha: " + temMais);
        return temMais;
    }

    @Override
    public String proxima() {
        int indice = indicesAleatorios.get(posicaoAtual++);
        String cor = copo.get(indice);
        System.out.println("Pegou bolinha: " + cor + " (indice real: " + indice + ")");
        return cor;
    }
}

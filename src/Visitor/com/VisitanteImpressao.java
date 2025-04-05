package com;
public class VisitanteImpressao implements Visitante {
    @Override
    public void visitarTexto(Texto t) {
        System.out.println("Imprimindo texto: " + t.conteudo);
    }

    @Override
    public void visitarImagem(Imagem i) {
        System.out.println("Imprimindo imagem do caminho: " + i.caminho);
    }
}

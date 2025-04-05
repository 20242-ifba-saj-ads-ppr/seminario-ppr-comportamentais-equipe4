package com;
public class VisitanteExportacao implements Visitante {
    @Override
    public void visitarTexto(Texto t) {
        System.out.println("Exportando texto: " + t.conteudo);
    }

    @Override
    public void visitarImagem(Imagem i) {
        System.out.println("Exportando imagem do caminho: " + i.caminho);
    }
}

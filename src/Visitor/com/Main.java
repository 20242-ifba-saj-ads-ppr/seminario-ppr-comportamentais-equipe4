package com;
public class Main {
    public static void main(String[] args) {
        ElementoDocumento[] elementos = new ElementoDocumento[] {
            new Texto("Texto do Documento"),
            new Imagem("/imagens/logo.png")
        };

        Visitante visitanteImpressao = new VisitanteImpressao();
        Visitante visitanteExportacao = new VisitanteExportacao();

        System.out.println("--- Impressao ---");
        for (ElementoDocumento e : elementos) {
            e.aceitar(visitanteImpressao);
        }

        System.out.println("\n--- Exportacao ---");
        for (ElementoDocumento e : elementos) {
            e.aceitar(visitanteExportacao);
        }
    }
}
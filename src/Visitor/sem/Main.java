package semvisitor;

public class Main {
    public static void main(String[] args) {
        Texto texto = new Texto("Texto de exemplo");
        Imagem imagem = new Imagem("/imagens/logo.png");

        System.out.println("--- Impressão ---");
        texto.imprimir();
        imagem.imprimir();

        System.out.println("\n--- Exportação ---");
        texto.exportar();
        imagem.exportar();
    }
}
package semvisitor;

public class Imagem {
    public String caminho;

    public Imagem(String caminho) {
        this.caminho = caminho;
    }

    public void imprimir() {
        System.out.println("Imprimindo imagem do caminho: " + caminho);
    }

    public void exportar() {
        System.out.println("Exportando imagem do caminho: " + caminho);
    }
}

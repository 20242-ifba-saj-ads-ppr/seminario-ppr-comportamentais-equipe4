package semvisitor;

public class Texto {
    public String conteudo;

    public Texto(String conteudo) {
        this.conteudo = conteudo;
    }

    public void imprimir() {
        System.out.println("Imprimindo texto: " + conteudo);
    }

    public void exportar() {
        System.out.println("Exportando texto: " + conteudo);
    }
}

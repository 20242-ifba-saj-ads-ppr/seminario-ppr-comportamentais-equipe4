package elemento;
import visitor.Visitor;

public class Produto implements Elemento {
    private String nome = "Produto A";
    private double preco = 100;

    public String getNome() {
        return nome;
    }

    public double getPreco(){
        return preco;
    }

    public void exibirDetalhes() {
        System.out.println("Exibindo detalhes do produto: " + nome);
    }

    @Override
    public void aceitar(Visitor visitante) {
        visitante.visitar(this);
    }
}

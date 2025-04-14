package visitor;
import elemento.Produto;
import elemento.Servico;

public class VisitorVisualizacao implements Visitor {
    @Override
    public void visitar(Produto produto) {
        System.out.println("Visualizador está exibindo produto: " + produto.getNome());
        produto.exibirDetalhes();
    }

    @Override
    public void visitar(Servico servico) {
        System.out.println("Visualizador está exibindo serviço: " + servico.getDescricao());
        servico.executar();
    }
}
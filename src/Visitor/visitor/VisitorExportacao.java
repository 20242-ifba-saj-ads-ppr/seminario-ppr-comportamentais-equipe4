package visitor;
import elemento.Produto;
import elemento.Servico;

public class VisitorExportacao implements Visitor {
    @Override
    public void visitar(Produto produto) {
        System.out.println("Exportador está exportando produto: " + produto.getNome());
        System.out.println("Preço: " + produto.getPreco());
    }

    @Override
    public void visitar(Servico servico) {
        System.out.println("Exportador está exportando serviço: " + servico.getDescricao());
        System.out.println("Duração: " + servico.getDuracaoHoras() + " horas");
    }
}
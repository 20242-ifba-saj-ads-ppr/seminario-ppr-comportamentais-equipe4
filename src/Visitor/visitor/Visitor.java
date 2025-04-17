package visitor;

import elemento.Produto;
import elemento.Servico;

public interface Visitor {
    void visitar(Produto produto);

    void visitar(Servico servico);
}

package elemento;
import visitor.Visitor;

public interface Elemento {
    void aceitar(Visitor visitante);
}


package demo;
import java.util.ArrayList;
import java.util.List;
import elemento.Elemento;
import visitor.Visitor;

public class EstruturaObjeto {
    private List<Elemento> elementos = new ArrayList<>();

    public void addElemento(Elemento elemento) {
        elementos.add(elemento);
    }

    public void removeElemento(Elemento elemento) {
        elementos.remove(elemento);
    }

    public void aceitar(Visitor visitante) {
        for (Elemento elemento : elementos) {
            elemento.aceitar(visitante);
        }
    }
}

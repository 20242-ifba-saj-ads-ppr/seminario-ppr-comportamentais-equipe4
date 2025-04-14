package sem;

import java.util.ArrayList;
import java.util.List;

public class ListaTrabalhadores {
    private List<Trabalhador> trabalhadores = new ArrayList<>();

    public void adicionar(Trabalhador trabalhador) {
        trabalhadores.add(trabalhador);
    }

    public Trabalhador obter(int indice) {
        return trabalhadores.get(indice);
    }

    public int contar() {
        return trabalhadores.size();
    }

    public void imprimirTodos() {
        for (int i = 0; i < contar(); i++) {
            trabalhadores.get(i).imprimir();
        }
    }
}
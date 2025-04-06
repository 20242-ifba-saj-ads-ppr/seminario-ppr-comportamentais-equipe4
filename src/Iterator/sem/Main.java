package sem;

public class Main {
    public static void main(String[] args) {
        ListaNomes lista = new ListaNomes();
        lista.adicionar("Ana");
        lista.adicionar("Beatriz");
        lista.adicionar("Carlos");
        lista.adicionar("Daniel");
        lista.imprimirTodos();
    }
}
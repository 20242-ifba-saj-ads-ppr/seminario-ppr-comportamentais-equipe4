package sem;

public class Main {
    public static void main(String[] args) {
        ListaTrabalhadores lista = new ListaTrabalhadores();
        lista.adicionar(new Trabalhador("Ana"));
        lista.adicionar(new Trabalhador("Bruna"));
        lista.adicionar(new Trabalhador("Carlos"));
        lista.adicionar(new Trabalhador("Daniel"));
        
        lista.imprimirTodos();
    }
}

package sem;

public class ListaNomes {
    private String[] nomes = new String[10];
    private int contador = 0;

    public void adicionar(String nome) {
        nomes[contador++] = nome;
    }

    public void imprimirTodos() {
        for (int i = 0; i < contador; i++) {
            System.out.println(nomes[i]);
        }
    }
}

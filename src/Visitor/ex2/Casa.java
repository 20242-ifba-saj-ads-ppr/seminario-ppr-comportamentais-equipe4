package ex2;

public class Casa {
    public static void main(String[] args) {
        Comodo[] comodos = { new Sala(), new Cozinha() };
        Visitante eletricista = new Eletricista();

        for (Comodo comodo : comodos) {
            comodo.aceitar(eletricista);
        }
    }
}
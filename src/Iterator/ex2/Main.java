package ex2;

public class Main {
    public static void main(String[] args) {
        CopoComBolinhas copo = new CopoComBolinhas();
        copo.adicionarBolinha("Vermelha");
        copo.adicionarBolinha("Azul");
        copo.adicionarBolinha("Verde");
        copo.adicionarBolinha("Rosa");
        copo.adicionarBolinha("Preta");


        BolinhaIterator iterator = copo.criarIterator();

        System.out.println("Pegando bolinhas do copo:");

        while (iterator.temProxima()) {
            System.out.println("Retirou bolinha: " + iterator.proxima());
        }
    }
}
package ex2;

class Eletricista implements Visitante {
    @Override
    public void visitarSala(Sala sala) {
        System.out.println("Eletricista entrou na sala.");
        sala.verificarTomadas();
    }

    @Override
    public void visitarCozinha(Cozinha cozinha) {
        System.out.println("Eletricista entrou na cozinha.");
        cozinha.checarEletrodomesticos();
    }
}
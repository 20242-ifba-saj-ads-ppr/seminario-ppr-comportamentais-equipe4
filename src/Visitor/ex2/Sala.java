package ex2;

class Sala implements Comodo {
    @Override
    public void aceitar(Visitante visitante) {
        visitante.visitarSala(this);
    }

    public void verificarTomadas() {
        System.out.println("Verificando as tomadas da sala.");
    }
}
package ex2;

class Cozinha implements Comodo {
    @Override
    public void aceitar(Visitante visitante) {
        visitante.visitarCozinha(this);
    }

    public void checarEletrodomesticos() {
        System.out.println("Checando os eletrodomesticos da cozinha.");
    }
}
package elemento;

import visitor.Visitor;

public class Servico implements Elemento {
    private String descricao = "servico basico";
    private int duracaoHoras = 5;

 
    public String getDescricao() {
        return descricao;
    }

    public int getDuracaoHoras() {
        return duracaoHoras;
    }

    public void executar() {
        System.out.println("Executando serviço: " + descricao);
    }

    @Override
    public void aceitar(Visitor visitante) {
        visitante.visitar(this);
    }
}
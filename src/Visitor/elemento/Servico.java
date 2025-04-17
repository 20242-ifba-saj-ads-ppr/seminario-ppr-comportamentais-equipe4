package elemento;

import visitor.Visitor;

public class Servico implements Elemento {
    private String descricao;
    private int duracaoHoras;

 
    public Servico(String descricao, int duracaoHoras) {
        this.descricao = descricao;
        this.duracaoHoras = duracaoHoras;
    }

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
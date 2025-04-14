package demo;

import elemento.Produto;
import elemento.Servico;
import visitor.VisitorExportacao;
import visitor.VisitorVisualizacao;

public class DemoPadraoVisitor {
    public static void main(String[] args) {
        EstruturaObjeto estruturaObj = new EstruturaObjeto();
        estruturaObj.addElemento(new Produto());
        estruturaObj.addElemento(new Servico());

        VisitorVisualizacao visitorVisualizacao = new VisitorVisualizacao();
        VisitorExportacao visitorExportacao = new VisitorExportacao();

        System.out.println("Aplicando o VisitorVisualizacao:");
        estruturaObj.aceitar(visitorVisualizacao);

        System.out.println("\nAplicando o VisitorExportacao:");
        estruturaObj.aceitar(visitorExportacao);
    }
}

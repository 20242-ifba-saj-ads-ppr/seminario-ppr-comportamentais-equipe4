package demo;

import elemento.Produto;
import elemento.Servico;
import visitor.VisitorExportacao;
import visitor.VisitorVisualizacao;

public class DemoPadraoVisitor {
    public static void main(String[] args) {
        EstruturaObjeto estruturaObj = new EstruturaObjeto();
        estruturaObj.addElemento(new Produto("Produto A", 100))
        estruturaObj.addElemento(new Servico("Servico Basico", 5));

        VisitorVisualizacao visitorVisualizacao = new VisitorVisualizacao();
        VisitorExportacao visitorExportacao = new VisitorExportacao();

        System.out.println("Aplicando o VisitorVisualizacao:");
        estruturaObj.aceitar(visitorVisualizacao);

        System.out.println("\nAplicando o VisitorExportacao:");
        estruturaObj.aceitar(visitorExportacao);
    }
}

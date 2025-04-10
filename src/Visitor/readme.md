# Visitor

### Padrão de Projeto Comportamental

## Intenção
Descrever uma ação a ser realizada sobre os itens de um conjunto de objetos. O Visitor possibilita estabelecer uma nova funcionalidade sem alterar as classes dos componentes que serão processados.

## Motivação 

Imagine que você tem uma estrutura de objetos, como elementos de um documento (Texto e Imagem), e precisa realizar várias operações diferentes sobre eles — como imprimir, exportar, validar ou traduzir. Se cada operação for implementada diretamente nessas classes, elas acabam ficando sobrecarregadas e difíceis de manter. O padrão Visitor resolve isso ao permitir que essas operações sejam extraídas para classes separadas (visitantes), mantendo as classes de dados simples e focadas, e facilitando a adição de novas operações sem modificar os elementos existentes.

## Estrutura GOF
![image](https://github.com/user-attachments/assets/2d6a0438-05e9-4956-9078-752c92173c75)

## Aplicabilidade 
Utilize o padrão Visitor nos seguintes cenários:

- Quando uma coleção de objetos possui múltiplas classes com interfaces distintas e você precisa aplicar operações que variam conforme o tipo de cada objeto.

- Diversas operações independentes devem ser realizadas sobre os elementos de uma estrutura, e você quer evitar sobrecarregar suas classes com essas funcionalidades. O Visitor agrupa operações relacionadas em uma única classe visitante, mantendo o código organizado.

- A estrutura de objetos é estável, mas novas operações são frequentemente adicionadas. Se a hierarquia de classes muda constantemente, implementar as operações diretamente nelas pode ser mais eficiente, já que alterações na estrutura exigem ajustes em todos os visitantes.

## Código sem o Visitor

```java

public class Texto {
    public String conteudo;

    public Texto(String conteudo) {
        this.conteudo = conteudo;
    }

    public void imprimir() {
        System.out.println("Imprimindo texto: " + conteudo);
    }

    public void exportar() {
        System.out.println("Exportando texto: " + conteudo);
    }
}

public class Imagem {
    public String caminho;

    public Imagem(String caminho) {
        this.caminho = caminho;
    }

    public void imprimir() {
        System.out.println("Imprimindo imagem do caminho: " + caminho);
    }

    public void exportar() {
        System.out.println("Exportando imagem do caminho: " + caminho);
    }
}

public class Main {
    public static void main(String[] args) {
        Texto texto = new Texto("Texto de exemplo");
        Imagem imagem = new Imagem("/imagens/logo.png");

        System.out.println("--- Impressão ---");
        texto.imprimir();
        imagem.imprimir();

        System.out.println("\n--- Exportação ---");
        texto.exportar();
        imagem.exportar();
    }
}


```

## Cenário sem o Visitor

@startuml

class Texto {
  + conteudo: String
  + imprimir(): void
  + exportar(): void
}

class Imagem {
  + caminho: String
  + imprimir(): void
  + exportar(): void
}

class DocumentoSemVisitorApp {
  + main(args: String[]): void
}

DocumentoSemVisitorApp --> Texto : usa
DocumentoSemVisitorApp --> Imagem : usa

note right of Texto::imprimir
  Exibe o conteúdo do texto
end note

note right of Imagem::exportar
  Exporta o caminho da imagem
end note

@enduml

## Cenário Com o Visitor

@startuml

interface Visitante {
  + visitarTexto(t: Texto): void
  + visitarImagem(i: Imagem): void
}

interface ElementoDocumento {
  + aceitar(v: Visitante): void
}

class Texto implements ElementoDocumento {
  + aceitar(v: Visitante): void
  + conteudo: String
}

class Imagem implements ElementoDocumento {
  + aceitar(v: Visitante): void
  + caminho: String
}

class VisitanteImpressao implements Visitante {
  + visitarTexto(t: Texto): void
  + visitarImagem(i: Imagem): void
}

class VisitanteExportacao implements Visitante {
  + visitarTexto(t: Texto): void
  + visitarImagem(i: Imagem): void
}

Texto --> ElementoDocumento
Imagem --> ElementoDocumento

VisitanteImpressao ..|> Visitante
VisitanteExportacao ..|> Visitante

ElementoDocumento o--> Visitante : aceitar(v)

note right of Texto::aceitar
  v.visitarTexto(this)
end note

note right of Imagem::aceitar
  v.visitarImagem(this)
end note

note right of Visitante
  Uma operação por tipo de elemento
end note

@enduml

## Participantes
- **Visitante (Visitor — Visitante Abstrato):** Define uma interface para todas as operações que podem ser executadas sobre os elementos da estrutura de objetos.
- **ElementoDocumento (Element — Elemento Abstrato):** Define a interface de um elemento que aceita visitantes.
- **Texto (ConcreteElement — Texto Concreto):** Implementa a interface ElementoDocumento. Chama o método específico do visitante correspondente a seu tipo,   v.visitarTexto(this).
- **Imagem (ConcreteElement — Imagem Concreto):** Implementa a interface ElementoDocumento. Chama o método específico do visitante correspondente a seu tipo,   v.visitarImagem(this).
- **VisitanteImpressao (ConcreteVisitor — Visitante Concreto de Impressão):** Implementa a interface Visitante. Define as operações específicas para impressão.
- **VisitanteExportacao (ConcreteVisitor — Visitante Concreto de Exportação):** Implementa a interface Visitante. Define as operações específicas para exportação.

## Código Com o Visitor

```java
public interface Visitante {
    void visitarTexto(Texto t);
    void visitarImagem(Imagem i);
}

public interface ElementoDocumento {
    void aceitar(Visitante v);
}

public class Texto implements ElementoDocumento {
    public String conteudo;

    public Texto(String conteudo) {
        this.conteudo = conteudo;
    }

    @Override
    public void aceitar(Visitante v) {
        v.visitarTexto(this);
    }
}

public class Imagem implements ElementoDocumento {
    public String caminho;

    public Imagem(String caminho) {
        this.caminho = caminho;
    }

    @Override
    public void aceitar(Visitante v) {
        v.visitarImagem(this);
    }
}

public class VisitanteImpressao implements Visitante {
    @Override
    public void visitarTexto(Texto t) {
        System.out.println("Imprimindo texto: " + t.conteudo);
    }

    @Override
    public void visitarImagem(Imagem i) {
        System.out.println("Imprimindo imagem do caminho: " + i.caminho);
    }
}

public class VisitanteExportacao implements Visitante {
    @Override
    public void visitarTexto(Texto t) {
        System.out.println("Exportando texto: " + t.conteudo);
    }

    @Override
    public void visitarImagem(Imagem i) {
        System.out.println("Exportando imagem do caminho: " + i.caminho);
    }
}

public class Main {
    public static void main(String[] args) {
        ElementoDocumento[] elementos = new ElementoDocumento[] {
            new Texto("Texto com Visitor"),
            new Imagem("/imagens/logo.png")
        };

        Visitante visitanteImpressao = new VisitanteImpressao();
        Visitante visitanteExportacao = new VisitanteExportacao();

        System.out.println("--- Impressão ---");
        for (ElementoDocumento e : elementos) {
            e.aceitar(visitanteImpressao);
        }

        System.out.println("\n--- Exportação ---");
        for (ElementoDocumento e : elementos) {
            e.aceitar(visitanteExportacao);
        }
    }
}
```
## Colaborações
- Um código cliente que implementa o Visitor precisa instanciar um VisitanteConcreto e iterar pela estrutura de objetos, aplicando a visita a cada componente

- Durante a visita, cada elemento aciona o método do Visitor correspondente ao seu tipo específico. O elemento se passa como parâmetro para essa chamada, possibilitando ao visitante consultar seus atributos quando requerido
## Consequências

## Benefícios
- **Facilidade para adicionar novas operações:** Com o Visitor, novas funcionalidades podem ser implementadas criando uma nova classe visitante, sem modificar as classes existentes dos elementos. Isso evita alterações em múltiplas classes quando uma nova operação é necessária.

- **Organização de operações relacionadas:** O padrão agrupa comportamentos relacionados em um único visitante, evitando a dispersão de lógica pelas classes da estrutura. Isso melhora a manutenibilidade e a clareza do código.

- **Suporte a estruturas heterogêneas:** Diferentemente de um Iterator, o Visitor pode operar sobre objetos de tipos distintos, mesmo que não compartilhem uma hierarquia comum.

- **Acúmulo de estado durante a visita:** Visitantes podem armazenar informações enquanto percorrem a estrutura, eliminando a necessidade de variáveis globais ou parâmetros extras.

## Desvantagens
- **Dificuldade para adicionar novos tipos de elementos:** Se a estrutura de objetos cresce frequentemente, cada novo tipo exige a atualização de todos os visitantes existentes, o que pode se tornar trabalhoso.

- **Violação do encapsulamento:** Para que o Visitor funcione, os elementos podem precisar expor métodos públicos que acessem seu estado interno, reduzindo o encapsulamento.
  
## Usos conhecidos
- Compiladores: Em compiladores, ele é usado para percorrer árvores de sintaxe abstrata (ASTs) e realizar operações como análise semântica ou geração de código

- Ferramentas de análise de código:
Ferramentas como linters e formatadores aplicam visitantes para analisar diferentes tipos de nós em estruturas de código-fonte, sem precisar alterar as classes que representam esses nós.

- Aplicações com documentos estruturados:
Sistemas que trabalham com documentos compostos por diferentes tipos de elementos (como texto, imagens e tabelas) utilizam Visitor para aplicar funcionalidades como exportação, renderização ou verificação em cada tipo de elemento.

## Padrões relacionados
- Composite:
Visitor é frequentemente usado com o padrão Composite para executar operações sobre uma hierarquia de objetos (como árvores). O visitante percorre a estrutura composta e realiza ações específicas para cada tipo de nó.

- Interpreter:
Em interpretadores de linguagens, o Visitor pode ser usado para implementar a lógica de interpretação, onde cada tipo de expressão é visitado e processado de forma distinta.

## Conclusão
O padrão Visitor é especialmente útil quando há a necessidade de realizar múltiplas operações sobre uma estrutura de objetos complexa, como árvores ou composições de elementos, sem modificar suas classes. Ele promove a separação de responsabilidades ao encapsular comportamentos em visitantes, facilitando a adição de novas operações. No entanto, sua utilização deve considerar o custo de manutenção caso novos tipos de elementos sejam frequentemente adicionados, pois isso requer mudanças em todos os visitantes existentes. Ainda assim, em contextos onde as operações mudam mais do que a estrutura dos dados, o Visitor oferece uma solução prática e extensível.

## Referências
GAMMA, Erich; HELM, Richard; JOHNSON, Ralph; VLISSIDES, John. Padrões de Projeto: Soluções Reutilizáveis de Software Orientado a Objetos. 1. ed. Porto Alegre: Bookman, 2000.

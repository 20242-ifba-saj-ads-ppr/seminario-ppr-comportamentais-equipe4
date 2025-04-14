
# Iterator

### Padrão de Projeto Comportamental

## Intenção
Fornecer uma maneira de acessar sequencialmente os elementos de um objeto agregado sem expor sua representação subjacente.

## Motivação

Objetos agregados, tais como listas, devem fornecer alguma forma de acessar seus elementos sem que sua estrutura interna seja exposta. Sendo necessário muitas vezes que os dados sejam percorridos de formas diferentes, mas não se deseja inflar a interface da coleção com múltiplos métodos para isso.  
Este padrão transfere a responsabilidade da navegação para um objeto à parte, o Iterador. Este, por sua vez, define uma interface padronizada para acessar os elementos sequencialmente, mantendo internamente o estado do percurso.
Permitindo dessa forma qque diferentes políticas de navegação sem alterar a estrutura do agregado.

## Aplicabilidade

- Para acessar os conteúdos de um objeto agregado sem que representação interna seja exposta.
- Para possibilitar formas diferentes de percorrer dados (objetos agregados).
- Para fornecer uma interface uniforme para percorrer diferentes tipos de coleções.

## Estrutura GOF
![image](https://github.com/user-attachments/assets/2b88b194-a588-43e2-82bb-1fc7f90405a8)

## Participantes

- **Iterator (Iterator):** Define a interface com os métodos para percorrer elementos de uma coleção (hasNext, next).
- **ConcreteIterator (ListaNomesIterator):** Implementa a interface Iterator e mantém o controle da posição atual na coleção.
- **Aggregate (IterableCollection):** Define uma interface para criar um objeto Iterator.
- **ConcreteAggregate (ListaNomes):** Implementa a interface de agregação, armazena os dados (nomes) e fornece um iterador para percorrê-los.


## Código sem o Iterator

```java

import java.util.ArrayList;
import java.util.List;

public class Trabalhador {
    private String nome;
    
    public Trabalhador(String nome) {
        this.nome = nome;
    }

    public void imprimir() {
        System.out.println("Trabalhador: " + nome);
    }
}

public class ListaTrabalhadores {
    private List<Trabalhador> trabalhadores = new ArrayList<>();

    public void adicionar(Trabalhador trabalhador) {
        trabalhadores.add(trabalhador);
    }

    public Trabalhador obter(int indice) {
        return trabalhadores.get(indice);
    }

    public int contar() {
        return trabalhadores.size();
    }

    public void imprimirTodos() {
        for (int i = 0; i < contar(); i++) {
            trabalhadores.get(i).imprimir();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ListaTrabalhadores lista = new ListaTrabalhadores();
        lista.adicionar(new Trabalhador("Ana"));
        lista.adicionar(new Trabalhador("Bruna"));
        lista.adicionar(new Trabalhador("Carlos"));
        lista.adicionar(new Trabalhador("Daniel"));

        
        lista.imprimirTodos();
    }
}

```

## Código Com o Iterator

```java
import java.util.ArrayList;
import java.util.List;


public interface Agregado<T> {
    Iterador<T> criarIterador();
}

interface Iterador<T> {
    void primeiro();
    void proximo();
    boolean acabou();
    T itemAtual();
}

public class IteradorTrabalhadores implements Iterador<Trabalhador> {
    private ListaTrabalhadores lista;
    private int atual = 0;

    public IteradorTrabalhadores(ListaTrabalhadores lista) {
        this.lista = lista;
    }

    @Override
    public void primeiro() {
        atual = 0;
    }

    @Override
    public void proximo() {
        atual++;
    }

    @Override
    public boolean acabou() {
        return atual >= lista.contar();
    }

    @Override
    public Trabalhador itemAtual() {
        if (acabou()) {
            throw new IllegalStateException("Iterador fora dos limites.");
        }
        return lista.obter(atual);
    }
}


public class ListaTrabalhadores implements Agregado<Trabalhador> {
    private List<Trabalhador> trabalhadores = new ArrayList<>();

    public void adicionar(Trabalhador trabalhador) {
        trabalhadores.add(trabalhador);
    }

    public Trabalhador obter(int indice) {
        return trabalhadores.get(indice);
    }

    public int contar() {
        return trabalhadores.size();
    }

    @Override
    public Iterador<Trabalhador> criarIterador() {
        return new IteradorTrabalhadores(this);
    }
}

public class Main {
    public static void imprimirTrabalhadores(Iterador<Trabalhador> iterador) {
        for (iterador.primeiro(); !iterador.acabou(); iterador.proximo()) {
            iterador.itemAtual().imprimir();
        }
    }

    public static void main(String[] args) {
        ListaTrabalhadores lista = new ListaTrabalhadores();
        lista.adicionar(new Trabalhador("Ana"));
        lista.adicionar(new Trabalhador("Bruna"));
        lista.adicionar(new Trabalhador("Carlos"));
        lista.adicionar(new Trabalhador("Daniel"));

        Iterador<Trabalhador> iterador = lista.criarIterador();
        imprimirTrabalhadores(iterador);
    }
}

public class Trabalhador {
    private String nome;
    
    public Trabalhador(String nome) {
        this.nome = nome;
    }

    public void imprimir() {
        System.out.println("Trabalhador: " + nome);
    }
}

```

## Cenário sem o Iterator
``` mermaid 
classDiagram
    class Trabalhador {
        - String nome
        + imprimir()
    }

    class ListaTrabalhadores {
        - List~Trabalhador~ trabalhadores
        + adicionar(Trabalhador)
        + obter(int): Trabalhador
        + contar(): int
        + imprimirTodos()
    }

    class Main {
        + main(String[])
    }

    Main --> ListaTrabalhadores : usa
    ListaTrabalhadores --> Trabalhador : contém

```

## Cenário Com o Iterator
``` mermaid 

classDiagram
    class Iterador~T~ {
        + primeiro()
        + proximo()
        + acabou(): boolean
        + itemAtual(): T
    }

    class Agregado~T~ {
        + criarIterador(): Iterador~T~
    }

    class Trabalhador {
        - String nome
        + imprimir()
    }

    class ListaTrabalhadores {
        - List~Trabalhador~ trabalhadores
        + adicionar(Trabalhador)
        + obter(int): Trabalhador
        + contar(): int
        + criarIterador(): Iterador~Trabalhador~
    }

    class IteradorTrabalhadores {
        - ListaTrabalhadores lista
        - int atual
        + primeiro()
        + proximo()
        + acabou(): boolean
        + itemAtual(): Trabalhador
    }

    class Main {
        + main(String[])
        + imprimirTrabalhadores(Iterador~Trabalhador~)
    }

    ListaTrabalhadores --> Trabalhador : contém
    ListaTrabalhadores ..|> Agregado~Trabalhador~
    IteradorTrabalhadores ..|> Iterador~Trabalhador~
    ListaTrabalhadores --> IteradorTrabalhadores : cria
    IteradorTrabalhadores --> ListaTrabalhadores : itera
    Main --> Agregado~Trabalhador~ : usa
    Main --> Iterador~Trabalhador~ : usa

```

  


## Consequências

### Benefícios:
- **Flexibilidade na iteração:** Diferentes formas de percorrer uma coleção podem ser facilmente aplicadas, bastando trocar o iterador ou criar variações dele.
- **Interface simplificada:** A lógica de navegação é isolada no iterador, mantendo a coleção com uma interface mais limpa.
- **Iterações paralelas:** É possível realizar múltiplos percursos simultâneos, já que cada iterador gerencia seu próprio estado.
  
### Desvantagens:
- **Mais objetos criados:** Em estruturas com iteração simples, a criação de objetos Iterator pode parecer um overhead desnecessário.
- **Exposição parcial de elementos internos:** Mesmo que a estrutura completa não seja revelada, os elementos ainda são acessíveis diretamente pelo iterador.

## Usos conhecidos

- **Java:** Implementações como java.util.Iterator e java.util.Enumeration permitem iterar sobre coleções como listas, mapas e conjuntos..
- **.NET (C#):** A interface IEnumerable permite o uso do foreach, que usa internamente um iterador.
- **Python:** A implementação de métodos como __iter__() e __next__() permite criar iteradores personalizados para qualquer tipo de coleção.

## Padrões relacionados

- **Composite:** Iterator é frequentemente usado para percorrer estruturas compostas de forma uniforme.
- **Factory Method:** Pode ser usado para instanciar diferentes iteradores dependendo da estrutura.
- **Visitor:** Pode ser combinado ao Iterator para aplicar operações em cada elemento visitado.

## Conclusão
O padrão Iterator é uma solução para percorrer coleções sem precisar conhecer sua estrutura interna. Ele facilita a leitura e manutenção do código ao separar a lógica de iteração da estrutura dos dados. Além disso, permite criar diferentes formas de percorrer uma coleção, até mesmo simultaneamente, sem complicar a implementação.

## Referências
GAMMA, Erich; HELM, Richard; JOHNSON, Ralph; VLISSIDES, John. *Padrões de Projeto: Soluções Reutilizáveis de Software Orientado a Objetos*. 1. ed. Porto Alegre: Bookman, 2000.

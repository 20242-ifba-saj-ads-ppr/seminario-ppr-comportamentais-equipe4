
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
public class ListaNomes {
    private String[] nomes = new String[10];
    private int contador = 0;

    public void adicionar(String nome) {
        nomes[contador++] = nome;
    }

    public void imprimirTodos() { 
        for (int i = 0; i < contador; i++) {
            System.out.println(nomes[i]);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ListaNomes lista = new ListaNomes();
        lista.adicionar("Ana");
        lista.adicionar("Beatriz");
        lista.adicionar("Carlos");
        lista.adicionar("Daniel");
        lista.imprimirTodos();
    }
}
```

## Código Com o Iterator

```java
public interface Iterator {
    boolean hasNext();
    Object next();
}

public interface IterableCollection {
    Iterator criarIterator();
}

public class ListaNomes implements IterableCollection {
    private String[] nomes = new String[10];
    private int contador = 0;

    public void adicionar(String nome) {
        nomes[contador++] = nome;
    }

    public String get(int index) {
        return nomes[index];
    }

    public int tamanho() {
        return contador;
    }

    @Override
    public Iterator criarIterator() {
        return new ListaNomesIterator(this);
    }
}

public class ListaNomesIterator implements Iterator {
    private ListaNomes lista;
    private int posicao = 0;

    public ListaNomesIterator(ListaNomes lista) {
        this.lista = lista;
    }

    @Override
    public boolean hasNext() {
        return posicao < lista.tamanho();
    }

    @Override
    public Object next() {
        return lista.get(posicao++);
    }
}

public class Main {
    public static void main(String[] args) {
        ListaNomes lista = new ListaNomes();
        lista.adicionar("Ana");
        lista.adicionar("Beatriz");
        lista.adicionar("Carlos");
        lista.adicionar("Daniel");

        Iterator iterador = lista.criarIterator();

        while (iterador.hasNext()) {
            System.out.println(iterador.next());
        }
    }
}
```

## Cenário sem o Iterator
``` mermaid 
classDiagram

class ListaNomes {
  -nomes: String[]
  -contador: int
  +adicionar(nome: String): void
  +imprimirTodos(): void
}

class Main {
  +main(args: String[]): void
}

Main --> ListaNomes
```

## Cenário Com o Iterator
``` mermaid 
classDiagram
    class Iterator {
        +hasNext(): boolean
        +next(): Object
    }

    class IterableCollection {
        +criarIterator(): Iterator
    }

    class ListaNomes {
        -nomes: String[]
        -contador: int
        +adicionar(nome: String): void
        +criarIterator(): Iterator
    }

    class ListaNomesIterator {
        -lista: ListaNomes
        -posicao: int
        +hasNext(): boolean
        +next(): Object
    }

    ListaNomes --> IterableCollection
    ListaNomesIterator ..|> Iterator
    ListaNomes --> ListaNomesIterator
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

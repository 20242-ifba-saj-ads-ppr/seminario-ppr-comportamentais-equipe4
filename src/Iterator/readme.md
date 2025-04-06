
# Iterator

### Padrão de Projeto Comportamental

## Intenção
Fornecer uma maneira de acessar sequencialmente os elementos de um objeto agregado sem expor sua representação subjacente.

## Motivação

Considere uma estrutura de coleção (como uma lista de objetos, um vetor ou uma árvore). Frequentemente, queremos acessar seus elementos um por um, sem saber como a coleção está implementada internamente.

Se a lógica de iteração estiver embutida diretamente na estrutura da coleção, o código torna-se difícil de reutilizar e manter. O padrão Iterator resolve isso separando o comportamento de iteração da estrutura de dados, permitindo que diferentes tipos de coleções sejam percorridos de maneira uniforme e independente da implementação.

## Aplicabilidade
Use o padrão Iterator quando:

- Você precisa acessar o conteúdo de uma coleção sem expor sua estrutura interna (listas, árvores, etc.).
- Deseja oferecer múltiplas variações de percorrimento (por exemplo, direto, reverso).
- Precisa ter múltiplos iteradores percorrendo a mesma coleção ao mesmo tempo.

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

## Cenário sem o Iterator

@startuml
class ListaNomes {
  - nomes: String[]
  - contador: int
  + adicionar(nome: String): void
  + imprimirTodos(): void
}

class Main {
  + main(args: String[]): void
}

Main --> ListaNomes : usa

note right of ListaNomes::imprimirTodos
  Iteração está acoplada à estrutura
end note
@enduml

## Cenário Com o Iterator

@startuml
interface Iterator {
  + hasNext(): boolean
  + next(): Object
}

interface IterableCollection {
  + criarIterator(): Iterator
}

class ListaNomes implements IterableCollection {
  - nomes: String[]
  - contador: int
  + adicionar(nome: String): void
  + criarIterator(): Iterator
}

class ListaNomesIterator implements Iterator {
  - lista: ListaNomes
  - posicao: int
  + hasNext(): boolean
  + next(): Object
}

ListaNomes --> IterableCollection
ListaNomesIterator ..|> Iterator
ListaNomes --> ListaNomesIterator : cria
@enduml

## Participantes

- **Iterator:** Define a interface para acessar e percorrer elementos (ex.: hasNext, next).
- **ConcreteIterator:** Implementa a interface Iterator e mantém o estado da iteração sobre os elementos.
- **IterableCollection:** Define uma interface para criar um objeto Iterator.
- **ListaNomes:** Implementa a interface de agregação e retorna um iterador apropriado.

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

## Consequências

### Benefícios:
- **Isola a lógica de iteração:** Evita expor detalhes internos da coleção.
- **Suporta múltiplas iterações simultâneas:** Você pode ter vários iteradores atuando sobre a mesma coleção ao mesmo tempo.
- **Código reutilizável:** A lógica de iteração pode ser reaproveitada em diferentes tipos de coleções.
- **Facilidade para mudar o tipo de coleção:** Como o cliente depende da interface do Iterator, é possível trocar a implementação da coleção com impacto mínimo.

### Desvantagens:
- **Iteração unidirecional:** Cobre apenas iteração direta (para frente); para navegar para trás, é necessário extender.
- **Mais objetos criados:** Em estruturas com iteração simples, a criação de objetos Iterator pode parecer um overhead desnecessário.
- **Exposição parcial de elementos internos:** Mesmo que a estrutura completa não seja revelada, os elementos ainda são acessíveis diretamente pelo iterador.

## Usos conhecidos

- **Coleções Java (Java Collections Framework):** Todas as coleções (List, Set, Map) implementam a interface Iterable que fornece um Iterator.
- **.NET (C#):** A interface IEnumerable permite o uso do foreach, que usa internamente um iterador.
- **Python:** Todo objeto iterável implementa os métodos __iter__() e __next__().

## Padrões relacionados

- **Composite:** Iterator é frequentemente usado para percorrer estruturas compostas de forma uniforme.
- **Factory Method:** Pode ser usado para instanciar diferentes iteradores dependendo da estrutura.
- **Visitor:** Pode ser combinado ao Iterator para aplicar operações em cada elemento visitado.

## Conclusão
O padrão Iterator permite desacoplar a lógica de percorrimento da estrutura dos dados. Ele oferece uma interface uniforme para percorrer diferentes tipos de coleções sem expor sua implementação interna. Embora simples, ele é amplamente utilizado em bibliotecas modernas. Ao promover a separação de responsabilidades, melhora a coesão e reutilização do código, sendo ideal para cenários onde a iteração precisa ser flexível e independente.

## Referências
GAMMA, Erich; HELM, Richard; JOHNSON, Ralph; VLISSIDES, John. *Padrões de Projeto: Soluções Reutilizáveis de Software Orientado a Objetos*. 1. ed. Porto Alegre: Bookman, 2000.

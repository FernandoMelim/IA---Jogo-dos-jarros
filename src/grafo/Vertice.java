package Grafo;

import Utilidade.ListaVertices;
import States.IState;
import States.Estado;

public class Vertice {

    private Vertice proximo;
    private Vertice proximoDaListaVertices;
    private Vertice proximoDaFila;
    private ListaEncadArestas arestasDesteVertice;
    ListaVertices parentes; // lista com cópia dos vértices de todos os antescedentes desse vértice
    // custo da raiz até esse nó utilizando o caminho que se encontra na lista 'parentes'

    IState state;

    public Vertice() {
    }

    public Vertice(IState state) {

        this.setProxListaVertices(null);
        this.setProxFila(null);
        this.setProximo(null);
        arestasDesteVertice = new ListaEncadArestas();
        this.parentes = new ListaVertices();
        this.state = new Estado(state.getNivel(), state.getLimiteMaximoJarro(), state.getnJarros(), state.getValorDeChegada(), state.getHeuristica(), state.getfN());

    }

    public void copiaPilha(Vertice pai) {

        if (pai != this) {
            for (Vertice a = pai.getParentes().getPrimeiro(); a != null; a = a.getProxListaVertices()) {
                IState b = new Estado(a.getState().getNivel(),
                        a.getState().getLimiteMaximoJarro(),
                        a.getState().getnJarros(),
                        a.getState().getValorDeChegada(),
                        a.getState().getHeuristica(),
                        a.getState().getfN());
                Vertice copia = new Vertice(b);
                for (int i = 0; i < pai.getState().getnJarros(); i++) {
                    copia.getState().setEstadoJarro(i, a.getState().getEstadoJarro(i));
                }
                this.parentes.adicionarFinal(copia);

            }

            IState b = new Estado(this.getParentes().getUltimo().getState().getNivel() + 1,
                    this.getParentes().getUltimo().getState().getLimiteMaximoJarro(),
                    this.getParentes().getUltimo().getState().getnJarros(),
                    this.getState().getValorDeChegada(),
                    this.getState().getHeuristica(),
                    this.getState().getfN());
            Vertice copia = new Vertice(b);
            for (int i = 0; i < pai.getState().getnJarros(); i++) {
                copia.getState().setEstadoJarro(i, this.getState().getEstadoJarro(i));
            }
            this.parentes.adicionarFinal(copia);

        } else {

            if (this == pai) {
                IState b = new Estado(this.getState().getNivel(),
                        this.getState().getLimiteMaximoJarro(),
                        this.getState().getnJarros(),
                        this.getState().getValorDeChegada(),
                        this.getState().getHeuristica(),
                        this.getState().getfN());
                Vertice filho = new Vertice(b);
                for (int i = 0; i < this.getState().getnJarros(); i++) {
                    filho.getState().setEstadoJarro(i, this.getState().getEstadoJarro(i));
                }
                this.parentes.adicionarFinal(filho);
            }
        }
    }

    public IState getState() {
        return state;
    }

    public ListaVertices getParentes() {
        return parentes;
    }

    public void setProximo(Vertice proximo) {
        this.proximo = proximo;
    }

    public Vertice getProximo() {
        return proximo;
    }

    public void setAresta(Vertice vertice, int peso) {
        this.arestasDesteVertice.setAresta(vertice, peso);
    }

    public Aresta getPrimAresta() {
        return this.arestasDesteVertice.getPrimeira();
    }

    public void printArestas() {

        this.arestasDesteVertice.printArestas();
    }

    public void printEstado() {
        for (int i = 0; i < this.state.getnJarros(); i++) {

            System.out.println("Jarro " + (i+1) + " tem " + this.state.getJarrosByIndex(i).getWater() + " litros.");
        }
    }

    // Daqui para baixo são métodos para a pilha 
    public void setProxListaVertices(Vertice v) {
        this.proximoDaListaVertices = v;
    }

    public Vertice getProxListaVertices() {
        return proximoDaListaVertices;
    }

    // Daqui para baixo são métodos para a fila
    public void setProxFila(Vertice v) {
        this.proximoDaFila = v;
    }

    public Vertice getProxFila() {
        return proximoDaFila;
    }
}

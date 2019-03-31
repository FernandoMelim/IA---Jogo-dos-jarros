package Grafo;

public class ListaEncadVertices {

    private Vertice primeiro;
    private Vertice ultimo;

    public void setPrimeiro(Vertice primeiro) {
        this.primeiro = primeiro;
    }

    public void setUltimo(Vertice ultimo) {
        this.ultimo = ultimo;
    }

    public Vertice getUltimo() {
        return ultimo;
    }

    public boolean checkIfListaVazia() {
        return this.primeiro == null;

    }

    public Vertice getPrimeiro() {
        return this.primeiro;
    }

    public void setVertice(Vertice v) {
        Vertice novo = v;

        if (this.checkIfListaVazia()) {
            this.primeiro = novo;
            this.ultimo = this.primeiro;
            this.ultimo.setProximo(null);
        } else //Lista contem ao menos 1 vertice
        {
            this.ultimo.setProximo(novo);
            this.ultimo = novo;
            this.ultimo.setProximo(null);
        }
    }

    public void setAresta(Vertice um, Vertice dois, int peso) {
        um.setAresta(dois, peso);
    }
}

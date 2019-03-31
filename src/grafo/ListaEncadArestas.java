package Grafo;

public class ListaEncadArestas {

    private Aresta primeira = null;
    private Aresta ultima = null;
  

    public boolean listaVazia() {
        if (this.primeira == null) {
            //System.out.println("Lista está vazia");
            return true;
        }
        return false;
    }

    public void setAresta(Vertice v, int peso) {
        Aresta aresta = new Aresta();
        aresta.setVerticeDestino(v);
        aresta.setPeso(peso);

        if (this.listaVazia()) {
            this.primeira = aresta;
            this.ultima = aresta;
        } else //Lista contem ao menos 1 vertice
        {
            this.ultima.setProxima(aresta);
            this.ultima = aresta;
            this.ultima.setProxima(null);
        }

    }


    public Aresta getPrimeira() {
        return this.primeira;
    }

    public void printArestas() {
        Aresta aresta;
        if (!this.listaVazia()) {
            int i = 1;
            for (aresta = this.primeira; aresta != null; aresta = aresta.getProxima()) {
                System.out.println("Aresta " + i + "Peso: " + aresta.getPeso());
                i++;
            }
        } else {
            //System.out.println("Nao possui arestas com nenhum outro vertice no momento, ou seja, estah isolado");
        }
    }

    public Aresta getUltima() {
        return this.ultima;
    }
}

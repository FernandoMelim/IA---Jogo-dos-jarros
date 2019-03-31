package Grafo;

public class Aresta {

    private Vertice verticeDestino;
    private Aresta proxima;
    int peso;

    public void setProxima(Aresta proxima) {
        this.proxima = proxima;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setVerticeDestino(Vertice verticeDestino) {
        this.verticeDestino = verticeDestino;
    }

    public int getPeso() {
        return peso;
    }

    public Aresta getProxima() {
        return proxima;
    }

    public Vertice getVerticeDestino() {
        return verticeDestino;
    }
}

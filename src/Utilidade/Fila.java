package Utilidade;

import Grafo.Vertice;

public class Fila {

    private Vertice primeiro;
    private Vertice ultimo;
    private int nNos;

    public Fila() {
        this.primeiro = null;
        this.ultimo = null;
        this.nNos = 0;
    }

    public Vertice getPrimeiro() {
        return primeiro;
    }

    public boolean ehVazia() {
        if (this.primeiro == null) {
            return true;
        } else {
            return false;
        }

    }

    public void adicionar(Vertice v) {
        if (this.primeiro == null) {
            this.primeiro = v;
            this.ultimo = v;
            this.primeiro.setProxFila(null);
            this.ultimo.setProxFila(null);
            this.nNos += 1;
        } else {
            if (this.primeiro == this.ultimo) {
                this.ultimo = v;
                this.primeiro.setProxFila(this.ultimo);
                this.ultimo.setProxFila(null);
                this.nNos += 1;
            } else {
                this.ultimo.setProxFila(v);
                this.ultimo = v;
                this.ultimo.setProxFila(null);
                this.nNos += 1;
            }
        }
    }

    public void excluir() {
        if (primeiro == null) {
            System.out.println("Fila vazia");
        } else {
            if (this.primeiro == this.ultimo) {
                this.primeiro.setProxFila(null);
                this.ultimo.setProxFila(null);
                this.primeiro = null;
                this.ultimo = null;
                this.nNos -= 1;

            } else {
                Vertice v = this.primeiro;
                this.primeiro = this.primeiro.getProxFila();
                v.setProxFila(null);
                this.nNos -= 1;

            }
        }
    }

    public void excluirTudo() {
        this.primeiro = null;
        this.primeiro.setProxFila(null);
        this.ultimo = null;
        this.ultimo.setProxFila(null);
        this.nNos = 0;
    }

    public void imprimeFila() {

        int i = 0;
        int custo = 0;
        System.out.println("Caminho: ");
        for (Vertice v = this.primeiro; v != null; v = v.getProxFila()) {
            System.out.println("Vertice " + i + " do Nível: " + v.getState().getNivel());
            v.printEstado();
            i++;
        }
    }

    public int getnNos() {
        return nNos;
    }
    
    
}

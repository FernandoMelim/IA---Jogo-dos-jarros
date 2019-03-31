package Utilidade;

import Grafo.Vertice;

public class ListaVertices {

    private Vertice primeiro;
    private Vertice ultimo;
    private int nNos;

    public ListaVertices() {
        this.primeiro = null;
        this.ultimo = null;
        this.nNos = 0;
    }

    public int getnNos() {
        return nNos;
    }

    public Vertice getPrimeiro() {
        return primeiro;
    }

    public void adicionarFinal(Vertice v) {
        if (this.primeiro == null) {
            this.primeiro = v;
            this.ultimo = v;
            this.primeiro.setProxListaVertices(null);
            this.ultimo.setProxListaVertices(null);
            this.nNos += 1;
        } else {
            if (this.primeiro == this.ultimo) {
                this.ultimo = v;
                this.primeiro.setProxListaVertices(this.ultimo);
                this.ultimo.setProxListaVertices(null);
                this.nNos += 1;
            } else {
                this.ultimo.setProxListaVertices(v);
                this.ultimo = v;
                this.ultimo.setProxListaVertices(null);
                this.nNos += 1;
            }
        }
    }

    public void excluir(Vertice v) {
        if (this.primeiro == this.ultimo) {
            if (this.primeiro == v) {
                this.excluirFinal();
            }
        } else {
            if (this.primeiro == v) {
                this.primeiro = this.primeiro.getProxListaVertices();
                v.setProxListaVertices(null);
                this.nNos -= 1;

            } else {
                boolean achou = false;
                Vertice a = this.primeiro;
                for (; a != null; a = a.getProxListaVertices()) {
                    if (a.getProxListaVertices() == v) {
                        achou = true;
                        break;
                    }
                }

                if (achou) {
                    if (this.ultimo == v) {
                        this.excluirFinal();
                    } else {
                        a.setProxListaVertices(v.getProxListaVertices());
                        v.setProxListaVertices(null);
                        this.nNos -= 1;
                    }
                }
            }
        }
    }

    public void excluirFinal() {
        if (primeiro == null) {
            System.out.println("Pilha vazia");
        } else {
            if (this.primeiro == this.ultimo) {
                this.primeiro.setProxListaVertices(null);
                this.ultimo.setProxListaVertices(null);
                this.primeiro = null;
                this.ultimo = null;
                this.nNos -= 1;

            } else {
                Vertice a = this.primeiro;
                for (; a != null; a = a.getProxListaVertices()) {
                    if (a.getProxListaVertices() == this.ultimo) {
                        break;
                    }
                }
                this.ultimo = a;
                this.ultimo.setProxListaVertices(null);
                this.nNos -= 1;
            }
        }

    }

    /*public void desempilharTudo() {
        this.inicio = null;
        this.inicio.setProxPilha(null);
        this.topo = null;
        this.topo.setProxPilha(null);
    }*/
    public void imprimeLista() {
        int i = 0;
        int custo = 0;
        System.out.println("Caminho: ");
        for (Vertice v = this.primeiro; v != null; v = v.getProxListaVertices()) {
            System.out.println("Nivel: " + v.getState().getNivel()
                    + " f(n): " + v.getState().getfN()
                    + " Heuristica: " + v.getState().getHeuristica()
                    + " Custo até a raiz: " + v.getState().getValorDeChegada());
            v.printEstado();
            i++;
        }
    }

    public boolean verificarNo(Vertice v) {

        boolean[] retorno = new boolean[v.getState().getnJarros()];

        for (int i = 0; i < retorno.length; i++) {
            retorno[i] = false;
        }

        for (Vertice a = this.primeiro; a != null; a = a.getProxListaVertices()) {
            for (int i = 0; i < v.getState().getnJarros(); i++) {
                if (a.getState().getJarrosByIndex(i).getWater() == v.getState().getJarrosByIndex(i).getWater()) {
                    retorno[i] = true;
                }
            }
            if (verificaVetorBooleano(retorno)) {
                return true;
            } else {
                for (int i = 0; i < retorno.length; i++) {
                    retorno[i] = false;
                }
            }
        }
        return false;
    }

    private boolean verificaVetorBooleano(boolean[] um) {

        for (int i = 0; i < um.length; i++) {
            if (um[i] == false) {
                return false;
            }
        }
        return true;
    }

    public Vertice getUltimo() {
        return this.ultimo;
    }

    public boolean ehVazia() {
        if (this.primeiro == null) {
            return true;
        } else {
            return false;
        }
    }
}

package Grafo;

public class Grafo {

    private ListaEncadVertices verticesDesteGrafo = new ListaEncadVertices();
    private int numeroDeVertices;
    private int numeroDeArestas;

    public Grafo() {
    }

    public int getNumeroDeArestas() {
        return numeroDeArestas;
    }

    public int getNumeroDeVertices() {
        return numeroDeVertices;
    }

    public void setAresta(Vertice um, Vertice dois, int peso) {
        if (this.verificaExistenciaVertice(um) && this.verificaExistenciaVertice(dois)) {
            this.verticesDesteGrafo.setAresta(um, dois, peso);
            this.numeroDeArestas++;
        } else {
            System.out.println("Algum dos vértices não existe.");
        }
    }

    public void setVertice(Vertice v) {
        this.verticesDesteGrafo.setVertice(v);
        this.numeroDeVertices++;

    }

    public void printGrafo() {
        if (this.numeroDeVertices > 0) {
            for (Vertice v = verticesDesteGrafo.getPrimeiro(); v != null; v = v.getProximo()) {
                v.printArestas();
            }
        } else {
            System.out.println("Grafo vazio");
        }
    }

    public void deletaGrafo() {
        this.verticesDesteGrafo.getPrimeiro().setProximo(null);
        this.verticesDesteGrafo.getUltimo().setProximo(null);
        this.verticesDesteGrafo.setPrimeiro(null);
        this.verticesDesteGrafo.setUltimo(null);
    }

    public ListaEncadVertices getVerticesDesteGrafo() {
        return verticesDesteGrafo;
    }

    private boolean verificaSeNoEhFolha(Vertice v) {
        if (v.getPrimAresta() == null) {
            return true;
        } else {
            return false;
        }
    }

    // Verifica se o estado do vértice passado como parâmetro está presente no grafo
    private boolean verificaExistenciaVertice(Vertice v) {
        for (Vertice a = this.verticesDesteGrafo.getPrimeiro(); a != null; a = a.getProximo()) {
            if (v == a) {
                return true;
            }
        }

        return false;
    }

    // Essa função verifica um vetor de booleanos
    // Retorna true se todos os valores forem true
    // retorna false caso contrário
    private boolean verificaVetorBooleano(boolean[] um) {

        for (int i = 0; i < um.length; i++) {
            if (um[i] == false) {
                return false;
            }
        }
        return true;
    }

    // Verifica se estado passado pelo vertice "v" está na árvore
    private boolean verificaExistenciaEstado(Vertice v) {
        boolean[] retorno = new boolean[v.getState().getnJarros()];

        for (int i = 0; i < retorno.length; i++) {
            retorno[i] = false;
        }

        for (Vertice a = this.verticesDesteGrafo.getPrimeiro(); a != null; a = a.getProximo()) {
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

    // Verifica se o array "objetivo" é igual ao conteúdo de cada jarro presente no vértice.
    // Se for, retorna true.
    public boolean verificaSolucao(int[] objetivo, Vertice v) {
        boolean[] retorno = new boolean[v.getState().getnJarros()];

        for (int i = 0; i < v.getState().getnJarros(); i++) {
            retorno[i] = false;
        }

        for (int i = 0; i < v.getState().getnJarros(); i++) {
            if (objetivo[i] == v.getState().getJarrosByIndex(i).getWater()) {
                retorno[i] = true;
            }
        }

        for (int i = 0; i < retorno.length; i++) {
            if (retorno[i] == false) {
                return false;
            }
        }

        return true;

    }

    // Essa função verifica se dois nós possuem estados iguais, retorna true se possuirem
     public boolean estadosIguais(Vertice a, Vertice b) {
        boolean[] retorno = new boolean[a.getState().getnJarros()];

        for (int i = 0; i < retorno.length; i++) {
            retorno[i] = false;
        }

        for (int i = 0; i < a.getState().getnJarros(); i++) {
            if (a.getState().getJarrosByIndex(i) == b.getState().getJarrosByIndex(i)) {
                retorno[i] = true;
            }

        }

        if (this.verificaVetorBooleano(retorno)) {
            return true;
        } else {
            return false;
        }
    }
}

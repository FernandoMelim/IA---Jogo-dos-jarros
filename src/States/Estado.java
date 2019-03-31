package States;

import Jarro.Jarro;

public class Estado implements IState {

    private int nivel;
    private Jarro[] jarros;
    private boolean raiz = false;
    private int limiteMaximoJarro[];
    private int nJarros;
    private int valorDeChegada; // valor da soma de todas as arestas da raiz até esse estado
    private int heuristica; // valor da heuristica do nó
    private int fN; // f(n) = heuristica + valorDeChegada

    public Estado(int nivel, int[] limiteMaximoJarro, int nJarros, int valorDeChegada, int heuristica, int fN) {
        this.nivel = nivel;
        this.nJarros = nJarros;

        this.limiteMaximoJarro = new int[nJarros];
        for (int i = 0; i < limiteMaximoJarro.length; i++) {
            this.limiteMaximoJarro[i] = limiteMaximoJarro[i];
        }
        this.nJarros = nJarros;
        this.jarros = new Jarro[nJarros];

        for (int i = 0; i < nJarros; i++) {
            this.jarros[i] = new Jarro(limiteMaximoJarro[i]);
        }

        this.valorDeChegada = valorDeChegada;
        this.heuristica = heuristica;
        this.fN = fN;
    }

    public Estado(int nivel, int[] limiteMaximoJarro, int nJarros) {
        this.nivel = nivel;
        this.nJarros = nJarros;

        this.limiteMaximoJarro = new int[nJarros];
        for (int i = 0; i < limiteMaximoJarro.length; i++) {
            this.limiteMaximoJarro[i] = limiteMaximoJarro[i];
        }
        this.nJarros = nJarros;
        this.jarros = new Jarro[nJarros];

        for (int i = 0; i < nJarros; i++) {
            this.jarros[i] = new Jarro(limiteMaximoJarro[i]);
        }

    }

    @Override
    public int getfN() {
        return fN;
    }

    @Override
    public void setfN(int fN) {
        this.fN = fN;
    }

    @Override
    public int getHeuristica() {
        return heuristica;
    }

    @Override
    public void setHeuristica(int heuristica) {
        this.heuristica = heuristica;
    }

    @Override
    public int getValorDeChegada() {
        return valorDeChegada;
    }

    @Override
    public void setValorDeChegada(int valorDeChegada) {
        this.valorDeChegada = valorDeChegada;
    }

    @Override
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    @Override
    public int getNivel() {
        return nivel;
    }

    @Override
    public boolean verificaSeEhRaiz() {
        return this.raiz;
    }

    @Override
    public int getEstadoJarro(int indice) {
        return jarros[indice].getWater();
    }

    @Override
    public void setEstadoJarro(int indice, int water) {
        jarros[indice].setWater(water);
    }

    //retorna o limite máximo de todos os jarros
    @Override
    public int[] getLimiteMaximoJarro() {
        return limiteMaximoJarro;
    }

    // retorna o limite maximo de um certo jarro
    @Override
    public int getLimiteMaximoJarro(int indice) {
        return limiteMaximoJarro[indice];
    }

    @Override
    public int getnJarros() {
        return nJarros;
    }

    @Override
    public void setnJarros(int nJarros) {
        this.nJarros = nJarros;
    }

    @Override
    public Jarro getJarrosByIndex(int index) {
        return this.jarros[index];
    }
}

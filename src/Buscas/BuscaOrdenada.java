package Buscas;

import Grafo.Grafo;
import Grafo.Vertice;
import Utilidade.ListaVertices;
import Jarro.Jarro;
import Operações.Operacoes;

public class BuscaOrdenada {

    private static boolean sucesso = false;
    private static boolean fracasso = false;
    private static Vertice solucao = null;
    private static int nosExpandidos;

    /*
        Se o filho for gerado esvaziando completamente um jarro a aresta vale 1
        Se o filho for gerado através de mover de um jarro para o outro a aresta vale 2
        Se o filho for gerado enchendo completamente um jarro a aresta vale 3
     */
    public static void geraFilhos(Vertice pai, Grafo arvore, ListaVertices list) {
        nosExpandidos += 1;
        for (int i = 0; i < pai.getState().getnJarros(); i++) {
            for (int j = 0; j < pai.getState().getnJarros(); j++) {
                if (i == j) {

                    Vertice filhoUm = new Vertice(pai.getState());
                    filhoUm.getState().setNivel((pai.getState().getNivel() + 1));

                    for (int jarro = 0; jarro < pai.getState().getnJarros(); jarro++) {
                        filhoUm.getState().setEstadoJarro(jarro, pai.getState().getEstadoJarro(jarro));
                    }
                    Jarro a = filhoUm.getState().getJarrosByIndex(i);

                    if (a.getWater() < filhoUm.getState().getLimiteMaximoJarro(i)) {
                        Operacoes.encher(a);

                        if (!pai.getParentes().verificarNo(filhoUm)) {

                            list.adicionarFinal(filhoUm);
                            arvore.setVertice(filhoUm);
                            arvore.setAresta(pai, filhoUm, 15);
                            filhoUm.getState().setValorDeChegada(pai.getState().getValorDeChegada() + 15);
                            filhoUm.copiaPilha(pai);
                        } else {
                            filhoUm = null;
                        }
                    } else {
                        filhoUm = null;
                    }

                    Vertice filhoDois = new Vertice(pai.getState());
                    filhoDois.getState().setNivel((pai.getState().getNivel() + 1));
                    for (int jarro = 0; jarro < pai.getState().getnJarros(); jarro++) {
                        filhoDois.getState().setEstadoJarro(jarro, pai.getState().getEstadoJarro(jarro));
                    }

                    Jarro b = filhoDois.getState().getJarrosByIndex(i);

                    if (b.getWater() > 0) {
                        Operacoes.esvaziar(b);
                        if (!pai.getParentes().verificarNo(filhoDois)) {
                            list.adicionarFinal(filhoDois);

                            arvore.setVertice(filhoDois);
                            arvore.setAresta(pai, filhoDois, 9);
                            filhoDois.getState().setValorDeChegada(pai.getState().getValorDeChegada() + 9);
                            filhoDois.copiaPilha(pai);
                        } else {
                            filhoDois = null;
                        }
                    } else {
                        filhoDois = null;
                    }

                } else {

                    Vertice filhoUm = new Vertice(pai.getState());
                    filhoUm.getState().setNivel((pai.getState().getNivel() + 1));

                    for (int jarro = 0; jarro < pai.getState().getnJarros(); jarro++) {
                        filhoUm.getState().setEstadoJarro(jarro, pai.getState().getEstadoJarro(jarro));
                    }
                    Jarro a = filhoUm.getState().getJarrosByIndex(i);
                    Jarro b = filhoUm.getState().getJarrosByIndex(j);

                    if (a.getWater() != 0 && b.getWater() < b.getMaxCapacity()) {
                        Operacoes.mover(a, b);
                        if (!pai.getParentes().verificarNo(filhoUm)) {
                            list.adicionarFinal(filhoUm);

                            arvore.setVertice(filhoUm);
                            arvore.setAresta(pai, filhoUm, 12);
                            filhoUm.getState().setValorDeChegada(pai.getState().getValorDeChegada() + 12);
                            filhoUm.copiaPilha(pai);
                        } else {
                            filhoUm = null;
                        }
                    } else {
                        filhoUm = null;
                    }

                }

            }
        }
        list.excluir(pai);
    }

    public static void buscaOrdenada(Vertice raiz, int[] objetivo, Grafo arvoreDeBusca) {
        BuscaOrdenada.sucesso = false;
        BuscaOrdenada.fracasso = false;
        BuscaOrdenada.solucao = null;
        nosExpandidos = 0;

        ListaVertices list = new ListaVertices();
        list.adicionarFinal(raiz);

        while (!(sucesso || fracasso)) {
            if (raiz == null) {
                BuscaOrdenada.fracasso = true;
                break;
            } else {
                if (arvoreDeBusca.verificaSolucao(objetivo, raiz)) {
                    BuscaOrdenada.sucesso = true;
                    solucao = raiz;

                    break;

                } else {
                    while (BuscaOrdenada.sucesso == false && BuscaOrdenada.fracasso == false && list.ehVazia() == false) {
                        Vertice menorCustoChegada = list.getPrimeiro();

                        // Selecionando vértice folha que tem o menos custo de chegada
                        for (Vertice a = list.getPrimeiro(); a != null; a = a.getProxListaVertices()) {
                            if (a.getState().getValorDeChegada() < menorCustoChegada.getState().getValorDeChegada()) {
                                menorCustoChegada = a;
                            }
                        }

                        if (arvoreDeBusca.verificaSolucao(objetivo, menorCustoChegada)) {
                            BuscaOrdenada.solucao = menorCustoChegada;
                            BuscaOrdenada.sucesso = true;
                            break;
                        } else {
                            BuscaOrdenada.geraFilhos(menorCustoChegada, arvoreDeBusca, list);
                        }

                        if (list.ehVazia()) {
                            BuscaOrdenada.fracasso = true;
                        }
                    }
                }
            }
        }

        if (sucesso) {
            System.out.println("RESULTADO: ");
            BuscaOrdenada.solucao.getParentes().imprimeLista();
            System.out.println();
            System.out.println("Custo total: " + BuscaOrdenada.solucao.getState().getValorDeChegada());
            System.out.println("Nós expandidos: " + nosExpandidos);
            System.out.println("Total de nós na árvore: " + arvoreDeBusca.getNumeroDeVertices());
            System.out.println("FIM");
        } else {
            System.out.println("Solução impossível de ser encontrada.");
        }
    }

}

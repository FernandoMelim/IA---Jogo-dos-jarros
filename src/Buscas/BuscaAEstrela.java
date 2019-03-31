package Buscas;

import Grafo.Grafo;
import Grafo.Vertice;
import Utilidade.ListaVertices;
import Jarro.Jarro;
import Operações.Operacoes;

public class BuscaAEstrela {

    private static boolean sucesso = false;
    private static boolean fracasso = false;
    private static Vertice solucao = null;
    private static int nosExpandidos;

    public static void geraFilhos(Vertice pai, Grafo arvore, ListaVertices list, int[] objetivo) {
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
                            int heuristicaFilho = 0;

                            for (int x = 0; x < objetivo.length; x++) {
                                int temp = 0;
                                temp = objetivo[x] - filhoUm.getState().getEstadoJarro(x);
                                if (temp < 0) {
                                    temp = temp * (-1);
                                }
                                heuristicaFilho += temp;
                            }
                            filhoUm.getState().setHeuristica(heuristicaFilho);
                            filhoUm.getState().setValorDeChegada(pai.getState().getValorDeChegada() + 15);
                            filhoUm.getState().setfN(filhoUm.getState().getValorDeChegada() + filhoUm.getState().getHeuristica());
                            list.adicionarFinal(filhoUm);
                            arvore.setVertice(filhoUm);
                            arvore.setAresta(pai, filhoUm, 15);
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
                            int heuristicaFilho = 0;

                            for (int x = 0; x < objetivo.length; x++) {
                                int temp = 0;
                                temp = objetivo[x] - filhoDois.getState().getEstadoJarro(x);
                                if (temp < 0) {
                                    temp = temp * (-1);
                                }
                                heuristicaFilho += temp;
                            }
                            filhoDois.getState().setHeuristica(heuristicaFilho);
                            list.adicionarFinal(filhoDois);
                            arvore.setVertice(filhoDois);
                            arvore.setAresta(pai, filhoDois, 9);
                            filhoDois.getState().setValorDeChegada(pai.getState().getValorDeChegada() + 9);
                            filhoDois.getState().setfN(filhoDois.getState().getValorDeChegada() + filhoDois.getState().getHeuristica());
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
                            int heuristicaFilho = 0;

                            for (int x = 0; x < objetivo.length; x++) {
                                int temp = 0;
                                temp = objetivo[x] - filhoUm.getState().getEstadoJarro(x);
                                if (temp < 0) {
                                    temp = temp * (-1);
                                }
                                heuristicaFilho += temp;
                            }
                            filhoUm.getState().setHeuristica(heuristicaFilho);
                            list.adicionarFinal(filhoUm);
                            arvore.setVertice(filhoUm);
                            arvore.setAresta(pai, filhoUm, 12);
                            filhoUm.getState().setValorDeChegada(pai.getState().getValorDeChegada() + 12);
                            filhoUm.getState().setfN(filhoUm.getState().getValorDeChegada() + filhoUm.getState().getHeuristica());
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

    public static void buscaAEstrela(Vertice raiz, int[] objetivo, Grafo arvoreDeBusca) {
        BuscaAEstrela.sucesso = false;
        BuscaAEstrela.fracasso = false;
        BuscaAEstrela.solucao = null;
        nosExpandidos = 0;

        ListaVertices list = new ListaVertices();
        list.adicionarFinal(raiz);

        while (!(sucesso || fracasso)) {
            if (raiz == null) {
                BuscaAEstrela.fracasso = true;
                break;
            } else {
                if (arvoreDeBusca.verificaSolucao(objetivo, raiz)) {
                    BuscaAEstrela.sucesso = true;
                    solucao = raiz;

                    break;

                } else {
                    while (BuscaAEstrela.sucesso == false && BuscaAEstrela.fracasso == false && list.ehVazia() == false) {
                        Vertice menorCustoChegada = list.getPrimeiro();

                        // Selecionando vértice folha que tem o menos custo de chegada
                        for (Vertice a = list.getPrimeiro(); a != null; a = a.getProxListaVertices()) {
                            if (a.getState().getfN() < menorCustoChegada.getState().getfN()) {
                                menorCustoChegada = a;
                            }
                        }

                        if (arvoreDeBusca.verificaSolucao(objetivo, menorCustoChegada)) {
                            BuscaAEstrela.solucao = menorCustoChegada;
                            BuscaAEstrela.sucesso = true;
                            break;
                        } else {
                            BuscaAEstrela.geraFilhos(menorCustoChegada, arvoreDeBusca, list, objetivo);
                        }

                        if (list.ehVazia()) {
                            BuscaAEstrela.fracasso = true;
                        }
                    }
                }
            }
        }

        if (sucesso) {
            System.out.println("RESULTADO: ");
            BuscaAEstrela.solucao.getParentes().imprimeLista();
            System.out.println();
            System.out.println("Custo total: " + BuscaAEstrela.solucao.getState().getValorDeChegada());
            System.out.println("Nós expandidos: " + nosExpandidos);
            System.out.println("Total de nós na árvore: " + arvoreDeBusca.getNumeroDeVertices());
            System.out.println("FIM");
        } else {
            System.out.println("Solução impossível de ser encontrada.");
        }
    }
}

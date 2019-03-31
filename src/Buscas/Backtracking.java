package Buscas;

import Grafo.Grafo;
import Grafo.Vertice;
import Jarro.Jarro;
import Operações.Operacoes;

public class Backtracking {

    private static boolean sucesso = false;
    private static boolean fracasso = false;
    private static Vertice solucao;
    private static int nosExapandidos;

    public static void backtracking(Vertice pai, Grafo arvore, int[] objetivo) {
        if (solucao == null) {
            if (arvore.verificaSolucao(objetivo, pai)) {
                solucao = pai;
            } else {
                nosExapandidos += 1;
                for (int i = 0; i < pai.getState().getnJarros(); i++) {
                    if (solucao == null) {
                        for (int j = 0; j < pai.getState().getnJarros(); j++) {
                            if (i == j) {
                                if (solucao == null) {
                                    Vertice filhoUm = new Vertice(pai.getState());
                                    filhoUm.getState().setNivel(pai.getState().getNivel() + 1);

                                    for (int jarro = 0; jarro < pai.getState().getnJarros(); jarro++) {
                                        filhoUm.getState().setEstadoJarro(jarro, pai.getState().getEstadoJarro(jarro));
                                    }
                                    Jarro a = filhoUm.getState().getJarrosByIndex(i);

                                    if (a.getWater() < filhoUm.getState().getLimiteMaximoJarro(i)) {
                                        Operacoes.encher(a);

                                        if (!pai.getParentes().verificarNo(filhoUm)) {
                                            arvore.setVertice(filhoUm);
                                            arvore.setAresta(pai, filhoUm, 15);
                                            filhoUm.getState().setValorDeChegada(pai.getState().getValorDeChegada() + 15);
                                            filhoUm.copiaPilha(pai);
                                            backtracking(filhoUm, arvore, objetivo);
                                        }
                                    }
                                } else {
                                    break;
                                }

                                if (solucao == null) {
                                    Vertice filhoDois = new Vertice(pai.getState());
                                    filhoDois.getState().setNivel(pai.getState().getNivel() + 1);
                                    for (int jarro = 0; jarro < pai.getState().getnJarros(); jarro++) {
                                        filhoDois.getState().setEstadoJarro(jarro, pai.getState().getEstadoJarro(jarro));
                                    }

                                    Jarro b = filhoDois.getState().getJarrosByIndex(i);

                                    if (b.getWater() > 0) {
                                        Operacoes.esvaziar(b);
                                        if (!pai.getParentes().verificarNo(filhoDois)) {
                                            arvore.setVertice(filhoDois);
                                            arvore.setAresta(pai, filhoDois, 9);
                                            filhoDois.getState().setValorDeChegada(pai.getState().getValorDeChegada() + 9);
                                            filhoDois.copiaPilha(pai);
                                            backtracking(filhoDois, arvore, objetivo);
                                        }
                                    }
                                } else {
                                    break;
                                }
                            } else {
                                if (solucao == null) {
                                    Vertice filhoUm = new Vertice(pai.getState());
                                    filhoUm.getState().setNivel(pai.getState().getNivel() + 1);

                                    for (int jarro = 0; jarro < pai.getState().getnJarros(); jarro++) {
                                        filhoUm.getState().setEstadoJarro(jarro, pai.getState().getEstadoJarro(jarro));
                                    }
                                    Jarro a = filhoUm.getState().getJarrosByIndex(i);
                                    Jarro b = filhoUm.getState().getJarrosByIndex(j);

                                    if (a.getWater() != 0 && b.getWater() < b.getMaxCapacity()) {
                                        Operacoes.mover(a, b);

                                        if (!pai.getParentes().verificarNo(filhoUm)) {
                                            arvore.setVertice(filhoUm);
                                            arvore.setAresta(pai, filhoUm, 12);
                                            filhoUm.getState().setValorDeChegada(pai.getState().getValorDeChegada() + 12);
                                            filhoUm.copiaPilha(pai);
                                            backtracking(filhoUm, arvore, objetivo);
                                        }
                                    }
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void inicializarBusca(Vertice raiz, int[] objetivo, Grafo arvoreDeBusca) {

        nosExapandidos = 0;
        sucesso = false;
        fracasso = false;
        solucao = null;

        while (!(sucesso || fracasso)) {
            if (raiz == null) {
                fracasso = true;
            } else {
                if (arvoreDeBusca.verificaSolucao(objetivo, raiz)) {
                    solucao = raiz;
                    sucesso = true;
                } else {
                    backtracking(raiz, arvoreDeBusca, objetivo);

                    if (solucao != null) {
                        sucesso = true;
                    } else {
                        fracasso = true;
                    }
                }
            }
        }

        if (sucesso) {
            System.out.println("RESULTADO: ");
            solucao.getParentes().imprimeLista();
            System.out.println();
            System.out.println("Custo total: " + solucao.getState().getValorDeChegada());
            System.out.println("Nos expandidos: " + nosExapandidos);
            System.out.println("Total de nós na árvore: " + arvoreDeBusca.getNumeroDeVertices());
            System.out.println("FIM");
        } else {
            System.out.println("Solução impossível de ser encontrada.");
        }
    }
}

package Buscas;

import Grafo.Aresta;
import Grafo.Grafo;
import Grafo.Vertice;
import Jarro.Jarro;
import Operações.Operacoes;

public class BuscaProfundidade {

    private static boolean sucesso = false;
    private static boolean fracasso = false;
    private static Vertice solucao;
    private static int nosExpandidos;

    // Gera todos os estados possíveis a partir do vértice "pai"
    public static void geraFilhos(Vertice pai, Grafo arvore) {
        nosExpandidos += 1;
        for (int i = 0; i < pai.getState().getnJarros(); i++) {
            for (int j = 0; j < pai.getState().getnJarros(); j++) {
                if (i == j) {

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
                        } else {
                            filhoUm = null;
                        }
                    } else {
                        filhoUm = null;
                    }

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
                        } else {
                            filhoDois = null;
                        }
                    } else {
                        filhoDois = null;
                    }

                } else {

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
                        } else {
                            filhoUm = null;
                        }
                    } else {
                        filhoUm = null;
                    }
                }

            }
        }
    }

    public static void buscaProfundidade(Vertice raiz, int[] objetivo, int limite, Grafo arvoreDeBusca) {

        sucesso = false;
        fracasso = false;
        solucao = null;
        nosExpandidos = 0;
        //Vertice raiz = arvoreDeBusca.getVerticesDesteGrafo().getPrimeiro();
        while (!(sucesso || fracasso)) {
            if (raiz == null) {
                fracasso = true;
                break;
            } else {
                if (arvoreDeBusca.verificaSolucao(objetivo, raiz)) {
                    solucao = raiz;
                    sucesso = true;
                    break;
                } else {
                    BuscaProfundidade.geraFilhos(raiz, arvoreDeBusca);
                    Aresta a = raiz.getPrimAresta();

                    for (; a != null; a = a.getProxima()) {
                        if (sucesso == false) {
                            Vertice destino = a.getVerticeDestino();
                            if (destino.getState().getNivel() < limite) {

                                if (arvoreDeBusca.verificaSolucao(objetivo, destino)) {
                                    solucao = destino;
                                    sucesso = true;
                                    break;
                                } else {
                                    BuscaProfundidade.auxBuscaProf(arvoreDeBusca, destino, objetivo, limite);
                                }
                            } else {
                                if (destino.getState().getNivel() == limite) {
                                    if (arvoreDeBusca.verificaSolucao(objetivo, destino)) {
                                        solucao = destino;
                                        sucesso = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    if (sucesso == false && a == null && solucao == null) {
                        fracasso = true;
                    }
                }
            }
        }

        if (sucesso) {
            System.out.println("RESULTADO: ");
            BuscaProfundidade.solucao.getParentes().imprimeLista();
            System.out.println();
            System.out.println("Custo total: " + solucao.getState().getValorDeChegada());
            System.out.println("Nos expandidos: " + BuscaProfundidade.nosExpandidos);
            System.out.println("Total de nós na árvore: " + arvoreDeBusca.getNumeroDeVertices());
            System.out.println("FIM");
        } else {
            System.out.println("Solução impossível de ser encontrada.");
        }

        arvoreDeBusca.deletaGrafo();

    }

    private static void auxBuscaProf(Grafo arvore, Vertice pai, int[] objetivo, int limite) {
        if (sucesso == false) {
            BuscaProfundidade.geraFilhos(pai, arvore);
            Aresta a = pai.getPrimAresta();

            for (; a != null; a = a.getProxima()) {

                if (sucesso == false) {
                    Vertice destino = a.getVerticeDestino();
                    if (destino.getState().getNivel() < limite) {

                        if (arvore.verificaSolucao(objetivo, destino)) {
                            solucao = destino;
                            sucesso = true;
                            break;
                        } else {
                            BuscaProfundidade.auxBuscaProf(arvore, destino, objetivo, limite);
                        }
                    } else {
                        if (destino.getState().getNivel() == limite) {

                            if (arvore.verificaSolucao(objetivo, destino)) {
                                solucao = destino;
                                sucesso = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}

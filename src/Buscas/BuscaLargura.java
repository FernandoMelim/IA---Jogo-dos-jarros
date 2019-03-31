package Buscas;

import Grafo.Aresta;
import Grafo.Grafo;
import Grafo.Vertice;
import Utilidade.Fila;
import Jarro.Jarro;
import Operações.Operacoes;

public class BuscaLargura {

    private static boolean sucesso = false;
    private static boolean fracasso = false;
    private static Vertice solucao = null;
    private static int nosExpandidos;

    public static void geraFilhos(Vertice pai, Grafo arvore) {
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

    public static void buscaLargura(Vertice raiz, int[] objetivo, Grafo arvoreDeBusca) {
        Fila fila = new Fila();
        fila.adicionar(raiz);
        BuscaLargura.sucesso = false;
        BuscaLargura.fracasso = false;
        BuscaLargura.solucao = null;
        nosExpandidos = 0;

        while (!(sucesso || fracasso)) {
            if (raiz == null) {
                BuscaLargura.fracasso = true;
                break;
            } else {
                if (arvoreDeBusca.verificaSolucao(objetivo, raiz)) {
                    BuscaLargura.sucesso = true;
                    solucao = raiz;

                    break;

                } else {
                    while (BuscaLargura.sucesso == false && BuscaLargura.fracasso == false && fila.ehVazia() == false) {
                        /*System.out.println();
                        System.out.println("INICIO DA FILA:");
                        System.out.println();
                        fila.imprimeFila();
                        System.out.println();
                        System.out.println("FIM DA FILA:");
                        System.out.println();*/

                        int n = fila.getnNos();
                        int j = 1;
                        for (Vertice pai = fila.getPrimeiro(); pai != null; pai = pai.getProxFila()) {
                            //System.out.println(pai.getState().getNivel() + " - " + j + " de " + n + ".");
                            if (arvoreDeBusca.verificaSolucao(objetivo, pai)) {
                                BuscaLargura.sucesso = true;
                                BuscaLargura.solucao = pai;
                                break;
                            } else {
                                BuscaLargura.geraFilhos(pai, arvoreDeBusca);
                            }
                            j++;
                        }

                        int i = 0;
                        for (Vertice pai = fila.getPrimeiro(); i < n && pai != null; pai = pai.getProxFila(), i++) {
                            for (Aresta filho = pai.getPrimAresta(); filho != null; filho = filho.getProxima()) {

                                fila.adicionar(filho.getVerticeDestino());
                            }
                        }

                        i = 0;
                        for (; i < n; i++) {
                            if (!fila.ehVazia()) {
                                fila.excluir();
                            }
                        }

                        if (fila.ehVazia()) {
                            BuscaLargura.fracasso = true;
                        }
                    }
                }
            }
        }

        if (sucesso) {
            System.out.println("RESULTADO: ");
            BuscaLargura.solucao.getParentes().imprimeLista();
            System.out.println();
            System.out.println("Custo total: " + BuscaLargura.solucao.getState().getValorDeChegada());
            System.out.println("Nós expandidos: " + nosExpandidos);
            System.out.println("Total de nós na árvore: " + arvoreDeBusca.getNumeroDeVertices());
            System.out.println("FIM");
        } else {
            System.out.println("Solução impossível de ser encontrada.");
        }
    }

}

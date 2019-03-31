package Buscas;

import Grafo.Grafo;
import Grafo.Vertice;
import Utilidade.ListaVertices;
import Jarro.Jarro;
import Operações.Operacoes;

public class BuscaIDAEstrela {

    private static boolean sucesso = false;
    private static boolean fracasso = false;
    private static Vertice solucao = null;
    private static int nosExpandidos;

    public static void geraArvore(Vertice pai, Grafo arvore, int[] objetivo, int patamarAtual, ListaVertices lixo) {
        if (solucao == null) {
            nosExpandidos += 1;
            if (arvore.verificaSolucao(objetivo, pai)) {
                solucao = pai;
                sucesso = true;
            } else {
                for (int i = 0; i < pai.getState().getnJarros(); i++) {
                    for (int j = 0; j < pai.getState().getnJarros(); j++) {
                        if (i == j) {
                            if (solucao == null) {
                                Vertice filhoUm = new Vertice(pai.getState());
                                filhoUm.getState().setNivel((pai.getState().getNivel() + 1));

                                for (int jarro = 0; jarro < pai.getState().getnJarros(); jarro++) {
                                    filhoUm.getState().setEstadoJarro(jarro, pai.getState().getEstadoJarro(jarro));
                                }
                                Jarro a = filhoUm.getState().getJarrosByIndex(i);

                                if (a.getWater() < filhoUm.getState().getLimiteMaximoJarro(i)) {
                                    Operacoes.encher(a);

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
                                    filhoUm.copiaPilha(pai);

                                    if (!pai.getParentes().verificarNo(filhoUm) && filhoUm.getState().getfN() <= patamarAtual) {

                                        arvore.setVertice(filhoUm);
                                        arvore.setAresta(pai, filhoUm, 15);
                                        if (arvore.verificaSolucao(objetivo, filhoUm)) {
                                            solucao = filhoUm;
                                        }
                                        geraArvore(filhoUm, arvore, objetivo, patamarAtual, lixo);

                                    } else {
                                        if (filhoUm.getState().getfN() > patamarAtual && !pai.getParentes().verificarNo(filhoUm)) {
                                            lixo.adicionarFinal(filhoUm);

                                        } else {
                                            filhoUm = null;
                                        }
                                    }
                                } else {
                                    filhoUm = null;
                                }
                            }
                            if (solucao == null) {
                                Vertice filhoDois = new Vertice(pai.getState());
                                filhoDois.getState().setNivel((pai.getState().getNivel() + 1));
                                for (int jarro = 0; jarro < pai.getState().getnJarros(); jarro++) {
                                    filhoDois.getState().setEstadoJarro(jarro, pai.getState().getEstadoJarro(jarro));
                                }

                                Jarro b = filhoDois.getState().getJarrosByIndex(i);

                                if (b.getWater() > 0) {
                                    Operacoes.esvaziar(b);

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
                                    filhoDois.getState().setValorDeChegada(pai.getState().getValorDeChegada() + 9);
                                    filhoDois.getState().setfN(filhoDois.getState().getValorDeChegada() + filhoDois.getState().getHeuristica());
                                    filhoDois.copiaPilha(pai);
                                    if (!pai.getParentes().verificarNo(filhoDois) && filhoDois.getState().getfN() <= patamarAtual) {

                                        arvore.setVertice(filhoDois);
                                        arvore.setAresta(pai, filhoDois, 9);
                                        if (arvore.verificaSolucao(objetivo, filhoDois)) {
                                            solucao = filhoDois;
                                        }
                                        geraArvore(filhoDois, arvore, objetivo, patamarAtual, lixo);

                                    } else {
                                        if (filhoDois.getState().getfN() > patamarAtual && !pai.getParentes().verificarNo(filhoDois)) {
                                            lixo.adicionarFinal(filhoDois);
                                        } else {
                                            filhoDois = null;
                                        }
                                    }
                                } else {
                                    filhoDois = null;
                                }
                            }
                        } else {
                            if (solucao == null) {
                                Vertice filhoUm = new Vertice(pai.getState());
                                filhoUm.getState().setNivel((pai.getState().getNivel() + 1));

                                for (int jarro = 0; jarro < pai.getState().getnJarros(); jarro++) {
                                    filhoUm.getState().setEstadoJarro(jarro, pai.getState().getEstadoJarro(jarro));
                                }
                                Jarro a = filhoUm.getState().getJarrosByIndex(i);
                                Jarro b = filhoUm.getState().getJarrosByIndex(j);

                                if (a.getWater() != 0 && b.getWater() < b.getMaxCapacity()) {
                                    Operacoes.mover(a, b);
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
                                    filhoUm.getState().setValorDeChegada(pai.getState().getValorDeChegada() + 12);
                                    filhoUm.getState().setfN(filhoUm.getState().getValorDeChegada() + filhoUm.getState().getHeuristica());
                                    filhoUm.copiaPilha(pai);
                                    if (!pai.getParentes().verificarNo(filhoUm) && filhoUm.getState().getfN() <= patamarAtual) {

                                        arvore.setVertice(filhoUm);
                                        arvore.setAresta(pai, filhoUm, 12);
                                        if (arvore.verificaSolucao(objetivo, filhoUm)) {
                                            solucao = filhoUm;
                                        }
                                        geraArvore(filhoUm, arvore, objetivo, patamarAtual, lixo);

                                    } else {
                                        if (filhoUm.getState().getfN() > patamarAtual && !pai.getParentes().verificarNo(filhoUm)) {
                                            lixo.adicionarFinal(filhoUm);
                                        } else {
                                            filhoUm = null;
                                        }
                                    }
                                } else {
                                    filhoUm = null;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void buscaIDAEstrela(Vertice raiz, int[] objetivo, Grafo arvoreDeBusca) {

        BuscaIDAEstrela.sucesso = false;
        BuscaIDAEstrela.fracasso = false;
        BuscaIDAEstrela.solucao = null;
        nosExpandidos = 0;

        ListaVertices lixo = new ListaVertices();
        lixo.adicionarFinal(raiz);
        int iteracao = 0;
        int patamarr = raiz.getState().getfN();

        while (!(sucesso || fracasso)) {
            if (raiz == null) {
                BuscaIDAEstrela.fracasso = true;
                break;
            } else {
                if (arvoreDeBusca.verificaSolucao(objetivo, raiz)) {
                    BuscaIDAEstrela.sucesso = true;
                    solucao = raiz;
                    break;

                } else {

                    while (BuscaIDAEstrela.sucesso == false && BuscaIDAEstrela.fracasso == false && solucao == null) {
                        iteracao += 1;
                        System.out.println("Iteração numero " + iteracao);

                        arvoreDeBusca = new Grafo();
                        arvoreDeBusca.setVertice(raiz);

                        Vertice patamar = lixo.getPrimeiro();
                        for (Vertice a = lixo.getPrimeiro(); a != null; a = a.getProxListaVertices()) {
                            if (a.getState().getfN() < patamar.getState().getfN()) {
                                patamar = a;
                            }
                        }
                        
                        lixo = new ListaVertices();
                        patamarr = patamar.getState().getfN();
                        BuscaIDAEstrela.geraArvore(raiz, arvoreDeBusca, objetivo, patamar.getState().getfN(), lixo);
                        
                        if (lixo.ehVazia()) {
                            break;
                        }


                    }
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
            BuscaIDAEstrela.solucao.getParentes().imprimeLista();
            System.out.println();
            System.out.println("Custo total: " + BuscaIDAEstrela.solucao.getState().getValorDeChegada());
            System.out.println("Nós expandidos: " + nosExpandidos);
            System.out.println("Total de nós na árvore: " + arvoreDeBusca.getNumeroDeVertices());
            System.out.println("Foram feitas " + iteracao + " iterações.");
            System.out.println("Último patar avaliado: " + patamarr);
            System.out.println("FIM");
        } else {
            System.out.println("Solução impossível de ser encontrada.");
        }
    }
}

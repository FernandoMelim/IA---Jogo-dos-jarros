package Main;

import Buscas.Backtracking;
import Buscas.BuscaAEstrela;
import Buscas.BuscaGulosa;
import Buscas.BuscaIDAEstrela;
import Buscas.BuscaLargura;
import Buscas.BuscaOrdenada;
import Grafo.Vertice;
import Grafo.Grafo;
import java.util.Scanner;
import Buscas.BuscaProfundidade;
import States.IState;
import States.Estado;

public class InteligenciaArtificial {

    public static void main(String[] args) {
        int nJarros; // numero de jarros
        int[] objetivo;  // quantidade de litro que quer encontrar
        Scanner scanner = new Scanner(System.in);
        int[] limiteMaximoJarros;// cada posição do vetor representa um jarro e diz o valor máximo do jarro

        System.out.println("Digite o número de jarros: ");
        nJarros = scanner.nextInt();

        if (nJarros < 0) {
            nJarros = nJarros * (-1);
        }
        objetivo = new int[nJarros];

        limiteMaximoJarros = new int[nJarros];

        for (int i = 0; i < limiteMaximoJarros.length; i = i + 1) {
            System.out.println("Para o jarro " + (i + 1) + " digite o número de litros que ele poderá suportar: ");
            limiteMaximoJarros[i] = scanner.nextInt();

        }

        int heuristicaRaiz = 0; // valor da heuristica no nó raiz
        for (int i = 0; i < limiteMaximoJarros.length; i = i + 1) {
            System.out.println("Digite o objetivo do jarro " + (i + 1) + ": ");
            objetivo[i] = scanner.nextInt();
            if (objetivo[i] < 0) {
                objetivo[i] = objetivo[i] * (-1);
            }
            heuristicaRaiz += objetivo[i];
        }

        if (possivel(objetivo, limiteMaximoJarros) && nJarros > 0) {
            int i = 0;
            while (i != -1) {
                Scanner ii = new Scanner(System.in);
                System.out.println("Digite -1 para sair do programa.");
                System.out.println("Digite 1 para realizar backtracking.");
                System.out.println("Digite 2 para realizar busca em largura.");
                System.out.println("Digite 3 para realizar busca em profundidade");
                System.out.println("Digite 4 para realizar busca ordenanda.");
                System.out.println("Digite 5 para realizar busca gulosa.");
                System.out.println("Digite 6 para realizar busca A*");
                System.out.println("Digite 7 para realizar busca IDA*");
                i = ii.nextInt();
                switch (i) {

                    case -1:
                        break;
                    case 1:

                        IState estado1 = new Estado(0, limiteMaximoJarros, nJarros);
                        Grafo arvore1 = new Grafo();
                        Vertice raiz1 = new Vertice(estado1);
                        arvore1.setVertice(raiz1);
                        raiz1.getState().setValorDeChegada(0);
                        raiz1.copiaPilha(raiz1);
                        long tempoInicial1 = System.currentTimeMillis();
                        Backtracking.inicializarBusca(raiz1, objetivo, arvore1);
                        System.out.println("O metodo foi executado em " + (System.currentTimeMillis() - tempoInicial1) + " milissegundos.\n");
                        break;
                    case 2:
                        IState estado2 = new Estado(0, limiteMaximoJarros, nJarros);
                        Grafo arvore2 = new Grafo();
                        Vertice raiz2 = new Vertice(estado2);
                        arvore2.setVertice(raiz2);
                        raiz2.getState().setValorDeChegada(0);
                        raiz2.copiaPilha(raiz2);
                        long tempoInicial2 = System.currentTimeMillis();
                        BuscaLargura.buscaLargura(raiz2, objetivo, arvore2);
                        System.out.println("O metodo foi executado em " + (System.currentTimeMillis() - tempoInicial2) + " milissegundos.\n");
                        break;
                    case 3:
                        Scanner scannner = new Scanner(System.in);
                        int limite;
                        System.out.println("Digite o nível limite: ");
                        limite = scannner.nextInt();
                        if (limite >= 0) {
                            IState estado3 = new Estado(0, limiteMaximoJarros, nJarros);
                            Grafo arvore3 = new Grafo();
                            Vertice raiz3 = new Vertice(estado3);
                            arvore3.setVertice(raiz3);
                            raiz3.getState().setValorDeChegada(0);
                            raiz3.copiaPilha(raiz3);
                            long tempoInicial3 = System.currentTimeMillis();
                            BuscaProfundidade.buscaProfundidade(raiz3, objetivo, limite, arvore3);
                            System.out.println("O metodo foi executado em " + (System.currentTimeMillis() - tempoInicial3) + " milissegundos.\n");
                        } else {
                            System.out.println("Limite inválido. Tem que ser maior ou igual a zero.");
                        }
                        break;
                    case 4:
                        IState estado4 = new Estado(0, limiteMaximoJarros, nJarros);
                        Grafo arvore4 = new Grafo();
                        Vertice raiz4 = new Vertice(estado4);
                        arvore4.setVertice(raiz4);
                        raiz4.getState().setValorDeChegada(0);
                        raiz4.copiaPilha(raiz4);
                        long tempoInicial4 = System.currentTimeMillis();
                        BuscaOrdenada.buscaOrdenada(raiz4, objetivo, arvore4);
                        System.out.println("O metodo foi executado em " + (System.currentTimeMillis() - tempoInicial4) + " milissegundos.\n");
                        break;
                    case 5:
                        IState estado5 = new Estado(0, limiteMaximoJarros, nJarros);
                        Grafo arvore5 = new Grafo();
                        Vertice raiz5 = new Vertice(estado5);
                        arvore5.setVertice(raiz5);
                        raiz5.getState().setHeuristica(heuristicaRaiz);
                        raiz5.copiaPilha(raiz5);
                        long tempoInicial5 = System.currentTimeMillis();
                        BuscaGulosa.buscaGulosa(raiz5, objetivo, arvore5);
                        System.out.println("O metodo foi executado em " + (System.currentTimeMillis() - tempoInicial5) + " milissegundos.\n");
                        break;
                    case 6:
                        IState estado6 = new Estado(0, limiteMaximoJarros, nJarros);
                        Grafo arvore6 = new Grafo();
                        Vertice raiz6 = new Vertice(estado6);
                        arvore6.setVertice(raiz6);
                        raiz6.getState().setHeuristica(heuristicaRaiz);
                        raiz6.getState().setValorDeChegada(0);
                        raiz6.getState().setfN(heuristicaRaiz);
                        raiz6.copiaPilha(raiz6);
                        long tempoInicial6 = System.currentTimeMillis();
                        BuscaAEstrela.buscaAEstrela(raiz6, objetivo, arvore6);
                        System.out.println("O metodo foi executado em " + (System.currentTimeMillis() - tempoInicial6) + " milissegundos.\n");
                        break;
                    case 7:
                        IState estado7 = new Estado(0, limiteMaximoJarros, nJarros);
                        Vertice raiz7 = new Vertice(estado7);
                        raiz7.getState().setHeuristica(heuristicaRaiz);
                        raiz7.getState().setValorDeChegada(0);
                        raiz7.getState().setfN(heuristicaRaiz);
                        raiz7.copiaPilha(raiz7);
                        Grafo arvore7 = new Grafo();
                        arvore7.setVertice(raiz7);
                        long tempoInicial7 = System.currentTimeMillis();
                        BuscaIDAEstrela.buscaIDAEstrela(raiz7, objetivo, arvore7);
                        System.out.println("O metodo foi executado em " + (System.currentTimeMillis() - tempoInicial7) + " milissegundos.\n");
                        break;
                    default:
                        System.out.println("Digite uma operação válida.");
                        break;
                }
            }
            System.out.println("Fim do programa.");

        } else {
            System.out.println("Impossível ler tal configuração.");
        }

    }

    public static boolean possivel(int objetivo[], int[] limiteMaximoJarro) { // Método para avaliar se o objetivo é adequado à configuração dos jarros
        for (int i = 0; i < limiteMaximoJarro.length; i++) {
            if (!(limiteMaximoJarro[i] >= objetivo[i])) {
                return false;
            }
        }
        return true;
    }

}

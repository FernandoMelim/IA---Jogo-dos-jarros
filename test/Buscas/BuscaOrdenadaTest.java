/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buscas;

import Grafo.Grafo;
import Grafo.Vertice;
import Utilidade.Fila;
import Utilidade.ListaVertices;
import States.IState;
import States.Estado;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author ferna
 */
public class BuscaOrdenadaTest {

    private static Vertice a, b, c, d, e;
    private static Grafo grafo;
    private static IState estado;
    private static int[] limiteMaximoJarro;
    private static int nJarros;
    private static Fila fila;
    private static int[] objetivo;
    private static ListaVertices li;

    public BuscaOrdenadaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        nJarros = 5;
        grafo = new Grafo();

        /*b = new Vertice();
        c = new Vertice();
        d = new Vertice();
        e = new Vertice();*/
        limiteMaximoJarro = new int[nJarros];
        limiteMaximoJarro[0] = 10;
        limiteMaximoJarro[1] = 20;
        limiteMaximoJarro[2] = 30;
        limiteMaximoJarro[3] = 40;
        limiteMaximoJarro[4] = 50;

        objetivo = new int[nJarros];
        objetivo[0] = 0;
        objetivo[1] = 10;
        objetivo[2] = 20;
        objetivo[3] = 30;
        objetivo[4] = 40;

        estado = new Estado(0, limiteMaximoJarro, nJarros);
        a = new Vertice(estado);
        grafo.setVertice(a);
        a.copiaPilha(a);

        li = new ListaVertices();
        li.adicionarFinal(a);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void teste() {
        ListaVertices pilha = new ListaVertices();
        ListaVertices lixo = new ListaVertices();
        pilha.adicionarFinal(a);
        int heuristicaFilho = 0;

        for (int x = 0; x < objetivo.length; x++) {
            int temp = 0;
            temp = objetivo[x] - a.getState().getEstadoJarro(x);
            if (temp < 0) {
                temp = temp * (-1);
            }
            heuristicaFilho += temp;
        }

        a.getState().setHeuristica(heuristicaFilho);
        a.getState().setValorDeChegada(0);
        a.getState().setfN(0 + a.getState().getHeuristica());

        int patamar = 80/*a.getState().getfN()*/;
        BuscaIDAEstrela busca = new BuscaIDAEstrela();

        
        
        //pilha.imprimeLista();   
        //System.out.println("Valor de chegada: " + b.getState().getValorDeChegada() + "Heuristica: " + b.getState().getHeuristica() + " F(n): " + b.getState().getfN());
        //lixo.imprimeLista();
        //b = lixo.getPrimeiro();
        //System.out.println("Valor de chegada: " + b.getState().getValorDeChegada() + "Heuristica: " + b.getState().getHeuristica() + " F(n): " + b.getState().getfN());
        

    }

}

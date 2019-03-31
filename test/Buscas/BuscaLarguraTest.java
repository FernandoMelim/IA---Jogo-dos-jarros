/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buscas;

import Grafo.Aresta;
import Grafo.Grafo;
import Grafo.Vertice;
import Utilidade.Fila;
import States.IState;
import States.Estado;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ferna
 */
public class BuscaLarguraTest {

    private static Vertice a, b, c, d, e;
    private static Grafo grafo;
    private static IState estado;
    private static int[] limiteMaximoJarro;
    private static int nJarros;
    private static Fila fila;
    private static int[] objetivo;

    @BeforeClass
    public static void setUpClass() {
        nJarros = 3;
        grafo = new Grafo();

        /*b = new Vertice();
        c = new Vertice();
        d = new Vertice();
        e = new Vertice();*/
        limiteMaximoJarro = new int[nJarros];
        limiteMaximoJarro[0] = 4;
        limiteMaximoJarro[1] = 8;
        limiteMaximoJarro[2] = 12;

        objetivo = new int[nJarros];
        objetivo[0] = 0;
        objetivo[1] = 4;
        objetivo[2] = 12;

        estado = new Estado(0, limiteMaximoJarro, nJarros);
        a = new Vertice(estado);
        grafo.setVertice(a);
        a.copiaPilha(a);

        fila = new Fila();
        fila.adicionar(a);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGeraFilhos() {
        //BuscaLargura.geraFilhos(a, grafo, objetivo);
        a.printArestas();

        for (Aresta aa = a.getPrimAresta(); aa != null; aa = aa.getProxima()) {
            System.out.println("Estado:");
            aa.getVerticeDestino().printEstado();
            System.out.println();
        }

        System.out.println("Criando filhos de b: ");
        b = a.getPrimAresta().getVerticeDestino();
        //BuscaLargura.geraFilhos(b, grafo, objetivo);
        

        for (Aresta aaa = b.getPrimAresta(); aaa != null; aaa = aaa.getProxima()) {
            System.out.println("Estado:");
            aaa.getVerticeDestino().printEstado();
            System.out.println();
        }
        

        System.out.println();
        System.out.println("Criando filhos de c: ");
        c = b.getPrimAresta().getVerticeDestino();
        //BuscaLargura.geraFilhos(c, grafo, objetivo);

        for (Aresta aaa = c.getPrimAresta(); aaa != null; aaa = aaa.getProxima()) {
            System.out.println("Estado:");
            aaa.getVerticeDestino().printEstado();
            System.out.println();
        }
    
        //BuscaLargura.getSolucao().getParentes().imprimePilha();
    }

}

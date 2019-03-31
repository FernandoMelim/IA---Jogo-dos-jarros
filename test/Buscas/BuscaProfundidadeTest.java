package Buscas;

import States.IState;
import States.Estado;
import Grafo.Grafo;
import Grafo.Vertice;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import Grafo.Aresta;

public class BuscaProfundidadeTest {

    private static int nJarros;
    private static int[] limiteMaximoJarros;
    private static BuscaProfundidade um;
    private static Vertice a, b, c, d, e;
    private static IState estado;
    private static Grafo arvore;
    private static int limite;
    private static int[] objetivo;

    @BeforeClass
    public static void setUpClass() {
        IState estado;

        nJarros = 5;
        limiteMaximoJarros = new int[nJarros];
        limiteMaximoJarros[0] = 10;
        limiteMaximoJarros[1] = 20;
        limiteMaximoJarros[2] = 30;
        limiteMaximoJarros[3] = 40;
        limiteMaximoJarros[4] = 50;
        objetivo = new int[nJarros];
        objetivo[0] = 0;
        objetivo[1] = 10;
        objetivo[2] = 20;
        objetivo[3] = 30;
        objetivo[4] = 40;

        estado = new Estado(limite, limiteMaximoJarros, nJarros);
        um = new BuscaProfundidade();
        arvore = new Grafo();

        a = new Vertice(estado);
        b = new Vertice(estado);
        c = new Vertice();
        d = new Vertice();
        /*a.getState().setEstadoJarro(0, 0);

        a.getState().setEstadoJarro(1, 0);
        a.getState().setEstadoJarro(2, 0);
        a.getState().setEstadoJarro(3, 0);

        a.getState().setEstadoJarro(4, 0);*/

        arvore.setVertice(a);
        a.copiaPilha(a);

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
    public void geraFilhosTest() {

        //a.getParentes().imprimeLista();
        BuscaProfundidade.geraFilhos(a, arvore);
        //a.getPrimAresta().getVerticeDestino().getParentes().imprimeLista();

        BuscaProfundidade.geraFilhos(a.getPrimAresta().getVerticeDestino(), arvore);

        c = a.getPrimAresta().getVerticeDestino().getPrimAresta().getVerticeDestino();
        //c.getParentes().imprimeLista();

        BuscaLargura.geraFilhos(c, arvore);

        //c.printArestas();

        System.out.println("Filhos de C:");
        int i = 1;
        for (Aresta bb = c.getPrimAresta(); bb != null; bb = bb.getProxima()) {
            System.out.println("Filho" + i);
            bb.getVerticeDestino().printEstado();
            System.out.println();
            i++;
        }

        d = c.getPrimAresta().getProxima().getVerticeDestino();
        d.getParentes().imprimeLista();

        b.getState().setEstadoJarro(0, 0);
        b.getState().setEstadoJarro(1, 20);
        b.getState().setEstadoJarro(2, 0);
        b.getState().setEstadoJarro(3, 0);
        b.getState().setEstadoJarro(4, 0);
        System.out.println(d.getParentes().verificarNo(b));

    }

}

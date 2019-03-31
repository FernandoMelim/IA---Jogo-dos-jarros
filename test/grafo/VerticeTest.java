package grafo;

import Buscas.BuscaProfundidade;
import Grafo.ListaEncadArestas;
import Grafo.Vertice;
import Grafo.Grafo;
import Grafo.Aresta;
import Utilidade.ListaVertices;
import States.IState;
import States.Estado;
import java.util.Stack;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class VerticeTest {

    private Vertice proximo;
    private int indice;
    private int grauDoVertice = 0;
    private int grauDeSaida = 0;
    private ListaEncadArestas arestasDesteVertice = new ListaEncadArestas();
    private ListaVertices parentes;
    Vertice a, b, c, d, e;
    private IState estado;
    private Grafo grafo = new Grafo();
    private ListaVertices pilha;

    public VerticeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        proximo = new Vertice();
        arestasDesteVertice = new ListaEncadArestas();
        parentes = new ListaVertices();

        int nivel = 0;
        int nJarros = 3;
        int[] limiteMaximoJarros = new int[nJarros];
        limiteMaximoJarros[0] = 5;
        limiteMaximoJarros[1] = 20;
        limiteMaximoJarros[2] = 20;
        estado = new Estado(nivel, limiteMaximoJarros, nJarros);

        a = new Vertice(estado);

        b = new Vertice(estado);
        c = new Vertice(estado);
        d = new Vertice(estado);
        e = new Vertice(estado);

        a.getState().setEstadoJarro(0, 0);
        a.getState().setEstadoJarro(1, 0);
        a.getState().setEstadoJarro(2, 0);

        b.getState().setEstadoJarro(0, 5);
        b.getState().setEstadoJarro(1, 0);
        b.getState().setEstadoJarro(2, 0);

        c.getState().setEstadoJarro(0, 0);
        c.getState().setEstadoJarro(1, 20);
        c.getState().setEstadoJarro(2, 0);

        d.getState().setEstadoJarro(0, 0);
        d.getState().setEstadoJarro(1, 0);
        d.getState().setEstadoJarro(2, 20);

        e.getState().setEstadoJarro(0, 5);
        e.getState().setEstadoJarro(1, 20);
        e.getState().setEstadoJarro(2, 0);

        grafo = new Grafo();
        this.grafo.setVertice(a);
        pilha = new ListaVertices();

   

    }

    @After
    public void tearDown() {
    }

    @Test
    public void copiaPilhaTest() {
        grafo.setVertice(a);
        a.copiaPilha(a);

        grafo.setVertice(b);
        grafo.setAresta(a, b, 0);

        grafo.setVertice(b);
        b.copiaPilha(a);

        grafo.setVertice(c);
        grafo.setVertice(d);
        grafo.setVertice(e);

        c.copiaPilha(b);

        d.copiaPilha(c);

        e.copiaPilha(d);
        e.getParentes().imprimeLista();

        //BuscaProfundidade.geraFilhos(a, grafo);
        //a.printArestas();
        //BuscaProfundidade.geraFilhos(a.getPrimAresta().getVerticeDestino(), grafo);
        //a.getPrimAresta().getVerticeDestino().printEstado();
        //a.getPrimAresta().getVerticeDestino().printArestas();
        //a.getPrimAresta().getVerticeDestino().printEstado();
        /*for(Aresta v = a.getPrimAresta().getVerticeDestino().getPrimAresta(); v!=null; v = v.getProxima()){
            v.getVerticeDestino().printEstado();
            System.out.println();
            
        }*/
    }

}

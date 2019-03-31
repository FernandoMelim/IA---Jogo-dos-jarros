package grafo;

import Grafo.Aresta;
import Grafo.Vertice;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Fernando Hottum
 */
public class ArestaTest {

    Aresta a, b, c, d, e, f;
    int pai, peso;
    Vertice verticeDestino;

    public ArestaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        a = new Aresta();
        b = new Aresta();
        c = new Aresta();
        d = new Aresta();
        e = new Aresta();
        f = new Aresta();
        verticeDestino = new Vertice();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setProxima method, of class Aresta.
     */
    @Test
    public void testSetProxima() {
        a.setProxima(b);
        d = a.getProxima();
        assertEquals(b, d);
    }

    /**
     * Test of setPai method, of class Aresta.
     */

    /**
     * Test of setPeso method, of class Aresta.
     */
    @Test
    public void testSetPeso() {
        a.setPeso(500);
        assertEquals(a.getPeso(), 500);

    }

    /**
     * Test of setVerticeDestino method, of class Aresta.
     */

    /**
     * Test of getPai method, of class Aresta.
     */


    /**
     * Test of getPeso method, of class Aresta.
     */
    @Test
    public void testGetPeso() {
        a.setPeso(500);
        assertEquals(a.getPeso(), 500);
    }

    /**
     * Test of getProxima method, of class Aresta.
     */
    @Test
    public void testGetProxima() {
        a.setProxima(b);
        d = a.getProxima();
        assertEquals(b, d);
    }


}

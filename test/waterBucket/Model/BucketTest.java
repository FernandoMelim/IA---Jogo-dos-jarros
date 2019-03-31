package waterBucket.Model;

import Jarro.Jarro;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BucketTest {

    private static int water;
    private static int max;
    private static Jarro a;

    @BeforeClass
    public static void setUpClass() {
        water = 5;
        max = 10;
        a = new Jarro(max);
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
    public void testGetMaxCapacity() {

    }

    /**
     * Test of setMaxCapacity method, of class Bucket.
     */
    @Test
    public void testSetMaxCapacity() {

    }

    /**
     * Test of getWater method, of class Bucket.
     */
    @Test
    public void testGetWater() {

    }

    /**
     * Test of setWater method, of class Bucket.
     */
    @Test
    public void testSetWater() {
        a.setWater(water);
        assertEquals(a.getWater(), 5);

    }

}

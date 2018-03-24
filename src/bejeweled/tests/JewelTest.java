package bejeweled.tests;

import bejeweled.model.Jewel;
import bejeweled.model.JewelType;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by igor on 24.03.18.
 */
public class JewelTest {
    @Test
    public void testOfSwap(){
        final Jewel first = new Jewel(1, 1, JewelType.CYAN);
        final Jewel second = new Jewel(2, 2, JewelType.RED);
        first.swap(second);
        assertEquals(2, first.getX());
        assertEquals(2, first.getY());
        assertEquals(JewelType.RED, first.getJewelType());
        assertEquals(1, second.getX());
        assertEquals(1, second.getY());
        assertEquals(JewelType.CYAN, second.getJewelType());
    }
}

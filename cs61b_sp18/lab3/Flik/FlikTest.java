import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Blander on 8/26/23.
 */
public class FlikTest {
    @Test
    public void isSameNumber() {
        for (Integer a = 0, b = 0; a < 128; a++, b++) {
            assertTrue(Flik.isSameNumber(a, b));
        }
        for (Integer a = 128, b = 128; a < 500; a++, b++) {
            assertTrue(Flik.isSameNumber(a, b));
        }
    }

    /**
     * A simple explanation:
     * <p>1. When the == operator is used with objects, it compares reference
     * of the objects rather than their values {@source Comes from ChartGPT}
     * <p>2. In Java, {@code Integer} objects whose values are from -128 to 127
     * are cached in the specific memory boxes named {@code cache[]} array, which
     * is aimed to enhance java's performance.
     */

}
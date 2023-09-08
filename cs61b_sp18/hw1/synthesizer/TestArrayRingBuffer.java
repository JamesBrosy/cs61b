package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Blander Liu
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        assertTrue(arb.isEmpty());
        arb.enqueue(0);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(5);
        assertEquals(0, (int) arb.dequeue());
        assertEquals(1, (int) arb.dequeue());
        assertEquals(2, (int) arb.dequeue());
        assertEquals(3, (int) arb.dequeue());
        assertEquals(4, (int) arb.dequeue());
        assertEquals(5, (int) arb.dequeue());
        assertTrue(arb.isEmpty());
        arb.enqueue(6);
        arb.enqueue(7);
        arb.enqueue(8);
        arb.enqueue(9);
        assertFalse(arb.isEmpty());
        assertFalse(arb.isFull());
        arb.enqueue(0);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(5);
        assertTrue(arb.isFull());
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by Blander on 8/26/23.
 */
public class TestArrayDequeGold {

    @Test
    public void testArrayDeque() {
        // the array deque should be tested
        StudentArrayDeque<Integer>  sad = new StudentArrayDeque<>();
        // the expected array deque
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        // times of for loop
        int loopTimes = 1000;

        for (int i = 0; i < loopTimes; i++) {
            // generate random double between 0 and 1 used to select method;
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.25) {
                // test addFirst method
                sad.addFirst(i);
                ads.addFirst(i);
                assertEquals("addFirst(" + i + ")\n",
                        ads.get(0), sad.get(0));
            } else if (numberBetweenZeroAndOne < 0.5) {
                // test addLast method
                sad.addLast(i);
                ads.addLast(i);
                assertEquals("addLast(" + i + ")\n",
                        ads.get(ads.size() - 1), sad.get(sad.size() - 1));
            } else if (numberBetweenZeroAndOne < 0.75) {
                // test removeFirst method
                assertEquals("removeFirst()",
                        ads.removeFirst(), sad.removeFirst());
            } else {
                // test removeLast method
                assertEquals("removeLast()",
                        ads.removeLast(), sad.removeLast());
            }
        }
    }
}

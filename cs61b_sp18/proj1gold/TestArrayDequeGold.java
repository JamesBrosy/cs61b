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

        // output messages
        String msg = "";

        for (int i = 0; i < loopTimes; i++) {
            // generate random integer between 0 and 4 used to select method;
            int randomNumber = StdRandom.uniform(4);

            switch (randomNumber) {
                case 0:
                    // test addFirst method
                    sad.addFirst(i);
                    ads.addFirst(i);
                    msg += "addFirst(" + i + ")\n";
                    assertEquals(msg, ads.get(0), sad.get(0));
                    break;
                case 1:
                    // test addLast method
                    sad.addLast(i);
                    ads.addLast(i);
                    msg += "addLast(" + i + ")\n";
                    assertEquals(msg, ads.get(ads.size() - 1), sad.get(sad.size() - 1));
                    break;
                case 2:
                    // test removeFirst method
                    msg += "removeFirst()\n";
                    assertEquals(msg, ads.removeFirst(), sad.removeFirst());
                    break;
                case 3:
                    // test removeLast method
                    msg += "removeLast()\n";
                    assertEquals(msg, ads.removeLast(), sad.removeLast());
                    break;
            }
        }
    }
}

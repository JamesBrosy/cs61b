package gh2;

import deque.ArrayDeque;
import deque.Deque;


/**
 * Created by Blander on 9/9/23.
 */
public class Harp {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private Deque<Double> buffer;
    private int capacity;

    /* Create a guitar string of the given frequency.  */
    public Harp(double frequency) {
        buffer = new ArrayDeque<>();
        capacity = (int) Math.round(SR / frequency) * 2;
        while (buffer.size() != capacity) {
            buffer.addLast(.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        while (!buffer.isEmpty()) {
            buffer.removeFirst();
        }
        while (buffer.size() != capacity) {
            buffer.addLast(Math.random() - 0.5);
        }
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.get(0);
    }

    public void flip() {
        double tmp = buffer.removeFirst();
        double avg = tmp + (buffer.get(0) - tmp) / 2;
        buffer.addLast(-avg * DECAY);
    }

}

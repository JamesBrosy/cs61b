package gh2;

import deque.ArrayDeque;
import deque.Deque;

/**
 * Created by Blander on 9/9/23.
 */
public class Drum {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = 1.0; // energy decay factor

    /* Buffer for storing sound data. */
    private Deque<Double> buffer;
    private int capacity;

    /* Create a guitar string of the given frequency.  */
    public Drum(double frequency) {
        buffer = new ArrayDeque<>();
        capacity = (int) Math.round(SR / frequency);
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

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void flip() {
        double tmp = buffer.removeFirst();
        double avg = tmp + (buffer.get(0) - tmp) / 2;
        if (Math.random() < 0.5) {
            buffer.addLast(-avg * DECAY);
        } else {
            buffer.addLast(avg * DECAY);
        }
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.get(0);
    }

}

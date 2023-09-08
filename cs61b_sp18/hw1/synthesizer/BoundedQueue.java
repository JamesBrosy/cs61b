package synthesizer;

/**
 * Created by Blander on 9/8/23.
 */
public interface BoundedQueue<T> extends Iterable<T> {

    /** return size of the buffer */
    int capacity();

    /** return number of items currently in the buffer */
    int fillCount();

    /** add item {@code x} to the end */
    void enqueue(T x);

    /** delete and return item from the front */
    T dequeue();

    /** return (but do not delete) item from the front */
    T peek();

    /** is the buffer empty ({@link #fillCount()} equals zero) ? */
    default boolean isEmpty() {
        return fillCount() == 0;
    }

    /** is the buffer full ({@link #fillCount()} is same as capacity) ? */
    default boolean isFull() {
        return fillCount() == capacity();
    }
}

package synthesizer;

import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private final T[] rb;

    private class ArrayRingBufferIterator implements Iterator<T> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < fillCount;
        }

        @Override
        public T next() {
            return rb[add(first, index++)];
        }
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.capacity   = capacity;
        this.fillCount  = 0;
        this.first      = 0;
        this.last       = 0;
        this.rb         = (T[]) new Object[capacity];
    }

    /** return the index {@code pos} adds {@code increment} */
    private int add(int pos, int increment) {
        return pos + increment < capacity
                ? pos + increment : pos + increment - capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last = add(last, 1);
        ++fillCount;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T tmp = rb[first];
        rb[first] = null;
        first = add(first, 1);
        --fillCount;
        return tmp;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        return rb[first];
    }

    /** return the iterator of the ArrayRingBuffer */
    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

}

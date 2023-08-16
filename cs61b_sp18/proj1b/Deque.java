public interface Deque<T> {

    /** Adds an item of type {@code T} to the front of the deque */
    void addFirst(T item);

    /** Adds an item of type {@code T} to the back of deque */
    void addLast(T item);

    /** Returns true if deque is empty, false otherwise */
    boolean isEmpty();

    /** Returns the number of items in the deque */
    int size();

    /** Returns the items in the deque from first to last */
    void printDeque();

    /** Removes and returns the item at the front of the deque */
    T removeFirst();

    /** Removes and returns the item at the back of the deque */
    T removeLast();

    /** Gets the item at the given index */
    T get(int index);
}

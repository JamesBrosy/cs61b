public class LinkedListDeque<T> {

    private final Node sentinel;

    private int size;

    private class Node {

        private T data;

        private Node prev;
        private Node next;

        Node() {
            this.data = null;
            this.prev = this;
            this.next = this;
        }

        Node(T data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    /** Creates an empty linked list deque */
    public LinkedListDeque() {
        this.sentinel = new Node();
        this.size = 0;
    }

    /** Create a deep copy of other linked list deque */
    public LinkedListDeque(LinkedListDeque<T> other) {
        sentinel =  new Node();
        Node tmp = sentinel;
        Node first = other.sentinel.next;
        while (first != null) {
            tmp.next = new Node(first.data, tmp, sentinel);
            sentinel.prev = tmp.next;
            tmp = tmp.next;
            first = first.next;
        }
        size = other.size;
    }

    /** Adds an item of type {@code T} to the front of the deque */
    public void addFirst(T item) {
        Node tmpFirst = sentinel.next;
        tmpFirst.prev = new Node(item, sentinel, tmpFirst);
        sentinel.next = tmpFirst.prev;
        size++;
    }

    /** Adds an item of type {@code T} to the back of deque */
    public void addLast(T item) {
        Node tmpLast = sentinel.prev;
        tmpLast.next = new Node(item, sentinel.prev, sentinel);
        sentinel.prev = tmpLast.next;
        size++;
    }

    /** Returns true if deque is empty, false otherwise */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last */
    public void printDeque() {
        Node tmp = sentinel.next;
        while (tmp != sentinel) {
            System.out.print(tmp.data + " ");
            tmp = tmp.next;
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size--;
        Node tmpFirst = sentinel.next;
        sentinel.next = tmpFirst.next;
        tmpFirst.next.prev = sentinel;
        return tmpFirst.data;
    }

    /** Removes and returns the item of back of the deque */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size--;
        Node tmpLast = sentinel.prev;
        sentinel.prev = tmpLast.prev;
        tmpLast.prev.next = sentinel;
        return tmpLast.data;
    }

    /** Gets the item at the given index */
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node tmp = sentinel;
        for (int i = 0; i < index + 1; i++) {
            tmp = tmp.next;
        }
        return tmp.data;
    }
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return get(index, sentinel.next);
    }

    private T get(int index, Node first) {
        if (index == 0) {
            return first.data;
        }
        return get(--index, first.next);
    }

}

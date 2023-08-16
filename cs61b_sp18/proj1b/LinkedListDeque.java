public class LinkedListDeque<T> implements Deque<T> {

    private int size;

    private final Node sentinel;

    private class Node {
        T data;
        Node prev;
        Node next;

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

    public LinkedListDeque() {
        this.size = 0;
        this.sentinel = new Node();
    }

    public LinkedListDeque(LinkedListDeque<T> other) {
        sentinel = new Node();
        Node tmp = sentinel;
        Node first = other.sentinel.next;
        while (first != other.sentinel) {
            tmp.next = new Node(first.data, tmp, sentinel);
            tmp = tmp.next;
            first = first.next;
        }
        sentinel.prev = tmp;
        size = other.size;
    }

    @Override
    public void addFirst(T item) {
        Node tmpFirst = sentinel.next;
        tmpFirst.prev = new Node(item, sentinel, tmpFirst);
        sentinel.next = tmpFirst.prev;
        size++;
    }

    @Override
    public void addLast(T item) {
        Node tmpLast = sentinel.prev;
        tmpLast.next = new Node(item, sentinel.prev, sentinel);
        sentinel.prev = tmpLast.next;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node tmp = sentinel.next;
        while (tmp != sentinel) {
            System.out.print(tmp.data + " ");
            tmp = tmp.next;
        }
        System.out.println();
    }

    @Override
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

    @Override
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

    @Override
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
}

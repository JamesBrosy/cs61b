import java.util.ArrayList;
import java.util.List;

/**
 * Created by Blander on 9/1/23.
 */
public class LinkedListDeque<T> implements Deque<T> {

    private final Node<T> sentinel;

    private int size;

    private static class Node<T> {
        private T data;

        private Node<T> prev;
        private Node<T> next;

        Node() {
            this.data = null;
            this.prev = this;
            this.next = this;
        }

        Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    public LinkedListDeque() {
        this.sentinel = new Node<>();
        this.size = 0;
    }

    @Override
    public void addFirst(T item) {
        Node<T> tmpFirst = sentinel.next;
        tmpFirst.prev = new Node<>(item, sentinel, tmpFirst);
        sentinel.next = tmpFirst.prev;
        size++;
    }

    @Override
    public void addLast(T item) {
        Node<T> tmpLast = sentinel.prev;
        tmpLast.next = new Node<>(item, sentinel.prev, sentinel);
        sentinel.prev = tmpLast.next;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> res = new ArrayList<>();
        Node<T> tmp = sentinel.next;
        for (int i = 0; i < size; i++) {
            res.add(tmp.data);
            tmp = tmp.next;
        }
        return res;
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
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size--;
        Node<T> tmpFirst = sentinel.next;
        sentinel.next = tmpFirst.next;
        tmpFirst.next.prev = sentinel;
        return tmpFirst.data;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size--;
        Node<T> tmpLast = sentinel.prev;
        sentinel.prev = tmpLast.prev;
        tmpLast.prev.next = sentinel;
        return tmpLast.data;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node<T> tmp = sentinel;
        for (int i = 0; i < index + 1; i++) {
            tmp = tmp.next;
        }
        return tmp.data;
    }

    /** Private helper method for {@link #getRecursive} */
    private T get(int index, Node<T> first) {
        if (index == 0) {
            return first.data;
        }
        return get(--index, first.next);
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return get(index, sentinel.next);
    }

}

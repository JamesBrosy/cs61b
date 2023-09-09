package deque;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Blander on 9/9/23.
 */
public class LinkedListDeque<E> implements Deque<E> {

    private final Node<E> sentinel;

    private int size;

    private class LinkedListDequeItr implements Iterator<E> {

        private Node<E> node = sentinel.next;

        @Override
        public boolean hasNext() {
            return node != sentinel;
        }

        @Override
        public E next() {
            E tmp = node.data;
            node = node.next;
            return tmp;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListDequeItr();
    }

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
    public void addFirst(E item) {
        Node<E> tmpFirst = sentinel.next;
        tmpFirst.prev = new Node<>(item, sentinel, tmpFirst);
        sentinel.next = tmpFirst.prev;
        size++;
    }

    @Override
    public void addLast(E item) {
        Node<E> tmpLast = sentinel.prev;
        tmpLast.next = new Node<>(item, sentinel.prev, sentinel);
        sentinel.prev = tmpLast.next;
        size++;
    }

    @Override
    public List<E> toList() {
        List<E> res = new ArrayList<>();
        Node<E> tmp = sentinel.next;
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
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size--;
        Node<E> tmpFirst = sentinel.next;
        sentinel.next = tmpFirst.next;
        tmpFirst.next.prev = sentinel;
        return tmpFirst.data;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        size--;
        Node<E> tmpLast = sentinel.prev;
        sentinel.prev = tmpLast.prev;
        tmpLast.prev.next = sentinel;
        return tmpLast.data;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node<E> tmp = sentinel;
        for (int i = 0; i < index + 1; i++) {
            tmp = tmp.next;
        }
        return tmp.data;
    }

    /**
     * Private helper method for {@link #getRecursive}
     */
    private E get(int index, Node<E> first) {
        if (index == 0) {
            return first.data;
        }
        return get(--index, first.next);
    }

    @Override
    public E getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return get(index, sentinel.next);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof LinkedListDeque<?> lld) {
            if (this.size != lld.size) {
                return false;
            }
            Node<E> node1 = sentinel.next;
            Node<?> node2 = lld.sentinel.next;
            while (node1 != sentinel) {
                if (!node1.data.equals(node2.data)) {
                    return false;
                }
                node1 = node1.next;
                node2 = node2.next;
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder buffer = new StringBuilder("[");
        Node<E> node = sentinel.next;
        for (int i = 0; i < size - 1; ++i) {
            buffer.append(node.data.toString()).append(", ");
            node = node.next;
        }
        buffer.append(node.data.toString()).append("]");
        return buffer.toString();
    }
}

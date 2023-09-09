package deque;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Blander on 9/9/23.
 */
public class ArrayDeque<T> implements Deque<T>{

    private final int DEFAULT_CAPACITY = 8;

    private T[] items;
    private int size;

    private int front;
    private int end;

    /** pointer to an element in array adds {@code num} */
    private int idxAdd(int ptr, int num) {
        return ptr + num < items.length ? ptr + num : ptr + num - items.length;
    }

    /** pointer to an element in array minuses {@code num} */
    private int idxMinus(int ptr, int num) {
        return ptr < num ? items.length + ptr - num : ptr - num;
    }

    /**
     * Copies an array from the specified source array, beginning at the
     * specified position, to the specified position of the destination array.
     * A subsequence of array components are copied from the source
     * array referenced by {@code src} to the destination array referenced by {@code dest}.
     * The number of components copied is equal to the {@code length} argument.
     * The components at positions {@code srcPos} through srcPos+length-1 in the source
     * array are copied into positions {@code destPos} through destPos+length-1,
     * respectively, of the destination array.
     * @param      src      the source array.
     * @param      srcPos   starting position in the source array.
     * @param      dest     the destination array.
     * @param      destPos  starting position in the destination data.
     * @param      length   the number of array elements to be copied.
     */
    private void arraycopy(T[] src, int srcPos, T[] dest, int destPos, int length) {
        for (int i = 0; i < length; i++) {
            dest[destPos] = src[srcPos];
            srcPos  = idxAdd(srcPos, 1);
            destPos = idxAdd(destPos, 1);
        }
    }

    /** resize array {@code items} */
    private void resize() {
        if (size < items.length) {
            return;
        }
        T[] tmp = (T[]) new Object[items.length << 1];
        arraycopy(items, front, tmp, 0, size);
        items = tmp;
        front = 0;
        end   = size;
    }

    /** shrink array {@code items} */
    private void shrink() {
        if (items.length < DEFAULT_CAPACITY << 1) {
            return;
        }
        if (items.length < size << 2) {
            return;
        }
        T[] tmp = (T[]) new Object[items.length >> 1];
        arraycopy(items, front, tmp, 0, size);
        items = tmp;
        front = 0;
        end   = size;
    }

    public ArrayDeque() {
        this.size  = 0;
        this.front = 0;
        this.end   = 0;
        this.items = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void addFirst(T item) {
        resize();
        front = idxMinus(front, 1);
        items[front] = item;
        size++;
    }

    @Override
    public void addLast(T item) {
        resize();
        items[end] = item;
        end = idxAdd(end, 1);
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> res = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            res.add(get(i));
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
        T tmp = items[front];
        items[front] = null;
        front = idxAdd(front, 1);
        shrink();
        return tmp;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size--;
        end = idxMinus(end, 1);
        T tmp = items[end];
        items[end] = null;
        shrink();
        return tmp;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int correctIndex = idxAdd(front, index);
        return items[correctIndex];
    }

    @Override
    public T getRecursive(int index) {
        return get(index);
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeItr();
    }

    private class ArrayDequeItr implements Iterator<T>{

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            return get(index++);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof ArrayDeque<?> ad) {
            if (this.size != ad.size) {
                return false;
            }
            for (int i = 0; i < size; ++i) {
                if (!get(i).equals(ad.get(i))) {
                    return false;
                }
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
        for (int i = 0; i < size - 1; ++i) {
            buffer.append(get(i).toString()).append(", ");
        }
        buffer.append(get(size - 1).toString()).append("]");
        return buffer.toString();
    }
}

import com.github.javaparser.ast.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Blander on 9/2/23.
 */
public class ArrayDeque<T> implements Deque<T>{

    private final int DEFAULT_CAPACITY = 8;

    private T[] items;
    private int size;

    private int front;
    private int end;

    /** pointer to an element in array adds {@code num} */
    private int add(int num, int ptr) {
        return ptr + num >= items.length ? ptr + num - items.length : ptr + num;
    }

    /** pointer to an element in array minuses {@code num} */
    private int minus(int num, int ptr) {
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
            srcPos  = add(1, srcPos);
            destPos = add(1, destPos);
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
        front = minus(1, front);
        items[front] = item;
        size++;
    }

    @Override
    public void addLast(T item) {
        resize();
        items[end] = item;
        end = add(1, end);
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
        front = add(1, front);
        shrink();
        return tmp;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size--;
        end = minus(1, end);
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
        int correctIndex = add(index, front);
        return items[correctIndex];
    }
}

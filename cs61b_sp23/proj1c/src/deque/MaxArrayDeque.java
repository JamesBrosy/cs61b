package deque;

import java.util.Comparator;

/**
 * Created by Blander on 9/9/23.
 */
public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private final Comparator<T> c;

    public MaxArrayDeque(Comparator<T> c) {
        this.c = c;
    }

    private T getMax(Comparator<T> c) {
        T max = get(0);
        for (int i = 1; i < size(); ++i) {
            T comp = get(i);
            if (c.compare(max, comp) < 0) {
                max = comp;
            }
        }
        return max;
    }

    public T max() {
        return getMax(c);
    }

    public T max(Comparator<T> c) {
        return getMax(c);
    }
}

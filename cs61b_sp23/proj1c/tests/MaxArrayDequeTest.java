import deque.MaxArrayDeque;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDequeTest {

    private static class MaxArrayDequeComp<T extends Comparable<T>> implements Comparator<T> {

        @Override
        public int compare(T o1, T o2) {
            return o1.compareTo(o2);
        }
    }

    @Test
    void max() {
        Comparator<Integer> c = new MaxArrayDequeComp<>();
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(c);
        mad.addLast(12);
        mad.addLast(34);
        mad.addLast(10);
        mad.addLast(37);
        mad.addLast(37);
        mad.addLast(56);
        mad.addLast(43);
        mad.addLast(21);
        mad.addLast(56);
        mad.addLast(89);
        mad.addLast(32);
        assertThat(mad.max()).isEqualTo(89);
    }

    @Test
    void testMax() {
        Comparator<Integer> c = new MaxArrayDequeComp<>();
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(c);
        mad.addLast(12);
        mad.addLast(34);
        mad.addLast(10);
        mad.addLast(37);
        mad.addLast(37);
        mad.addLast(56);
        mad.addLast(43);
        mad.addLast(21);
        mad.addLast(56);
        mad.addLast(89);
        mad.addLast(32);
        assertThat(mad.max(c)).isEqualTo(89);
    }
}

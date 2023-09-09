import deque.ArrayDeque;
import deque.LinkedListDeque;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by Blander on 9/9/23.
 */
public class ObjectMethodsTest {
    @Test
    public void testEquals() {
        // phase 1: test ArrayDeque
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ArrayDeque<Integer> ad2 = new ArrayDeque<>();
        ArrayDeque<Integer> ad3 = ad1;
        assertThat(ad3).isEqualTo(ad1);
        assertThat(ad1).isEqualTo(ad2);

        ad1.addLast(0);
        ad1.addLast(1);
        ad1.addLast(2);
        ad1.addLast(3);
        ad1.addLast(4);
        ad1.addLast(128);
        ad1.addFirst(0);
        ad1.addFirst(1);
        ad1.addFirst(2);
        ad1.addFirst(3);
        ad2.addLast(0);
        ad2.addLast(1);
        ad2.addLast(2);
        ad2.addLast(3);
        ad2.addLast(4);
        ad2.addLast(128);
        ad2.addFirst(0);
        ad2.addFirst(1);
        ad2.addFirst(2);
        ad2.addFirst(3);
        assertThat(ad1).isEqualTo(ad2);

        // phase 2: LinkedListDeque
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        LinkedListDeque<Integer> lld2 = new LinkedListDeque<>();
        LinkedListDeque<Integer> lld3 = lld1;
        assertThat(lld3).isEqualTo(lld1);
        assertThat(lld1).isEqualTo(lld2);

        lld1.addLast(0);
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.addLast(4);
        lld1.addLast(128);
        lld1.addFirst(0);
        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.addFirst(3);
        lld2.addLast(0);
        lld2.addLast(1);
        lld2.addLast(2);
        lld2.addLast(3);
        lld2.addLast(4);
        lld2.addLast(128);
        lld2.addFirst(0);
        lld2.addFirst(1);
        lld2.addFirst(2);
        lld2.addFirst(3);
        assertThat(lld1).isEqualTo(lld2);
    }

    @Test
    public void testToString() {
        // phase 1: ArrayDeque
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addLast(0);
        ad1.addLast(1);
        ad1.addLast(2);
        ad1.addLast(3);
        ad1.addLast(4);
        ad1.addLast(128);
        ad1.addFirst(0);
        ad1.addFirst(1);
        ad1.addFirst(2);
        ad1.addFirst(3);
        assertThat(ad1.toString()).isEqualTo("[3, 2, 1, 0, 0, 1, 2, 3, 4, 128]");
        ArrayDeque<Integer> ad2 = new ArrayDeque<>();
        assertThat(ad2.toString()).isEqualTo("[]");

        // phase 2: LinkedListDeque
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addLast(0);
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.addLast(4);
        lld1.addLast(128);
        lld1.addFirst(0);
        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.addFirst(3);
        assertThat(lld1.toString()).isEqualTo("[3, 2, 1, 0, 0, 1, 2, 3, 4, 128]");
        ArrayDeque<Integer> lld2 = new ArrayDeque<>();
        assertThat(lld2.toString()).isEqualTo("[]");
    }

    @Test
    public void testIterator() {
        // phase 1: ArrayDeque
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        for (int i : ad) {
            assertThat(i).isNull();
        }
        ad.addLast(0);
        ad.addLast(1);
        ad.addLast(2);
        ad.addLast(3);
        ad.addLast(4);
        ad.addLast(128);
        ad.addFirst(0);
        ad.addFirst(1);
        ad.addFirst(2);
        ad.addFirst(3);
        int[] expected1 = new int[]{3, 2, 1, 0, 0, 1, 2, 3, 4, 128};
        int j = 0;
        for (int i : ad) {
            assertThat(i).isEqualTo(expected1[j++]);
        }

        // phase 1: ArrayDeque
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        for (int i : lld) {
            assertThat(i).isNull();
        }
        lld.addLast(0);
        lld.addLast(1);
        lld.addLast(2);
        lld.addLast(3);
        lld.addLast(4);
        lld.addLast(128);
        lld.addFirst(0);
        lld.addFirst(1);
        lld.addFirst(2);
        lld.addFirst(3);
        int[] expected2 = new int[]{3, 2, 1, 0, 0, 1, 2, 3, 4, 128};
        int k = 0;
        for (int i : lld) {
            assertThat(i).isEqualTo(expected2[k++]);
        }
    }

}

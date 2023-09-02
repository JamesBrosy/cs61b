import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class ArrayDequeTest {

    @Test
    void addFirstAndAddLastTest() {
        Deque<Integer> ad = new ArrayDeque<>();

        ad.addLast(3);
        ad.addLast(4);
        ad.addLast(5);
        ad.addLast(6);
        ad.addLast(7);
        ad.addFirst(2);
        ad.addFirst(1);
        ad.addFirst(0);
        ad.addFirst(-1);
        ad.addFirst(-2);

        assertThat(ad.toList()).containsExactly(-2, -1, 0, 1, 2, 3, 4, 5, 6, 7).inOrder();
    }

    @Test
    void removeFirstAndRemoveLastTest() {
        Deque<Integer> ad = new ArrayDeque<>();

        ad.addLast(3);
        ad.addLast(4);
        ad.addLast(5);
        ad.addLast(6);
        ad.addLast(7);
        ad.addFirst(2);
        ad.addFirst(1);
        ad.addFirst(0);
        ad.addFirst(-1);
        ad.addFirst(-2);

        ad.removeFirst();
        ad.removeLast();
        ad.removeFirst();
        ad.removeLast();
        ad.removeFirst();
        ad.removeLast();

        assertThat(ad.toList()).containsExactly(1, 2, 3, 4).inOrder();
    }

    @Test
    void isEmpty() {
        Deque<Integer> ad = new ArrayDeque<>();
        assertThat(ad.isEmpty()).isTrue();
        ad.addFirst(0);
        ad.addLast(1);
        assertThat(ad.isEmpty()).isFalse();
        ad.removeFirst();
        ad.removeLast();
        assertThat(ad.isEmpty()).isTrue();
    }

    @Test
    void size() {
        Deque<Integer> ad = new ArrayDeque<>();
        assertThat(ad.size()).isEqualTo(0);
        ad.addLast(0);
        ad.addLast(1);
        assertThat(ad.size()).isEqualTo(2);
        ad.removeLast();
        ad.removeFirst();
        assertThat(ad.size()).isEqualTo(0);
    }

    @Test
    void get() {
        Deque<Integer> ad = new ArrayDeque<>();

        ad.addLast(3);    // [3]
        ad.addLast(4);    // [3, 4]
        ad.addFirst(2);   // [2, 3, 4]
        ad.addFirst(1);   // [1, 2, 3, 4]

        assertThat(ad.get(-1)).isNull();
        assertThat(ad.get(0)).isEqualTo(1);
        assertThat(ad.get(1)).isEqualTo(2);
        assertThat(ad.get(2)).isEqualTo(3);
        assertThat(ad.get(3)).isEqualTo(4);
        assertThat(ad.get(4)).isNull();
    }
}

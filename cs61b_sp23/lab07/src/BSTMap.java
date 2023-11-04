import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Bland on 11/3/23.
 */
public class BSTMap<K extends Comparable<K>, V>  implements Map61B<K, V> {

    private Node root;

    private class Node {
        private final K key;
        private V value;
        private Node left, right;
        private int size;

        public Node(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
            this.left = null;
            this.right = null;
        }
    }

    /**
     *  Insert the specified key-value pair into the {@code BSTMap}, overwriting the old
     *  value with the new value if the {@code BSTMap} already contains the specified key.
     *  Deletes the specified key (and its associated value) from this {@code BSTMap}
     *  if the specified value is {@code null}.
     *
     * @param key the key
     * @param value the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("calls put() with a null key");
        }
        root = put(root, key, value);
    }

    private Node put(Node x, K key, V value) {
        if (x == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
            return x;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * Return the value associated with the given key.
     *
     * @param key the key
     * @return the value associated with the given key if the key is in the
     *         {@code BSTMap} and {@code null} if the key is not in the {@code BSTMap}
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node x, K key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with a null key");
        }
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.value;
        }
    }

    /**
     * Does this {@code BSTMap} contain the given key?
     *
     * @param key the key
     * @return {@code true} if this {@code BSTMap} contains {@code key} and
     *         {@code false} otherwise.
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to containsKey() is null");
        }
        if (root == null) {
            return false;
        }
        Node tmp = root;
        while (true) {
            int cmp = key.compareTo(tmp.key);
            if (cmp < 0 && tmp.left != null) {
                tmp = tmp.left;
            } else if (cmp > 0 && tmp.right != null) {
                tmp = tmp.right;
            } else {
                return cmp == 0;
            }
        }
    }

    /**
     * Returns the number of key-value pairs in this {@code BSTMap}.
     *
     * @return the number of key-value pairs in this {@code BSTMap}.
     */
    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        return x == null ? 0 : x.size;
    }

    /**
     *
     */
    @Override
    public void clear() {
        root = null;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new TreeSet<>();
        Node tmp = root;
        while (size(tmp) != 0) {
            keySet.add(tmp.key);
            tmp = remove(tmp, tmp.key);
        }
        return keySet;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("calls remove() with a null key");
        }
        V tmp = get(key);
        if (tmp == null) {
            return null;
        }
        root = remove(root, key);
        return tmp;
    }

    /**
     * Removes the specified key and its associated value from this {@code BSTMap}
     * (if the key is in this {@code BSTMap})
     *
     * @param x the root
     * @param key the key
     * @return the root after remove operation
     */
    private Node remove(Node x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = remove(x.left, key);
        } else if (cmp > 0) {
            x.right = remove(x.right, key);
        } else {
            if (x.left == null) {
                return x.right;
            }
            if (x.right == null) {
                return x.left;
            }
            Node tmp = x;
            x = min(x.right);
            x.size = tmp.size;
            x.right = removeMin(tmp.right);
            x.left = tmp.left;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * Returns the smallest key in the binary search tree rooted by {@code x}.
     *
     * @param x the root of the binary search tree
     * @return the smallest key in the binary search tree rooted by {@code x}
     */
    private Node min(Node x) {
        return x.left == null ? x : min(x.left);
    }

    /**
     * Removes the smallest key and associated value from the {@code BSTMap}.
     *
     * @param x the root
     * @return the root
     */
    private Node removeMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = removeMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    public void printInOrder() {
        System.out.println(keySet().toString());
    }
}

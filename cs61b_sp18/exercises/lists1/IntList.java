public class IntList {
	public int first;
	public IntList rest;

	public IntList(int f, IntList r) {
		first = f;
		rest = r;
	}

	/** Return the size of the list using... recursion! */
	public int size() {
		if (rest == null) {
			return 1;
		}
		return 1 + rest.size();
	}

	/** Return the size of the list using no recursion! */
	public int iterativeSize() {
		int size = 0;
		IntList L = this;
		while (L != null) {
			size++;
			L = L.rest;
		}
		return size;
	}

	/** Returns the ith value in this list.*/
	public int get(int i) {
		IntList L = this;
		while (i-- != 0) {
			L = L.rest;
		}
		return L.first;
	}

	public static void main(String[] args) {
		IntList L = new IntList(15, null);
		L = new IntList(10, L);
		L = new IntList(5, L);

		System.out.println(L.iterativeSize());
	}
} 
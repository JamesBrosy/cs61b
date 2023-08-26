public class OffByN implements CharacterComparator {

    int num;

    public OffByN(int N) {
        this.num = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return (x > y ? x - y : y - x) == num;
    }
}

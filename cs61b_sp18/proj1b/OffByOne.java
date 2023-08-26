public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        return (x > y ? x - y : y - x) == 1;
    }
}

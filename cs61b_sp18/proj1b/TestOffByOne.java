import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    /*
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    Uncomment this class once you've created your CharacterComparator
    interface and OffByOne class. **/
    @Test
    public void equalCharsTest() {
        OffByOne obo = new OffByOne();
        assertTrue(obo.equalChars('a', 'b'));
        assertTrue(obo.equalChars('%', '&'));
        assertTrue(obo.equalChars('A', 'B'));
        assertFalse(obo.equalChars('a', 'c'));
        assertFalse(obo.equalChars('A', 'C'));
    }
}

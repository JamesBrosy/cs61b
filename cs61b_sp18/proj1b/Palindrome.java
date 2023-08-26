public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        // Deque<Character> res = new ArrrayDeque<>();
        Deque<Character> res = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            res.addLast(word.charAt(i));
        }
        return res;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque = wordToDeque(word);
        while (true) {
            if (wordDeque.isEmpty() || wordDeque.size() == 1) {
                return true;
            }
            if (wordDeque.removeFirst() != wordDeque.removeLast()) {
                return false;
            }
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);
        while (true) {
            if (wordDeque.isEmpty() || wordDeque.size() == 1) {
                return true;
            }
            if (!cc.equalChars(wordDeque.removeFirst(), wordDeque.removeLast())) {
                return false;
            }
        }
    }
}

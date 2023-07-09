import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        Map<Character, Integer> result = new TreeMap<>();
        char ch = 'a';
        final int letterTotal = 26;
        for (int i = 1; i <= letterTotal; i++, ch++) {
            result.put(ch, i);
        }
        return result;
    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        Map<Integer, Integer> result = new TreeMap<>();
        for (int item : nums) {
            result.put(item, item * item);
        }
        return result;
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        Map<String, Integer> result = new TreeMap<>();
        ArrayList<String> listTmp = new ArrayList<>(words);
        String keyTmp = listTmp.get(0);
        result.put(keyTmp, 1);
        listTmp.remove(keyTmp);
        while (!listTmp.isEmpty()) {
            if (keyTmp.equals(listTmp.get(0))) {
                result.put(keyTmp, result.get(keyTmp) + 1);
                listTmp.remove(keyTmp);
            } else {
                keyTmp = listTmp.get(0);
                result.put(keyTmp, 1);
                listTmp.remove(keyTmp);
            }
        }
        return result;
    }

}

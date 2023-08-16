import java.util.ArrayList;
import java.util.List;


public class ListExercises {

    /** Returns the total sum in a list of integers */
    public static int sum(List<Integer> L) {
        int sum = 0;
        for (int item : L) {
            sum += item;
        }
        return sum;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        List<Integer> result = new ArrayList<>();
        for (int item : L) {
            if (item % 2 == 0) {
                result.add(item);
            }
        }
        return result;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        List<Integer> result = new ArrayList<>();
        for (int item1 : L1) {
            for (int item2 : L2) {
                if (item1 == item2) {
                    result.add(item1);
                    break;
                }
            }
        }
        return result;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        int count = 0;
        for (String str : words) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == c) {
                    count++;
                }
            }
        }
        return count;
    }
}

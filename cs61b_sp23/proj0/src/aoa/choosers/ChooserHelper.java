package aoa.choosers;

import com.github.javaparser.utils.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ChooserHelper {

    /** time complexity is O(wordLength)*/
    public static Pair<Integer, Pair<String, String>>  generateIntToPair(
            String word,
            char letter,
            String oldPattern) {
        StringBuilder newPattern = new StringBuilder(oldPattern);
        int sum = 0;
        for (int i = 0; i < word.length(); i++) {
            if (letter == word.charAt(i)) {
                sum++;
                newPattern.replace(i, i + 1, Character.toString(letter));
            }
        }
        return new Pair<>(sum, new Pair<>(newPattern.toString(), word));
    }

    /** time complexity is O(wordPool.size())*/
    public static Map<String, Pair<ArrayList<String>, Integer>> generatePatternToPairMap(
            List<String> wordPool,
            char letter,
            String oldPattern
    ) {
        Map<String, Pair<ArrayList<String>, Integer>> patternToPairMap = new TreeMap<>();
        for (String item : wordPool) {
            var IntToPair = generateIntToPair(item, letter, oldPattern);
            if (!patternToPairMap.containsKey(IntToPair.b.a)) {
                var value = new Pair<>(new ArrayList<>(List.of(IntToPair.b.b)), IntToPair.a);
                patternToPairMap.put(IntToPair.b.a, value);
                continue;
            }
            patternToPairMap.get(IntToPair.b.a).a.add(IntToPair.b.b);
        }
        return patternToPairMap;
    }
}

package aoa.guessers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GuesserHelper {

    public static String generateRegex(
            String          pattern,
            List<Character> guesses
    ) {
        if (guesses.isEmpty()) {
            return pattern.replace('-', '.');
        }
        StringBuilder replStr = new StringBuilder(guesses.toString().replace(", ", ""));
        replStr.insert(1, '^');
        return pattern.replace("-", replStr);
    }

    public static List<String> getMatchedWords(
            String       regex,
            List<String> words
    ) {
        List<String> matchedWords = new ArrayList<>();
        for (String item : words) {
            if (item.matches(regex)) {
                matchedWords.add(item);
            }
        }
        return matchedWords;
    }

    public static Map<Character, Integer> getFrequencyMap(
            List<String>    matchedWords
    ) {
        Map<Character, Integer> freqMap = new TreeMap<>();
        for (String item : matchedWords) {
            for (int i = 0; i < item.length(); i++) {
                char ch = item.charAt(i);
                if (!freqMap.containsKey(ch)) {
                    freqMap.put(ch, 1);
                } else {
                    freqMap.put(ch, freqMap.get(ch) + 1);
                }
            }
        }
        return freqMap;
    }

    public static char getGuess(
            Map<Character, Integer> freqMap,
            List<Character>         guesses
    ) {
        if (freqMap.isEmpty()) {
            return '?';
        }
        freqMap.put('?', 0);
        char bestGuess = '?';
        for (char item : freqMap.keySet()) {
            if (guesses.contains(item)) {
                continue;
            }
            if (freqMap.get(item) > freqMap.get(bestGuess)) {
                bestGuess = item;
            }
        }
        return bestGuess;
    }
}

package aoa.helpers;


import java.util.*;

public class HelperUtils {

    public static String generateRegex(String pattern, List<Character> guesses) {
        if (guesses.isEmpty()) {
            return pattern.replace('-', '.');
        }
        StringBuilder replStr = new StringBuilder(guesses.toString().replace(", ", ""));
        replStr.insert(1, '^');
        return pattern.replace("-", replStr);
    }

    public static List<String> getMatchedWords(String pattern, List<String> words) {
        List<String> matchedWords = new ArrayList<>();
        for (String item : words) {
            if (item.matches(pattern)) {
                matchedWords.add(item);
            }
        }
        return matchedWords;
    }

    public static Map<Character, Integer> getFrequentMap(String pattern, List<String> words, List<Character> guesses) {
        Map<Character, Integer> freqMap = new TreeMap<>();
        List<String> matchedWords = getMatchedWords(pattern, words);
        for (String item : matchedWords) {
            for (int i = 0; i < item.length(); i++) {
                char ch = item.charAt(i);
                if (guesses.contains(ch)) {
                    continue;
                }
                if (!freqMap.containsKey(ch)) {
                    freqMap.put(ch, 1);
                } else {
                    freqMap.put(ch, freqMap.get(ch) + 1);
                }
            }
        }
        return freqMap;
    }

    public static char getGuess(String pattern, List<String> words, List<Character> guesses) {
        Map<Character, Integer> freqMap = getFrequentMap(pattern, words, guesses);
        if (freqMap.isEmpty()) {
            return '?';
        }
        freqMap.put('?', 0);
        char bestGuess = '?';
        for (char item : freqMap.keySet()) {
            if (freqMap.get(item) > freqMap.get(bestGuess)) {
                bestGuess = item;
            }
        }
        return bestGuess;
    }

}

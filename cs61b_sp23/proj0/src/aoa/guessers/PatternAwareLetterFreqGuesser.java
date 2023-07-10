package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.*;

public class PatternAwareLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PatternAwareLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    protected List<String> getMatchedWords(String pattern) {
        List<String> matchedWords = new ArrayList<>();
        String newPattern = pattern.replace('-', '.');
         for (String item : words) {
             if (Pattern.matches(newPattern, item)) {
                 matchedWords.add(item);
             }
         }
         return matchedWords;
    }

    protected Map<Character, Integer> getFrequentMap(String pattern) {
        List<String> matchedWords = getMatchedWords(pattern);
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

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN. */
    public char getGuess(String pattern, List<Character> guesses) {
        Map<Character, Integer> freqMap = getFrequentMap(pattern);
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

    public static void main(String[] args) {
        PatternAwareLetterFreqGuesser palfg = new PatternAwareLetterFreqGuesser("data/example.txt");
        System.out.println(palfg.getGuess("-e--", List.of('e')));
    }
}
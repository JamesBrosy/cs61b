package aoa.choosers;

import java.util.*;

import aoa.helpers.HelperUtils;
import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;

public class EvilChooser implements Chooser {
    private String pattern;
    private List<String> wordPool;

    public EvilChooser(int wordLength, String dictionaryFile) {
        if (wordLength < 1) {
            throw new IllegalArgumentException("wordLength is less than one");
        }
        wordPool = FileUtils.readWordsOfLength(dictionaryFile, wordLength);
        if (wordPool.isEmpty()) {
            throw new IllegalStateException("No words found of wordsLength");
        }

        char[] tmp = new char[wordLength];
        Arrays.fill(tmp, '-');
        pattern = new String(tmp);
    }

    @Override
    public int makeGuess(char letter) {
        Map<String, List<String>> patternToWordsMap = HelperUtils.generatePatternToWordsMap(wordPool, letter, pattern);
        patternToWordsMap.put("?", List.of());
        pattern = "?";
        for (String item : patternToWordsMap.keySet()) {
            if (patternToWordsMap.get(item).size() > patternToWordsMap.get(pattern).size()) {
                pattern = item;
            }
        }
        wordPool = patternToWordsMap.get(pattern);

        int sum = 0;
        for (int i = 0; i < pattern.length(); i++) {
            if (letter == pattern.charAt(i)) {
                sum++;
            }
        }
        return sum;
    }

    @Override
    public String getPattern() {
        return pattern;
    }

    @Override
    public String getWord() {
        int randomLyChosenWordNumber = StdRandom.uniform(wordPool.size());
        return wordPool.get(randomLyChosenWordNumber);
    }
}

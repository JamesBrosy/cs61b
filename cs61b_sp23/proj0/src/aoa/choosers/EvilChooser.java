package aoa.choosers;

import java.util.*;

import com.github.javaparser.utils.Pair;
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

        pattern = "-".repeat(wordLength);
    }

    @Override
    public int makeGuess(char letter) {
        var patternToPairMap = ChooserHelper.generatePatternToPairMap(wordPool, letter, pattern);
        patternToPairMap.put("?", new Pair<>(new ArrayList<>(), 0));
        pattern = "?";
        for (String item : patternToPairMap.keySet()) {
            if (patternToPairMap.get(item).a.size() > patternToPairMap.get(pattern).a.size()) {
                pattern = item;
            }
        }
        wordPool = patternToPairMap.get(pattern).a;

        return patternToPairMap.get(pattern).b;
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

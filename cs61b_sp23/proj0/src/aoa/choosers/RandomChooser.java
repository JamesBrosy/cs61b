package aoa.choosers;

import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;

import java.util.List;

public class RandomChooser implements Chooser {
    private final String chosenWord;
    private String pattern;

    public RandomChooser(int wordLength, String dictionaryFile) {
        if (wordLength < 1) {
            throw new IllegalArgumentException("wordLength is less than one");
        }
        List<String> words = FileUtils.readWordsOfLength(dictionaryFile, wordLength);
        if (words.isEmpty()) {
            throw new IllegalStateException("No words found of wordsLength");
        }
        int randomLyChosenWordNumber = StdRandom.uniform(words.size());
        chosenWord = words.get(randomLyChosenWordNumber);

        pattern = "-".repeat(wordLength);
    }

    @Override
    public int makeGuess(char letter) {
        var intToPair = ChooserHelper.generateIntToPair(chosenWord, letter, pattern);
        pattern = intToPair.b.a;
        return intToPair.a;
    }

    @Override
    public String getPattern() {
        return pattern;
    }

    @Override
    public String getWord() {
        return chosenWord;
    }
}

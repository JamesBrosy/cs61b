package aoa.choosers;

import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;

import java.util.Arrays;
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
        int numWords = words.size();
        int randomLyChosenWordNumber = StdRandom.uniform(numWords);
        chosenWord = words.get(randomLyChosenWordNumber);

        char[] tmp = new char[wordLength];
        Arrays.fill(tmp, '-');
        pattern = new String(tmp);
    }

    @Override
    public int makeGuess(char letter) {
        StringBuilder tmpPattern = new StringBuilder(pattern);
        int sum = 0;
        for (int i = 0; i < chosenWord.length(); i++) {
            if (letter == chosenWord.charAt(i)) {
                sum++;
                tmpPattern.replace(i, i + 1, Character.toString(letter));
            }
        }
        pattern = tmpPattern.toString();
        return sum;
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

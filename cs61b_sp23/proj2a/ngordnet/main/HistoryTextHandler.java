package ngordnet.main;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;

import java.util.List;

/**
 * Created by Bland on 11/5/23.
 */
public class HistoryTextHandler extends NgordnetQueryHandler {

    private final NGramMap map;

    public HistoryTextHandler(NGramMap map) {
        this.map = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();

        StringBuilder response = new StringBuilder();
        for (String word : words) {
            response.append(word).append(": ").
                    append(map.weightHistory(word, startYear, endYear).toString()).
                    append("\r\n");
        }
        return response.toString();
    }
}

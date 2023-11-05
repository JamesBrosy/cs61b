package ngordnet.main;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;
import ngordnet.ngrams.TimeSeries;
import ngordnet.plotting.Plotter;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bland on 11/5/23.
 */
public class HistoryHandler extends NgordnetQueryHandler {

    private final NGramMap map;

    public HistoryHandler(NGramMap map) {
        this.map = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();

        List<TimeSeries> lts = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        for (String word : words) {
            System.out.print(word + ": "
                    + map.weightHistory(word, startYear, endYear).toString()
                    + "\r\n");
            labels.add(word);
            lts.add(map.weightHistory(word, startYear, endYear));
        }
        XYChart chart = Plotter.generateTimeSeriesChart(labels, lts);
        return Plotter.encodeChartAsString(chart);
    }
}

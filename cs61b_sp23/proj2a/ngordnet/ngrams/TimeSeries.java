package ngordnet.ngrams;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    private static final int MIN_YEAR = 1400;
    private static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        if (startYear < MIN_YEAR || endYear > MAX_YEAR) {
            throw new IllegalArgumentException("startYear or endYear is out of the range");
        }
        for (int year = startYear; year <= endYear; year++) {
            if (ts.containsKey(year)) {
                this.put(year, ts.get(year));
            }
        }
    }

    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        return new ArrayList<>(keySet());
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        List<Double> data = new ArrayList<>();
        List<Integer> years = years();
        for (int year : years) {
            data.add(get(year));
        }
        return data;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        TimeSeries res = new TimeSeries(ts, MIN_YEAR, MAX_YEAR);
        List<Integer> yearList = years();
        for (int year : yearList) {
            if (res.containsKey(year)) {
                res.put(year, res.get(year) + get(year));
                continue;
            }
            res.put(year, get(year));
        }
        return res;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        if (isEmpty()) {
            return new TimeSeries();
        }
        TimeSeries res = new TimeSeries();
        List<Integer> yearList = years();
        for (int year : yearList) {
            if (ts.containsKey(year)) {
                res.put(year, get(year) / ts.get(year));
                continue;
            }
            throw new IllegalArgumentException("ts is missing a year that exists in this TimeSeries");
        }
        return res;
    }
}

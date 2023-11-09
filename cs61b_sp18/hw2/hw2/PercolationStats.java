package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double mean;
    private final double stddev;
    private final int T;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N or T is not greater than 0");
        }
        this.T = T;
        double[] ratio = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int ranRow = StdRandom.uniform(N);
                int ranCol = StdRandom.uniform(N);
                p.open(ranRow, ranCol);
            }
            ratio[i] = ((double) p.numberOfOpenSites()) / (N * N);
        }
        this.mean = StdStats.mean(ratio);
        this.stddev = StdStats.stddev(ratio);
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLow() {
        return mean - 1.96 * stddev / Math.sqrt(T);
    }

    public double confidenceHigh() {
        return mean + 1.96 * stddev / Math.sqrt(T);
    }

    public static void main(String[] args) {
        int trials = 100, gridSize = 50;
        PercolationFactory pf = new PercolationFactory();
        PercolationStats ps = new PercolationStats(gridSize, trials, pf);
        System.out.printf("Grid Size: %d x %d | Number of Trials: %d%n", gridSize, gridSize, trials);
        System.out.printf("The mean percolation threshold is %.2f%n", ps.mean());
        System.out.printf("The standard deviation of the percolation threshold is %.2f.%n", ps.stddev());
        System.out.printf("The 95%% confidence interval is [%.3f, %.3f].%n", ps.confidenceLow(), ps.confidenceHigh());
    }
}

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Arrays;

public class Percolation {
    private final WeightedQuickUnionUF wquUF;
    private final boolean[][] isOpenArr;
    private final int width;
    private int openSize = 0;
    private final int[] openBottomCol;
    private int openBottomSize = 0;
    private boolean isPercolated = false;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N is less than 0");
        }
        width = N;
        isOpenArr = new boolean[N][N];
        openBottomCol = new int[N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(isOpenArr[i], false);
        }
        wquUF = new WeightedQuickUnionUF(N * N);
        for (int i = 0; i < N; i++) {
            wquUF.union(0, i);
        }
    }

    public void open(int row, int col) {
        if (isInvalid(row, col)) {
            throw new IndexOutOfBoundsException("row or column is out of bounds.");
        }
        if (isOpenArr[row][col]) {
            return;
        }
        isOpenArr[row][col] = true;
        percolateAround(row, col);
        if (!isPercolated) {
            if (row == width - 1) {
                openBottomCol[openBottomSize] = col;
                openBottomSize++;
            }
            for (int i = 0; i < openBottomSize; i++) {
                if (wquUF.connected(coordsToInt(width - 1, openBottomCol[i]), 0)) {
                    isPercolated = true;
                }
            }
        }
        openSize++;
    }

    public boolean isOpen(int row, int col) {
        if (isInvalid(row, col)) {
            throw new IllegalArgumentException("row or column is out of bounds.");
        }
        return isOpenArr[row][col];
    }

    public boolean isFull(int row, int col) {
        if (isInvalid(row, col)) {
            throw new IllegalArgumentException("row or column is out of bounds.");
        }
        return isOpen(row, col) && wquUF.connected(0, coordsToInt(row, col));
    }

    public int numberOfOpenSites() {
        return openSize;
    }

    public boolean percolates() {
        return isPercolated;
    }

    private int coordsToInt(int row, int col) {
        return row * width + col;
    }

    private boolean isInvalid(int row, int col) {
        return row < 0 || row >= width || col < 0 || col >= width;
    }

    private void percolateAround(int row, int col) {
        int pos = coordsToInt(row, col);
        if (row > 0 && isOpen(row - 1, col)) {
            wquUF.union(pos, coordsToInt(row - 1, col));
        }
        if (col > 0 && isOpen(row, col - 1)) {
            wquUF.union(pos, coordsToInt(row, col - 1));
        }
        if (col < width - 1 && isOpen(row, col + 1)) {
            wquUF.union(pos, coordsToInt(row, col + 1));
        }
        if (row < width - 1 && isOpen(row + 1, col)) {
            wquUF.union(pos, coordsToInt(row + 1, col));
        }
    }
}

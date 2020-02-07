import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    final private int trials;
    final private double[] openSitesUntilPercolates;
    final public static double CONFIDENCE_95 = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        this.trials = trials;
        this.openSitesUntilPercolates = new double[trials];

        for (int i = 0; i < trials; i++) {

            Percolation p = new Percolation(n);

            while (!p.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);

                p.open(row, col);
            }

            this.openSitesUntilPercolates[i] = (1.0 * p.numberOfOpenSites()) / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.openSitesUntilPercolates);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.openSitesUntilPercolates);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return (this.mean() - CONFIDENCE_95 * this.stddev() / Math.sqrt(this.trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return (this.mean() + CONFIDENCE_95 * this.stddev() / Math.sqrt(this.trials));
    }

    // test client (see below)
    public static void main(String[] args) {
        int gridSize = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats ps = new PercolationStats(gridSize, trials);

        System.out.println("mean\t\t\t= " + ps.mean());
        System.out.println("stddev\t\t\t= " + ps.stddev());
        System.out.println("95% confidence interval\t= " + "[" + ps.confidenceLo() + "," + ps.confidenceHi() + "]");
    }
}

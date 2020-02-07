import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    final private WeightedQuickUnionUF uf;
    final private int gridSize;
    private boolean[][] grid;
    private int openSites;
    public static final int NUMBER_OF_NEIGHBORS = 4;
    public static final int NUMBER_OF_COORDINATES = 2;

    /**
     * Constructor.
     * 
     * @param n size of a grid n * n
     */
    public Percolation(int n) {

        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        this.gridSize = n;
        this.uf = new WeightedQuickUnionUF(n * n + 2); // grid n*n + virtual top + virtual bottom
        this.grid = new boolean[n][n];

        // connecting virtual top to first row sites
        int row = 1;
        for (int col = 1; col <= n; col++) {
            this.uf.union(coordinatesToNumber(row, col, n), 0);
        }

        // connecting virtual bottom to last row sites
        row = n;
        for (int col = 1; col <= n; col++) {
            this.uf.union(coordinatesToNumber(row, col, n), n * n + 1);
        }
    }

    /**
     * Open a site by setting its correspoding position in the grid to 1 and
     * connecting it to its open neighbors.
     * 
     * @param row the row value from 1 to n
     * @param col the column value from 1 to n
     */
    public void open(int row, int col) {

        if (row < 1 || row > this.gridSize || col < 1 || col > this.gridSize) {
            throw new IllegalArgumentException();
        }

        if (!this.isOpen(row, col)) {

            this.grid[row - 1][col - 1] = true;
            this.openSites++;

            int[] neighbors = Percolation.getNeighbors(row, col, this.gridSize);
            int site = Percolation.coordinatesToNumber(row, col, this.gridSize);

            for (int i = 0; i < NUMBER_OF_NEIGHBORS; i++) {

                if (neighbors[i] != -1) {

                    int[] neighbor = Percolation.numberToCoordinates(neighbors[i], this.gridSize);

                    if (this.isOpen(neighbor[0], neighbor[1])) {
                        this.uf.union(neighbors[i], site);
                    }
                }
            }
        }
    }

    /**
     * Checks if a site is open. A site is open when its corresponding position in
     * the grid is set to 1.
     * 
     * @return true if open, false otherwise.
     */
    public boolean isOpen(int row, int col) {

        if (row < 1 || row > this.gridSize || col < 1 || col > this.gridSize) {
            throw new IllegalArgumentException();
        }

        return this.grid[row - 1][col - 1] == true;
    }

    /**
     * Returns true if site is connected to another site in the top.
     * 
     * @param row the row value from 1 to n
     * @param col the column value from 1 to n
     * @return
     */
    @SuppressWarnings("deprecation")
    public boolean isFull(int row, int col) {

        if (row < 1 || row > this.gridSize || col < 1 || col > this.gridSize) {
            throw new IllegalArgumentException();
        }

        // is connected to virtual top and virtual bottom?
        return this.uf.connected(Percolation.coordinatesToNumber(row, col, this.gridSize), 0);
    }

    /**
     * Returns the number of sites open in the grid.
     * 
     * @return number of open sites
     */
    public int numberOfOpenSites() {
        return this.openSites;
    }

    /**
     * Checks if virtual top is connected to virtual bottom, meaning that the grid
     * system percolates.
     * 
     * @return true if system percolates, false otherwise.
     */
    @SuppressWarnings("deprecation")
    public boolean percolates() {
        return this.uf.connected(0, this.gridSize * this.gridSize + 1);
    }

    /**
     * Given a coordinate (ROW,COLUMN) returns the number corresponding to this
     * site. Couting starts at site (1,1) = 1 and is incremented by one moving to
     * left site (1,1) = 2, (1,2) = 3 and so forth.
     * 
     * @param row site's row number
     * @param col site's column number
     * @param n   size of the grid (n * n)
     * @return the ith site number corresponding to the coordinate provided
     *         (ROW,COL)
     */
    private static int coordinatesToNumber(int row, int col, int n) {
        return n * (row - 1) + col;
    }

    /**
     * Given a site number returns the number corresponding coordinate in the format
     * (ROW,COLUMN) to this site. Couting starts at site (1,1) = 1 and is
     * incremented by one moving to left site (1,1) = 2, (1,2) = 3 and so forth.
     * 
     * @param siteNumber site's number
     * @param n          size of the grid (n * n)
     * @return the coordinate (ROW,COL) corresponding to the site number provided
     */
    private static int[] numberToCoordinates(int siteNumber, int n) {
        int[] coordinates = new int[NUMBER_OF_COORDINATES];

        coordinates[0] = (siteNumber % n == 0) ? siteNumber / n : (siteNumber / n + 1); // row
        coordinates[1] = (siteNumber % n == 0) ? n : (siteNumber % n); // column

        return coordinates;
    }

    /**
     * Given a coordinate (ROW,COLUMN) returns the numbers corresponding to the
     * neighbors of this site. A neighbor is on the top (ROW-1), right (COL+1),
     * bottom (ROW+1), and left (COL-1).
     * 
     * @param row site's row number
     * @param col site's column number
     * @param n   size of the grid (n * n)
     * @return an arrays with 4 numbers corresponding to the site's neighbors (TOP,
     *         RIGHT, BOTTOM, LEFT) in this order. If a site doesn't have a top
     *         neighbor, for example, the first position in the array will be -1.
     */
    private static int[] getNeighbors(int row, int col, int n) {
        int[] neighbors = new int[NUMBER_OF_NEIGHBORS];

        neighbors[0] = (row - 1 > 0) ? coordinatesToNumber(row - 1, col, n) : -1; // top
        neighbors[1] = (col + 1 <= n) ? coordinatesToNumber(row, col + 1, n) : -1; // right
        neighbors[2] = (row + 1 <= n) ? coordinatesToNumber(row + 1, col, n) : -1; // bottom
        neighbors[3] = (col - 1 > 0) ? coordinatesToNumber(row, col - 1, n) : -1; // left

        return neighbors;
    }
}

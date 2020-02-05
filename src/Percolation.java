import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
    	
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
    	
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
    	return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
    	return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
    	return 0;
    }

    // does the system percolate?
    public boolean percolates() {
    	return false;
    }
    
    /*
     * Given a coordinate (ROW,COLUMN) returns the number corresponding
     * to this site. Couting starts at site (0,0) = 1 and is incremented
     * by one moving to left site (0,1) = 2, (0,2) = 3 and so forth.
     * @param row site's row number
     * @param col site's column number
     * @param n size of the grid (n * n)
     * @return the ith site number corresponding to the coordinate provided (ROW,COL)
     */
    private static int coordinatesToNumber(int row, int col, int n) {
    	return n*row + (col+1);
    }

    /*
     * Given a site number returns the number corresponding coordinate
     * in the format (ROW,COLUMN) to this site. Couting starts at site
     * (0,0) = 1 and is incremented by one moving to left site (0,1) = 2,
     * (0,2) = 3 and so forth.
     * @param siteNumber site's number
     * @param n size of the grid (n * n)
     * @return the coordinate (ROW,COL) corresponding to the site number
     * provided
     */
    private static int[] numberToCoordinates(int siteNumber, int n) {
        int[] coordinates = new int[2];
        
        coordinates[0] = (siteNumber % n == 0) ? (siteNumber/n) - 1 : (siteNumber / n); // row
        coordinates[1] = (siteNumber % n == 0) ? n-1 : (siteNumber % n) - 1; // column

        return coordinates;
    }
    
    /*
     * Given a coordinate (ROW,COLUMN) returns the numbers corresponding to the
     * neighbors of this site. A neighbor is on the top (ROW-1), right (COL+1),
     * bottom (ROW+1), and left (COL-1).
     * @param row site's row number
     * @param col site's column number
     * @param n size of the grid (n * n)
     * @return an arrays with 4 numbers corresponding to the site's neighbors (TOP,
     * RIGHT, BOTTOM, LEFT) in this order. If a site doesn't have a top neighbor,
     * for example, the first position in the array will be -1. 
     */
    private static int[] getNeighbors(int row, int col, int n) {
    	int[] neighbors = new int[4];
   
    	neighbors[0] = (row - 1 > -1) ? coordinatesToNumber(row-1, col, n) : -1; // top
    	neighbors[1] = (col + 1 < n) ? coordinatesToNumber(row, col+1, n) : -1; // right
    	neighbors[2] = (row + 1 < n) ? coordinatesToNumber(row+1, col, n) : -1; // bottom
    	neighbors[3] = (col - 1 > -1) ? coordinatesToNumber(row, col-1, n) : -1; // left
    	
		return neighbors;	
    }

    // test client (optional)
    public static void main(String[] args) {
    }
}

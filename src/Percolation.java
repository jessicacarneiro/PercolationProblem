import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	private WeightedQuickUnionUF uf;
	private int gridSize;
	
    /*
     * Constructor.
     * @param n size of a grid n * n
     */
    public Percolation(int n) {
    	this.gridSize = n;
    	this.uf = new WeightedQuickUnionUF(n*n + 2); // grid n*n + virtual top + virtual bottom
    	
    	// connecting virtual top to first row sites
    	int row = 1;
    	for (int col = 1; col <= n; col++) {
    		this.uf.union(coordinatesToNumber(row,col,n), 0);
    	}
    	
    	// connecting virtual bottom to last row sites
    	row = n;
    	for (int col = 1; col <= n; col++) {
    		this.uf.union(coordinatesToNumber(row,col,n), n*n + 1);
    	}
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
     * to this site. Couting starts at site (1,1) = 1 and is incremented
     * by one moving to left site (1,1) = 2, (1,2) = 3 and so forth.
     * @param row site's row number
     * @param col site's column number
     * @param n size of the grid (n * n)
     * @return the ith site number corresponding to the coordinate provided (ROW,COL)
     */
    private static int coordinatesToNumber(int row, int col, int n) {
    	return n*(row - 1) + col;
    }

    /*
     * Given a site number returns the number corresponding coordinate
     * in the format (ROW,COLUMN) to this site. Couting starts at site
     * (1,1) = 1 and is incremented by one moving to left site (1,1) = 2,
     * (1,2) = 3 and so forth.
     * @param siteNumber site's number
     * @param n size of the grid (n * n)
     * @return the coordinate (ROW,COL) corresponding to the site number
     * provided
     */
    private static int[] numberToCoordinates(int siteNumber, int n) {
        int[] coordinates = new int[2];
        
        coordinates[0] = (siteNumber % n == 0) ? siteNumber/n : (siteNumber / n + 1); // row
        coordinates[1] = (siteNumber % n == 0) ? n : (siteNumber % n); // column

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
   
    	neighbors[0] = (row - 1 > 0) ? coordinatesToNumber(row-1, col, n) : -1; // top
    	neighbors[1] = (col + 1 <= n) ? coordinatesToNumber(row, col+1, n) : -1; // right
    	neighbors[2] = (row + 1 <= n) ? coordinatesToNumber(row+1, col, n) : -1; // bottom
    	neighbors[3] = (col - 1 > 0) ? coordinatesToNumber(row, col-1, n) : -1; // left
    	
		return neighbors;	
    }

    // test client (optional)
    public static void main(String[] args) {
    }
}

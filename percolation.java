import java.util.List;
import java.util.Collections;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class percolation {
	
	// creates n-by-n grid, with all sites initially blocked
	// blocked: 1; open: 0
	
    public Percolation(int n);
    
    // opens the site (row, col) if it is not open already
    public void open(int row, int col);

    // is the site (row, col) open?
    public boolean isOpen(int row, int col);

    // is the site (row, col) full?
    public boolean isFull(int row, int col);

    // returns the number of open sites
    public int numberOfOpenSites();

    // does the system percolate?
    public boolean percolates();

    // test client (optional)
    public static void main(String[] args);

}

public class PercolationStats {

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials)

    // sample mean of percolation threshold
    public double mean()

    // sample standard deviation of percolation threshold
    public double stddev()

    // low endpoint of 95% confidence interval
    public double confidenceLo()

    // high endpoint of 95% confidence interval
    public double confidenceHi()

   // test client (see below)
   public static void main(String[] args)

}
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	private int n;
	private int trials;
	private double[] means;
	private double mean;
	private double stddev;
	private double cfdlow;
	private double cfdhigh;
	private final static double CFD_CRITICAL_VAL = 1.96;

	// perform independent trials on an n-by-n grid
	public PercolationStats(int n, int trials) {
		if(n<=0 || trials<=0) {
			throw new IllegalArgumentException();
		}
		this.n = n;
		this.trials = trials;
		this.means = new double[trials];
		for(int i=0;i<trials;++i) {
			Percolation trial = new Percolation(n);
			while(!trial.percolates()) {
				int row = StdRandom.uniform(1, n+1);
                int col = StdRandom.uniform(1, n+1);
                trial.open(row, col);
			}
			int count = trial.numberOfOpenSites()/(n*n);
			means[i] = count;
		}
		this.mean = StdStats.mean(means);
		this.stddev = StdStats.stddev(means);
		this.cfdlow = this.mean - CFD_CRITICAL_VAL * (this.stddev / Math.sqrt(trials));
		this.cfdhigh = this.mean + CFD_CRITICAL_VAL * (this.stddev / Math.sqrt(trials));
	}

    // sample mean of percolation threshold
    public double mean() {
    	return this.mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
    	return this.stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
    	return this.cfdlow;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
    	return this.cfdhigh;
    }

   // test client (see below)
   // public static void main(String[] args)

}
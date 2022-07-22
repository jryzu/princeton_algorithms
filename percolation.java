import java.util.List;
import java.util.Collections;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private int n;
	private WeightedQuickUnionUF uf;
	private byte[] openStateRecord;
	private int index;

	// creates n-by-n grid, with all sites initially blocked
	
    public Percolation(int n) {
    	if(n<=0) {
    		throw new IllegalArgumentException();
    	}
    	this.n = n;
    	uf = new WeightedQuickUnionUF(n * (n+2));
    	openStateRecord = new byte[n * (n+2)];
    	// 3 states: 0 = closed, 1 = empty open, 2 = full open
    	for(int i=0; i<n;++i) { // base (invisible) layer; where the water starts (20
    		openStateRecord[i] = 2;
    	}
    	for(int i=n; i<(n*(n+1)); ++i) {
    		openStateRecord[i] = 0;
    	}
    	for(int i=(n*(n+1)); i<(n*(n+2)); ++i) {
    		openStateRecord[i] = 1; 
    	}
    	
    }
    
    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
    	index = (row * n) + col;
    	if (openStateRecord[index]!=0) {
    		return;
    	}
    	openStateRecord[index]=1;
    }
    
    public void percolating() {
    	for(int i=0;i<n*(n+2);++i) {
    		if(openStateRecord[i]==1) {
    	    	if (openStateRecord[index-1]==2 ||
    	    			openStateRecord[index+1]==2 ||
    	    			openStateRecord[index-n]==2 ||
    	    			openStateRecord[index+n]==2) {
    	    		openStateRecord[index]=2;
    	    	}
    		}
    	}
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
    	index=(row*n)+col;
    	return openStateRecord[index]!=0;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
    	index=(row*n)+col;
    	return openStateRecord[index]==2;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
    	int a = 0;
    	for(int i=n;i<n*(n+1);++i) {
    		if(openStateRecord[i]!=0) {
    			++a;
    		}
    	}
    	return a;
    }

    // does the system percolate?
    public boolean percolates() {
    	percolating();
    	return openStateRecord[n*(n+2)]==2;
    }
    
    // test client (optional)
    //public static void main(String[] args);
}

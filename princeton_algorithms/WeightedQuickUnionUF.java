package princeton_algorithms;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickUnionUF {
	
	private int[] parent;
	private int[] size;
	private int count;
	
	//weighted quick union includes size of each tree and attaches smaller tree (assigns index)
	//to larger tree!
	
	public WeightedQuickUnionUF(int n) {
		count = n;
		parent = new int[n];
		size = new int[n];
		for(int i=0;i<n;++i) {
			parent[i] = i;
			size[i] = 1;
		}
	}
	
	public int count() {
		return count;
	}
	
	//verify that p is a valid index
	private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));  
        }
    }
	
	public int find(int p) {
		validate(p);
		while(p!=parent[p]) {
			p = parent[p];
		}
		return parent[p];
	}
	
	public void union(int p, int q) {
		int rootP = find(p);
		int rootQ = find(q);
		if(rootP == rootQ) {
			return;
		}
		if(size[rootP] < size[rootQ]){
			parent[rootP] = rootQ;
			size[rootQ] += size[rootP];
		}
		if(size[rootQ] < size[rootP]){
				parent[rootQ] = rootP;
				size[rootP] += size[rootQ];
		}
		count--;
	}
	
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	
	public static void main(String[] args) {
        int n = StdIn.readInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.find(p) == uf.find(q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }
	
}
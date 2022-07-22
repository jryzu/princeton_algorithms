package princeton_algorithms;

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
		validate(p);
		validate(q);
		int rootP = find(p);
		int rootQ = find(q);
		if(rootP!=rootQ) {
			if(rootP < rootQ){
				parent[p] = q;
				size[q] += size[p];
			}
			if(rootQ < rootP){
				parent[q] = p;
				size[p] += size[q];
			}
		}
	}
	
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	
}
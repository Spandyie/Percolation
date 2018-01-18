
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    //false-closed true -open
    private int counter = 0;
    private boolean[] states;
    private final int side;
    private final int top;
    private final int bottom;
    private final WeightedQuickUnionUF grid;
    
    
    public Percolation(int n) {
        side = n;
        top = side *side;
        bottom = side*side +1;
        states = new boolean[n*n+2];           // the two additional sites are for top and bottom
        grid = new WeightedQuickUnionUF(n*n+2);
        for (int i = 0;i < n*n; i++) {
            states[i] = false;
        }
        states[top] = true;
        states[bottom] = true;
    }

    public void open(int i, int j) {
        checkRange(i, j);
        if (isOpen(i, j)) return; 

        else{

        	int cell = grid2vec(i, j);
        	states[cell] = true;
        	counter++; 

	        if (i > 1 && isOpen(i-1, j)) {
	        	 
	            union(grid2vec(i-1,j), cell); 
	        }
	        else if(i == 1) {
	        	
	            union(cell,top);
	        }

	        if ( i < side && isOpen( i+1, j)) {
	        	
	            union(cell,grid2vec(i+1, j));
	        }        
	        else if (i == side) {
	        	
	            union(cell,bottom);
	        }
	        if (j > 1 && isOpen(i, j-1)) {
	        	
	            union(cell, grid2vec(i, j-1));
	        }
	        if (j < side && isOpen(i, j+1)) {      	
	            union(cell, grid2vec(i, j+1));
	        }
        } 
    }
    
    private void checkRange(int i, int j) {
        if ( i < 1 || j < 1 || i > side || j > side) throw new IndexOutOfBoundsException();
    }
    
    private void union(int p, int q) {
        if (!grid.connected(p,q)) grid.union(p, q);
    }
        
    public boolean isOpen(int i, int j) {  
        checkRange(i,j);
        return states[grid2vec(i,j)] == true;
    }
    public boolean isFull(int i, int j) {
        checkRange(i,j);
        return grid.connected(top,grid2vec(i,j));
    }
    
    
    public boolean percolates() {
        return grid.connected(top,bottom);
    }
    
    private int grid2vec(int i, int j) {
        return ((side * (i-1)) + j-1);
    }
   
    public int numberOfOpenSites() {        
        return counter;
    }
   
}

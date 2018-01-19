import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats{

    private final double CONFIDENCE_95 = 1.96;
    private final double [] array;
        
    public PercolationStats(int n, int t){
        if (n <= 0 || t <= 0) throw new IllegalArgumentException();
        array = new double[t];        
        for(int i = 0;i < t;i++){
            Percolation inp = new Percolation(n);
            while (!inp.percolates()) {
                
                int x = StdRandom.uniform(n)+1;
                int y = StdRandom.uniform(n)+1;
                if(!inp.isOpen(x, y)) {
                    inp.open(x, y); 
                    }
        }
            array[i] = (double) inp.numberOfOpenSites()/ (n*n);
            
        }
    }
    
    public double mean() {
       return StdStats.mean(array);
    }
    
    public double stddev() {
        return StdStats.stddev(array);
    }
    
    public double confidenceLo(){
        double output = mean() - ( CONFIDENCE_95 / Math.sqrt(array.length)) * stddev();
        return output;
    }
    
     public double confidenceHi() {
        double output = mean() + ( CONFIDENCE_95 / Math.sqrt(array.length)) * stddev();
        return output;
    }
     
     
    public static void main(String[] args){

        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        
        PercolationStats inp = new PercolationStats(n,trials);
        StdOut.print("mean =    " + inp.mean() + "\n");
        StdOut.print("stdev = " + inp.stddev() + "\n");
        StdOut.print("95% confidence interval =[" + inp.confidenceLo() + "," + inp.confidenceHi()+"]" );       
    }
    
}


//import stdlib.*;
//import StdRandom;

public class PercolationStats {
  
  int gridSize;
  int results[];
  int noOfTests;
  
   public PercolationStats(int N, int T)    // perform T independent computational experiments on an N-by-N grid
   {
     gridSize = N;
     noOfTests = T;
     results = new int[T];
     
     for(int i = 0; i < T; i++)
       results[i] = execute();
   }
   
   private int execute()
   {
     Percolation obj = new Percolation(gridSize);
     while(!obj.percolates())
     {
       obj.open(StdRandom.uniform(gridSize) + 1, StdRandom.uniform(gridSize) + 1);
     }
//     obj.display();
     return obj.count;
   }
    
   public void display()
   {
     for(int i = 0; i < noOfTests; i++)
       System.out.println(results[i]);
   }
   
   public double mean()                     // sample mean of percolation threshold
   {
     return StdStats.mean(results);
   }
   
   public double stddev()                   // sample sPercolationStatsimport tandard deviation of percolation threshold
   {
     return StdStats.stddev(results);
   }
   
   public double confidenceLo()             // returns lower bound of the 95% confidence interval
   {
     double mean = mean();
     double stddev = stddev();
     
     return mean - (1.96 * stddev / Math.sqrt(noOfTests));
   }   

   public double confidenceHi()             // returns upper bound of the 95% confidence interval
   {
     double mean = mean();
     double stddev = stddev();
     
     return mean + (1.96 * stddev / Math.sqrt(noOfTests));
   }
     
     public static void main(String[] args)   // test client, described below
   {
     //PercolationStats pobj = new PercolationStats( Integer.parseInt(args[0]), Integer.parseInt(args[1]));
     PercolationStats pobj = new PercolationStats(4,40);
         System.out.println("mean                    = " + pobj.mean());
     System.out.println("stddev                  = " + pobj.stddev());
     System.out.println("95% confidence interval = " + pobj.confidenceLo() + ", " + pobj.confidenceHi());
   }
}

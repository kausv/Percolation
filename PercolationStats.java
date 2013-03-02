import java.util.Random;

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
       obj.open(new Random().nextInt(gridSize) + 1, new Random().nextInt(gridSize) + 1);
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
       Integer sum = 0;
       for(int result : results)
           sum += result;
     return sum/noOfTests;
   }
   
   public double stddev()                   // sample sPercolationStatsimport tandard deviation of percolation threshold
   {
       double mean = mean();
       double stddev = 0;
       for(int result : results)
           stddev += Math.pow(result - mean, 2);
     return Math.sqrt(stddev);
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
     PercolationStats pobj = new PercolationStats( Integer.parseInt(args[0]), Integer.parseInt(args[1]));
     //PercolationStats pobj = new PercolationStats(5,3);
         System.out.println("mean                    = " + pobj.mean()/Integer.parseInt(args[0]) / Integer.parseInt(args[0]));
     System.out.println("stddev                  = " + pobj.stddev());
     System.out.println("95% confidence interval = " + pobj.confidenceLo() + ", " + pobj.confidenceHi());
   }
}

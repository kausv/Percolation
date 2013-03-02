class Percolation
{
  private int id[][];
  private int sz[][];
  private boolean op[][];
  
  private int size;
  public int count;
  
  public Percolation(int N)              // create N-by-N grid, with all sites blocked
  {
    
    size = N;
    id = new int[N + 2][N];
    sz = new int[N + 2][N];
    op = new boolean[N + 2][N];
    
    for(int i = 0; i < N + 2; i++)
    {
      for(int j = 0; j < N; j++)
      {
        id[i][j] = i * size + j;
        sz[i][j] = 1;
        op[i][j] = false;
      }
    }
    
    for(int i = 1; i <= N; i++)
    {
      open(0,i);
      open(N+1,i);
    }
    count = 0;
  }
  
  public void display()
  {
    System.out.println();
    for(int i = 0; i < size + 2; i++)
    {
      System.out.println("");
      for(int j = 0; j < size; j++)
      {
        System.out.print(id[i][j] + " ");
      }
      
      System.out.print("     ");
      
      for(int j = 0; j < size; j++)
      {
        System.out.print(op[i][j]?"1 ":"0 ");
      }
      
      System.out.print("     ");
      
      for(int j = 0; j < size; j++)
      {
        System.out.print(sz[i][j] + " ");
      }
    }
    System.out.println(percolates());
  }

  private int find(int i, int j)
  {
    while(!(id[i][j] == i * size + j))
    {
      i = id[i][j] / size;
      j = id[i][j] % size;
    }
    return id[i][j];
  }
  
  private void union(int i1, int j1, int i2, int j2)
  {
    int p = find(i1, j1);
    int x1 = p / size;
    int y1 = p % size;
    
    int q = find(i2, j2);
    int x2 = q / size;
    int y2 = q % size;
    
    if(p == q)
      return;
    
    if( sz[x1][y1] > sz[x2][y2])
    {
      id[x2][y2] = id[x1][y1];
      sz[x1][y1] += sz[x2][y2];
    }
    else
    {
      id[x1][y1] = id[x2][y2];
      sz[x2][y2] += sz[x1][y1];
    }
  }
  
  private boolean connected(int i1, int j1, int i2, int j2)
  {
    return find(i1,j1) == find(i2,j2);
  }
  
  public void open(int i, int j)         // open site (row i, column j) if it is not already
  {
    j--;
    if(op[i][j]) return;
    op[i][j] = true;
    count++;
    
    if(i != 0)
      if( isOpen( i - 1, j + 1) )
        union( i, j, i - 1, j);
    
    if(j != 0)
       if( isOpen( i, j-1 + 1))
         union( i, j, i, j - 1 );
    
    if(i + 1 != size + 2)
      if( isOpen( i + 1, j + 1) )
        union( i, j, i + 1, j);
    
    if(j + 1 != size)
       if( isOpen( i, j + 1 + 1))
         union( i, j, i, j + 1 );     
  }
  
  public boolean isOpen(int i, int j)    // is site (row i, column j) open?
  {
    return op[i][j - 1];
  }
  
   public boolean isFull(int i, int j)    // is site (row i, column j) full?
   {
     return find(i,j - 1) == find(0,0);
   }

   public boolean percolates()            // does the system percolate?
   {
     return find(0,0) == find(size + 1,0);
   }  
  public static void main(String args[])
  {
    Percolation obj=new Percolation(4);
    obj.display();
    obj.open(3,3);
    obj.display();
    obj.open(1,3);
    obj.display();
    obj.open(2,2);
    obj.display();
    obj.open(4,2);
    obj.display();
    obj.open(1,2);
    obj.display();
    obj.open(4,3);
    obj.display();
    obj.open(2,3);
    obj.display();
    obj.open(4,2);
    obj.display();
    
  }
}
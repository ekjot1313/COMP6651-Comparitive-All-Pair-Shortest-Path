public class Johnson implements AllPairShortestPath{
	static final int INFINITY = Integer.MAX_VALUE;
    @Override
    public int[][] getShortestDistAdjMatix(int[][] distAdjMatrix) {
    	int vertices = distAdjMatrix.length;
    	int source;
    	int[] distance= new int[vertices+1];
        int[][] dist = new int[vertices+1][vertices+1];
        for (int i = 0; i < vertices; i++)
            for (int j = 0; j < vertices; j++)
            {	if(i!=j && distAdjMatrix[i][j]==0)
            		dist[i][j]=INFINITY;
            	else
            		dist[i][j] = distAdjMatrix[i][j];
            }
        for(int k=0;k<=vertices;k++)
        {	dist[vertices][k]=0;
        	dist[vertices][k]=INFINITY;
        }
        source=vertices;
        for(int i=0;i<=source;i++)
			distance[i]=0;
        boolean b=BellmanFord(dist,vertices,distance);
        //System.out.println(b);
        if (b== true)
        {
        	for(int m=0;m<vertices;m++)
    			for(int n=0;n<vertices;n++)
    			{	if(m!=n && dist[m][n]!=INFINITY)
    						dist[m][n]=	dist[m][n] + distance[m] - distance[n];	
    			    else
    			    	dist[m][n]=0;
    			}
             int[][] SDAM=new int[vertices][vertices];
             for(int i=0;i<vertices;i++){
                 SDAM[i]=Dijkstra.getShortestDistArray(dist,i,1);
                 for(int j=0;j<vertices;j++) {
                	if(distAdjMatrix[i][j]!=0) {
                	 int n= SDAM[i][j]- distance[i] + distance[j];
                 	 SDAM[i][j]=n;
                	}  
                 }
             }
             return SDAM;
        }
        else
        	System.out.println("Johnson's cannot handle negative weight cycle.\n");
        	return null;
    }
	private boolean BellmanFord(int[][] dist, int source,int[] distance) {
		// TODO Auto-generated method stub
		for(int i=0;i<=source;i++)
			for(int m=0;m<=source;m++)
				for(int n=0;n<=source;n++)
				{	if(m!=n&& dist[m][n]!=INFINITY)
						if((distance[m]+dist[m][n]) < distance[n])
							distance[n]=distance[m]+dist[m][n];					
				}
		for(int i=0;i<=source;i++)
		//System.out.println(distance[i]);
		for(int m=0;m<source;m++)
			for(int n=0;n<source;n++)
			{	if(m!=n && dist[m][n]!=INFINITY)
				{	//System.out.println(m+"   "+n+"\n" + distance[m]+"  "+dist[m][n]+"  "+distance[n]);
					if(distance[n] > (distance[m]+dist[m][n]))
						return false;				
				}
			}
		return true;
	}
}

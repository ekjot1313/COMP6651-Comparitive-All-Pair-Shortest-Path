public class FloydWarshall implements AllPairShortestPath {
	 //static final int INFINITY = Integer.MAX_VALUE;
    @Override
    public int[][] getShortestDistAdjMatix(int[][] distAdjMatrix) {
        int vertices = distAdjMatrix.length;
        int[][] dist = new int[vertices][vertices];

        for (int i = 0; i < vertices; i++)
            for (int j = 0; j < vertices; j++)
                dist[i][j] = distAdjMatrix[i][j];

        for (int k = 0; k < vertices; k++)
            for (int i = 0; i < vertices; i++)
                for (int j = 0; j < vertices; j++)
                	if( dist[i][k]!=INFINITY && dist[k][j]!=INFINITY )
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
        return dist;
    }
}

public class AllPairDijkstra implements AllPairShortestPath{
    @Override
    
    public int[][] getShortestDistAdjMatix(int[][] distAdjMatrix) {
        int vertices =distAdjMatrix.length;
        int[][] SDAM=new int[vertices][vertices];
        for(int i=0;i<vertices;i++){
            SDAM[i]=Dijkstra.getShortestDistArray(distAdjMatrix,i,0);
        }
        return SDAM;
    }
}

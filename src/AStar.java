import java.util.ArrayList;

public class AStar{
    static final int INFINITY = Integer.MAX_VALUE;

    static class Vertex implements Comparable{
        public Vertex prev;
        public double g,h;

        Vertex(Vertex prev, double g, double h){
            this.prev = prev;
            this.g = g;
            this.h = h;
        }

        @Override
        public int compareTo(Object obj){
            Vertex secondObj = (Vertex) obj;
            return (int)((this.g + this.h) - (secondObj.g + secondObj.h));
        }
    }

    static int[] getShortestDistArray(int[][] distAdjMatrix, int sourceVertex){
        int vertices = distAdjMatrix.length;
        int[] dist = new int[vertices];

        ArrayList<Integer> openList = new ArrayList(); //initialize list of open nodes
        ArrayList<Integer> closedList = new ArrayList(); //initialize list of closed nodes

        openList.add(sourceVertex); //add source vertex to the open list

        dist[sourceVertex] = 0;
        //iterate through every node other than source node
        for (int i = 0; i < vertices; i++){
            if (sourceVertex != i){ //check if it's not the source vertex
                openList.clear();
                closedList.clear();
                openList.add(sourceVertex);

                //repeat while destination vertex has not yet been reached
                while( !(openList.isEmpty()) ){
                    for (int j =0; j < vertices; j++){
                        if (i != j){
                            
                        }
                    }
                }
            }
        }

        return dist;
    }
}

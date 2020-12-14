import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    static final String pathToDataset = "dist.txt";
    static int vertices = 0;
    static final int INFINITY = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        System.gc();
        int[][] distAdjMatrix = getDistAdjMatrixFromDataset();

//        System.out.println("Original Adjacency Matrix:");
//        printMatrix(distAdjMatrix);

//        System.out.println("\nNumber of vertices: "+distAdjMatrix[0].length);

        AllPairShortestPath dijkstra = new AllPairDijkstra();
        AllPairShortestPath floydWarshall = new FloydWarshall();
        AllPairShortestPath johnson = new Johnson();

        long startFWTime = System.nanoTime();
        int[][] fw_SDAM = floydWarshall.getShortestDistAdjMatix(distAdjMatrix);
        long endFWTime = System.nanoTime();
        long totalFWTime = endFWTime - startFWTime; //calculate runtime of Floyd-Warshall algorithm

        long startJTime = System.nanoTime();
        int[][] j_SDAM = johnson.getShortestDistAdjMatix(distAdjMatrix);
        long endJTime = System.nanoTime();
        long totalJTime = endJTime - startJTime; //calculate runtime of Johnson's algorithm

        long startDTime = System.nanoTime();
        int[][] d_SDAM = dijkstra.getShortestDistAdjMatix(distAdjMatrix);
        long endDTime = System.nanoTime();
        long totalDTime = endDTime - startDTime; //calculate runtime of Dijkstra's algorithm

//        System.out.println("\nDijkstra:");
//        printMatrix(d_SDAM);
        System.out.println("Runtime of Dijkstra: "+totalDTime+" nanoseconds");

//        System.out.println("\nFloyd-Warshall:");
//       printMatrix(fw_SDAM);
        System.out.println("Runtime of Floyd-Warshall: "+totalFWTime+" nanoseconds");

//        System.out.println("\nJohnson:");
//        printMatrix(j_SDAM);
        System.out.println("Runtime of Johnson: "+totalJTime+" nanoseconds");
    }

    private static void printMatrix(int[][] matrix) {
        int v = matrix.length;
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
            	if( matrix[i][j]!= INFINITY)
            		System.out.print(matrix[i][j] + " ");
            	else
            		System.out.print("U ");
            }
            System.out.println();
        }
    }
    private static int[][] getDistAdjMatrixFromDataset() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(pathToDataset));
        String line = br.readLine().trim();
        if (!line.equals("")) {
            int[] x = Arrays.stream(line.replaceAll(" +"," ").split(" ")).mapToInt(Integer::parseInt).toArray();
            vertices = x.length;

            int[][] distAdjMatrix = new int[vertices][vertices];
            distAdjMatrix[0] = x;
            for (int i = 1; i < vertices; i++) {
                line = br.readLine().trim();
                if (!line.equals("")) {
                    distAdjMatrix[i] = Arrays.stream(line.replaceAll(" +"," ").split(" ")).mapToInt(Integer::parseInt).toArray();
                }
            }
            for (int m = 0; m < vertices; m++) {
            	 for (int n = 0; n < vertices; n++) 
            		 if(m!=n && distAdjMatrix[m][n]==0)
            			 distAdjMatrix[m][n]=INFINITY;
            }
            return distAdjMatrix;
        }
        return new int[0][];
    }
}

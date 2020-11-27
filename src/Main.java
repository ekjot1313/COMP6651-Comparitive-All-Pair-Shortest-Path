import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    static final String pathToDataset = "dist.txt";
    static int vertices = 0;

    public static void main(String[] args) throws IOException {
        int[][] distAdjMatrix = getDistAdjMatrixFromDataset();

        System.out.println("Original Adjacency Matrix:");
        printMatrix(distAdjMatrix);

        AllPairShortestPath dijkstra = new AllPairDijkstra();
        AllPairShortestPath floydWarshall = new FloydWarshall();
        AllPairShortestPath johnson = new Johnson();

        int[][] d_SDAM = dijkstra.getShortestDistAdjMatix(distAdjMatrix);
        int[][] fw_SDAM = floydWarshall.getShortestDistAdjMatix(distAdjMatrix);
        int[][] j_SDAM = johnson.getShortestDistAdjMatix(distAdjMatrix);

        System.out.println("\nDijkstra:");
        printMatrix(d_SDAM);

        System.out.println("\nFloyd-Warshall:");
        printMatrix(fw_SDAM);
    }

    private static void printMatrix(int[][] matrix) {
        int v = matrix.length;
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                System.out.print(matrix[i][j] + " ");
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
            return distAdjMatrix;
        }

        return new int[0][];
    }
}

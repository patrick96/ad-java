
package patrick96.ad_java.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DirectedGraph implements Graph {

    /**
     * Number of vertices in this network
     * Vertices are labeled from 0 to n-1
     */
    protected final int n;

    protected int numEdges = 0;

    protected List<List<Integer>> adjacencyList;
    protected boolean[][] adjacencyMatrix;

    public DirectedGraph(int n) {
        this.n = n;

        this.init();
    }

    protected void init() {
        this.adjacencyList = new ArrayList<List<Integer>>(n);

        for (int i = 0; i < n; i++) {
            this.adjacencyList.add(new ArrayList<Integer>());
        }

        this.adjacencyMatrix = new boolean[n][n];
    }

    @Override
    public int countEdges() {
        return numEdges;
    }

    @Override
    public int countVertices() {
        return n;
    }

    @Override
    public boolean connect(int u, int v) {
        if(u < 0 || u >= n || v < 0 || v >= n) {
            return false;
        }

        if(!adjacencyMatrix[u][v]) {
            numEdges++;
            adjacencyMatrix[u][v] = true;
            adjacencyList.get(u).add(v);
        }
        
        return true;
    }

    @Override
    public List<Integer> getNeighbours(int v) {
        if(v < 0 || v >= n) {
            return null;
        }

        return Collections.unmodifiableList(adjacencyList.get(v));
    }
}

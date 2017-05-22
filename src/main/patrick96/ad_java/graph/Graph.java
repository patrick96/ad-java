package patrick96.ad_java.graph;

import java.util.List;

interface Graph {

    public int countEdges();
    public int countVertices();

    /**
     * Adds an edge from u to v to the graph
     *
     * @param u
     * @param v
     * @return false - If one of the two nodes is not in the graph
     *         true  - Otherwise
     */
    public boolean connect(int u, int v);

    /**
     * Gets the list of neighbours of the given vertex
     * In other words the number of edges originating from v
     *
     * @param v
     * @return null - If vertex is not in graph
     *         list with neighbours - Otherwise
     */
    public List<Integer> getNeighbours(int v);
}

package patrick96.ad_java.graph;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DirectedGraphTest {

    DirectedGraph graph;
    int n = 10;

    @Before
    public void setUp() {
        graph = new DirectedGraph(n);
    }

    @After
    public void tearDown() {}

    @Test
    public void initTest() throws Exception {
        assertNumEdges(graph);
    }

    @Test
    public void connectTest_invalidInputs() throws Exception {
        assertFalse(graph.connect(-1, 1));
        assertFalse(graph.connect(1, -1));
        assertFalse(graph.connect(n, -1));
        assertFalse(graph.connect(n, 1));
        assertFalse(graph.connect(1, n));
    }

    @Test
    public void connectTest_AddEdgeTwice_FindOnlyOnce() throws Exception {
        assertTrue(graph.connect(0,1));
        assertTrue(graph.connect(0,1));
        assertEquals(1, graph.adjacencyList.get(0).size());
        assertNumEdges(graph);
    }

    @Test
    public void connectTest() throws Exception {
        assertTrue(graph.connect(0,1));
        assertEquals(1, graph.numEdges);
        assertTrue(graph.adjacencyMatrix[0][1]);
        assertNumEdges(graph);
    }

    public static void assertNumEdges(DirectedGraph graph) {
        int adjacencyMatrixCount = 0;
        int adjacencyListCount = 0;

        for (int i = 0; i < graph.n; i++) {

            adjacencyListCount += graph.adjacencyList.get(i).size();

            for (int j = 0; j < graph.n; j++) {
                if(graph.adjacencyMatrix[i][j]) {
                    adjacencyMatrixCount++;
                }
            }
        }

        assertEquals(graph.numEdges, adjacencyMatrixCount);
        assertEquals(graph.numEdges, adjacencyListCount);
    }
    
}

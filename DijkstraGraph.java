// --== CS400 File Header Information ==--
// Name: Will Fitzgerald
// Email: wbfitzgerald@wisc.edu
// Group and Team: AI Blue
// Group TA: AI
// Lecturer: Gary Dahl
// Notes to Grader: N/A

import java.util.PriorityQueue;
import java.util.Hashtable;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This class extends the BaseGraph data structure with additional methods for
 * computing the total cost and list of node data along the shortest path
 * connecting a provided starting to ending nodes. This class makes use of
 * Dijkstra's shortest path algorithm.
 */
public class DijkstraGraph<NodeType, EdgeType extends Number>
        extends BaseGraph<NodeType, EdgeType>
        implements GraphADT<NodeType, EdgeType> {

    /**
     * While searching for the shortest path between two nodes, a SearchNode
     * contains data about one specific path between the start node and another
     * node in the graph. The final node in this path is stored in it's node
     * field. The total cost of this path is stored in its cost field. And the
     * predecessor SearchNode within this path is referened by the predecessor
     * field (this field is null within the SearchNode containing the starting
     * node in it's node field).
     *
     * SearchNodes are Comparable and are sorted by cost so that the lowest cost
     * SearchNode has the highest priority within a java.util.PriorityQueue.
     */
    protected class SearchNode implements Comparable<SearchNode> {
        public Node node;
        public double cost;
        public SearchNode predecessor;

        public SearchNode(Node node, double cost, SearchNode predecessor) {
            this.node = node;
            this.cost = cost;
            this.predecessor = predecessor;
        }

        public int compareTo(SearchNode other) {
            if (cost > other.cost)
                return +1;
            if (cost < other.cost)
                return -1;
            return 0;
        }
    }

    /**
     * This helper method creates a network of SearchNodes while computing the
     * shortest path between the provided start and end locations. The
     * SearchNode that is returned by this method is represents the end of the
     * shortest path that is found: it's cost is the cost of that shortest path,
     * and the nodes linked together through predecessor references represent
     * all of the nodes along that shortest path (ordered from end to start).
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return SearchNode for the final end node within the shortest path
     * @throws NoSuchElementException when no path from start to end is found
     *                                or when either start or end data do not
     *                                correspond to a graph node
     */
    protected SearchNode computeShortestPath(NodeType start, NodeType end) throws NoSuchElementException {
        // Check that graph has both nodes
        if (!this.containsNode(start) || !this.containsNode(end)) {
            throw new NoSuchElementException("Error, start or end not found.");
        }

        // Create Data Structures
        PriorityQueue<SearchNode> queue = new PriorityQueue<>();
        Hashtable<NodeType, Double> map = new Hashtable<>();

        // Create the starting node and add to list
        SearchNode startNode = new SearchNode(this.nodes.get(start), 0, null);
        queue.add(startNode);
        map.put(start, 0.0);

        while (!queue.isEmpty()) {
            SearchNode currNode = queue.poll();

            // We have reached the destination node
            if (currNode.node.data.equals(end)) {
                return currNode;

            }
            // Span each edge leaving current node
            for (Edge edge : currNode.node.edgesLeaving) {
                double cost = edge.data.doubleValue() + currNode.cost;
                Node adjNode = edge.successor;

                // Check if map doesn't contain node or if cost is less than cost in map
                if (!map.containsKey(adjNode.data) || cost < map.get(adjNode.data)) {
                    queue.add(new SearchNode(adjNode, cost, currNode));
                    map.put(adjNode.data, cost);
                }
            }

        }
        // Queue is empty, no path found
        throw new NoSuchElementException("Error, no path found from start to end");
    }

    /**
     * Returns the list of data values from nodes along the shortest path
     * from the node with the provided start value through the node with the
     * provided end value. This list of data values starts with the start
     * value, ends with the end value, and contains intermediary values in the
     * order they are encountered while traversing this shorteset path. This
     * method uses Dijkstra's shortest path algorithm to find this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return list of data item from node along this shortest path
     */
    public List<NodeType> shortestPathData(NodeType start, NodeType end) {
        List<NodeType> list = new LinkedList<>();
        SearchNode finalNode = computeShortestPath(start, end);

        // Recurse through path for data
        return pathDataHelper(finalNode, list);
    }

    /**
     * A recursive helper class that handles getting the data of the shortest path
     * for our given graph
     * 
     * @param node     The current node along the path
     * @param pathList The list of data from the path
     * @return The final list of data from the path
     */
    private List<NodeType> pathDataHelper(SearchNode node, List<NodeType> pathList) {
        // Base case
        if (node == null) {
            return pathList;
        }

        // Recursive case, recurse back through tree to get to start node
        pathDataHelper(node.predecessor, pathList);

        // Add the node data
        pathList.add(node.node.data);

        return pathList;
    }

    /**
     * Returns the cost of the path (sum over edge weights) of the shortest
     * path from the node containing the start data to the node containing the
     * end data. This method uses Dijkstra's shortest path algorithm to find
     * this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return the cost of the shortest path between these nodes
     */
    public double shortestPathCost(NodeType start, NodeType end) {
        SearchNode finalNode;
        // Get the search node for this best path
        finalNode = computeShortestPath(start, end);

        // Return the total cost of the best path
        return finalNode.cost;
    }
    
    /**
     * Tests the example that we traced in lecture
     */
    
    /*
    @Test
    public void test1() {

        // Create graph from lecture
        DijkstraGraph<Character, Integer> graph = new DijkstraGraph<>();
        graph.insertNode('A');
        graph.insertNode('B');
        graph.insertNode('C');
        graph.insertNode('D');
        graph.insertNode('E');
        graph.insertNode('F');
        graph.insertNode('G');
        graph.insertNode('H');
        graph.insertNode('I');
        graph.insertNode('J');
        graph.insertNode('K');
        graph.insertNode('L');
        graph.insertNode('M');

        graph.insertEdge('A', 'B', 1);
        graph.insertEdge('A', 'H', 8);
        graph.insertEdge('A', 'M', 5);
        graph.insertEdge('B', 'M', 3);
        graph.insertEdge('D', 'A', 7);
        graph.insertEdge('D', 'G', 2);
        graph.insertEdge('F', 'G', 9);
        graph.insertEdge('G', 'L', 7);
        graph.insertEdge('H', 'B', 6);
        graph.insertEdge('H', 'I', 2);
        graph.insertEdge('I', 'D', 1);
        graph.insertEdge('I', 'L', 5);
        graph.insertEdge('I', 'H', 2);
        graph.insertEdge('M', 'E', 3);
        graph.insertEdge('M', 'F', 4);

        // Create expected list of the shortest path between D -> I
        List<Character> list = new LinkedList<>();
        list.add('D');
        list.add('A');
        list.add('H');
        list.add('I');

        // Test the D -> I Trace
        assertEquals('I', graph.computeShortestPath('D', 'I').node.data);
        assertEquals(list, graph.shortestPathData('D', 'I'));
        assertEquals(17, graph.shortestPathCost('D', 'I'));

    }
    */
    /**
     * Tests the lecture example's cost and sequence of data between a different
     * start and end node
     */

    /*
    @Test
    public void test2() {

        // Create graph from lecture
        DijkstraGraph<Character, Integer> graph = new DijkstraGraph<>();
        graph.insertNode('A');
        graph.insertNode('B');
        graph.insertNode('C');
        graph.insertNode('D');
        graph.insertNode('E');
        graph.insertNode('F');
        graph.insertNode('G');
        graph.insertNode('H');
        graph.insertNode('I');
        graph.insertNode('J');
        graph.insertNode('K');
        graph.insertNode('L');
        graph.insertNode('M');

        graph.insertEdge('A', 'B', 1);
        graph.insertEdge('A', 'H', 8);
        graph.insertEdge('A', 'M', 5);
        graph.insertEdge('B', 'M', 3);
        graph.insertEdge('D', 'A', 7);
        graph.insertEdge('D', 'G', 2);
        graph.insertEdge('F', 'G', 9);
        graph.insertEdge('G', 'L', 7);
        graph.insertEdge('H', 'B', 6);
        graph.insertEdge('H', 'I', 2);
        graph.insertEdge('I', 'D', 1);
        graph.insertEdge('I', 'L', 5);
        graph.insertEdge('I', 'H', 2);
        graph.insertEdge('M', 'E', 3);
        graph.insertEdge('M', 'F', 4);

        // Create expected list of the shortest path between A -> G
        List<Character> list = new LinkedList<>();
        list.add('A');
        list.add('H');
        list.add('I');
        list.add('D');
        list.add('G');

        // Test the D -> I Trace
        assertEquals('G', graph.computeShortestPath('A', 'G').node.data);
        assertEquals(list, graph.shortestPathData('A', 'G'));
        assertEquals(13, graph.shortestPathCost('A', 'G'));

    }
    */
    /**
     * Tests the behavior of this implementation when node that
     * is being searched for a path between does exist, but there is no sequence
     * of directed edges that connects them from the start to the end.
     */
    
    /*
    @Test
    public void test3() {

        // Create graph from lecture
        DijkstraGraph<Character, Integer> graph = new DijkstraGraph<>();
        graph.insertNode('A');
        graph.insertNode('B');
        graph.insertNode('C');
        graph.insertNode('D');
        graph.insertNode('E');
        graph.insertNode('F');
        graph.insertNode('G');
        graph.insertNode('H');
        graph.insertNode('I');
        graph.insertNode('J');
        graph.insertNode('K');
        graph.insertNode('L');
        graph.insertNode('M');

        graph.insertEdge('A', 'B', 1);
        graph.insertEdge('A', 'H', 8);
        graph.insertEdge('A', 'M', 5);
        graph.insertEdge('B', 'M', 3);
        graph.insertEdge('D', 'A', 7);
        graph.insertEdge('D', 'G', 2);
        graph.insertEdge('F', 'G', 9);
        graph.insertEdge('G', 'L', 7);
        graph.insertEdge('H', 'B', 6);
        graph.insertEdge('H', 'I', 2);
        graph.insertEdge('I', 'D', 1);
        graph.insertEdge('I', 'L', 5);
        graph.insertEdge('I', 'H', 2);
        graph.insertEdge('M', 'E', 3);
        graph.insertEdge('M', 'F', 4);

        // Test B -> H trace, there is no valid path to these two nodes

        // Method should throw a NoSuchElementException
        assertThrows(NoSuchElementException.class, () -> {
            graph.computeShortestPath('B', 'H');
        });

        // Method should throw a NoSuchElementException
        assertThrows(NoSuchElementException.class, () -> {
            graph.shortestPathData('B', 'H');
        });

        // Method should throw a NoSuchElementException
        assertThrows(NoSuchElementException.class, () -> {
            graph.shortestPathCost('B', 'H');
        });

    }
    */
}

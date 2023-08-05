import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * This class extends the DijkstraGraph class and will find the shortest round
 * trip path (cycle) between two nodes, the shortest path between two nodes with
 * required node to be visited on the way, and getting all the nodes on graph
 * 
 * @author Will Fitzgerald
 */
public class DijkstraShortestPathAE<NodeType, EdgeType extends Number>
        extends DijkstraGraph<NodeType, EdgeType> implements DijkstraShortestPathInterface<NodeType, EdgeType> {

    /**
     * Uses DijkstraGraph class to get a shortest path from a starting node to an
     * ending node that has a constraint of having to visit a certain other node on
     * the way in the path
     * 
     * @params nodeList: List of size 3 that has the starting node, the node to be
     *         visited on the way, and the ending node in that order
     * @return The list of nodes in order of traversal of the shortest path that
     *         goes through the specified nodes
     */
    @Override
    public List<NodeType> getPathWithNodeVisit(List<NodeType> nodeList) {
        // Check that list is correct format for method call
        if (nodeList.size() != 3) {
            return null;
        }

        try {
            // Call Dijkstra Algorithm for Starting Node -> Desired Node Visit
            List<NodeType> list = shortestPathData(nodeList.get(0), nodeList.get(1));
            // Call Dijkstra Algorithm for Desired Node Visit -> Ending Node
            List<NodeType> list2 = shortestPathData(nodeList.get(1), nodeList.get(2));

            // Remove duplicate of specific node visited
            list2.remove(0);

            // Add all paths for shortest total path
            list.addAll(list2);

            return list;
        } catch (NoSuchElementException e) {
            // Path not found
            return null;
        }
    }

    /**
     * Uses DijkstraGraph class to get a total round trip shortest path (cycle) from
     * a given starting and ending node
     * 
     * @params nodeList: List of size 2 that has the starting node and ending node
     *         in that order
     * @return The list of nodes in order of traversal of the shortest round trip
     *         path
     */
    @Override
    public List<NodeType> getRoundTripPath(List<NodeType> nodeList) {
        // Check that list is correct format for method call
        if (nodeList.size() != 2) {
            return null;
        }

        try {
            // Call Dijkstra Algorithm for Starting Node -> Ending Node
            List<NodeType> list = shortestPathData(nodeList.get(0), nodeList.get(1));
            // Call Dijkstra Algorithm for Ending Node -> Starting Node
            List<NodeType> list2 = shortestPathData(nodeList.get(1), nodeList.get(0));

            // Remove duplicate of specific node visited
            list2.remove(0);

            // Add path from start to finish and finish to start for total round trip path
            list.addAll(list2);

            return list;
        } catch (NoSuchElementException e) {
            // Path not found
            return null;
        }
    }

    /**
     * Gets the graph's total set of Nodes and returns all nodes in a list
     * 
     * @return The list of all the nodes in the graph
     */
    @Override
    public List<NodeType> getAllNodes() {
        // Get Each Node from the graph's node hashtable
        Set<NodeType> keys = nodes.keySet();

        // Create and return a list with all the nodes from the graph
        List<NodeType> list = new ArrayList<NodeType>();
        for (NodeType key : keys) {
            list.add(key);
        }

        return list;
    }

}

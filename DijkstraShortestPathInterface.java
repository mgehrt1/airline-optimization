import java.util.List;

public interface DijkstraShortestPathInterface<NodeType, EdgeType extends Number> extends GraphADT<NodeType, EdgeType>{
    public List<NodeType> getPathWithNodeVisit(List<NodeType> nodeList);

    public List<NodeType> getRoundTripPath(List<NodeType> nodeList);

    public List<NodeType> getAllNodes();
}

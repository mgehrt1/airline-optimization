import java.util.List;

public class DijkstraShortestPathFD<NodeType, EdgeType extends Number> implements DijkstraShortestPathInterface{

	@Override
	public boolean insertNode(Object data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeNode(Object data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsNode(Object data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getNodeCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean insertEdge(Object pred, Object succ, Number weight) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeEdge(Object pred, Object succ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsEdge(Object pred, Object succ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Number getEdge(Object pred, Object succ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getEdgeCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List shortestPathData(Object start, Object end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double shortestPathCost(Object start, Object end) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List getPathWithNodeVisit(List nodeList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getRoundTripPath(List nodeList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getAllNodes() {
		// TODO Auto-generated method stub
		return null;
	}

}

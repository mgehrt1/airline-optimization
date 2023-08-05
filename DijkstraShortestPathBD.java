// --== CS400 File Header Information ==--
// Name: Sampreeth Immidisetty
// Email: immidisetty@wisc.edu
// Group and Team: AI Blue
// Group TA: Rachit Tibdewal
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.ArrayList;
import java.util.List;

public class DijkstraShortestPathBD implements DijkstraShortestPathInterface {

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
    
    return 5;
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

    ArrayList<AirportInterface> testList = new ArrayList<AirportInterface>();

    AirportInterface airport1 = new AirportBD("airport 1");
    testList.add(airport1);

    AirportInterface airport2 = new AirportBD("airport 2");
    testList.add(airport2);

    AirportInterface airport3 = new AirportBD("airport 3");
    testList.add(airport3);

    return testList;
  }

  @Override
  public double shortestPathCost(Object start, Object end) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public List getPathWithNodeVisit(List nodeList) {
    
    ArrayList<AirportInterface> testList = new ArrayList<AirportInterface>();

    AirportInterface airport1 = new AirportBD("airport 1");
    testList.add(airport1);

    AirportInterface airport2 = new AirportBD("airport 2");
    testList.add(airport2);

    AirportInterface airport3 = new AirportBD("airport 3");
    testList.add(airport3);
    
    AirportInterface airport4 = new AirportBD("airport 4");
    testList.add(airport4);
    
    AirportInterface airport5 = new AirportBD("airport 5");
    testList.add(airport5);

    return testList;
  }

  @Override
  public List getRoundTripPath(List nodeList) {
    
    ArrayList<AirportInterface> testList = new ArrayList<AirportInterface>();

    AirportInterface airport1 = new AirportBD("airport 1");
    

    AirportInterface airport2 = new AirportBD("airport 2");
    

    AirportInterface airport3 = new AirportBD("airport 3");
    
    testList.add(airport1);
    testList.add(airport3);
    testList.add(airport2);
    testList.add(airport3);
    testList.add(airport1);

    return testList;
    
  }

  @Override
  public List getAllNodes() {
    
    ArrayList<AirportInterface> testList = new ArrayList<AirportInterface>();

    AirportInterface airport1 = new AirportBD("airport 1");

    AirportInterface airport2 = new AirportBD("airport 2");

    AirportInterface airport3 = new AirportBD("airport 3");
    
    AirportInterface airport4 = new AirportBD("airport 4");
    
    AirportInterface airport5 = new AirportBD("airport 5");
    
    testList.add(airport1);
    testList.add(airport2);
    testList.add(airport3);
    testList.add(airport4);
    testList.add(airport5);
    
    return testList;
  }

}

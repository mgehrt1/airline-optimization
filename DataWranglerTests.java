import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

/**
 * This class tests the functionality of the AirportDW and MapReaderDW files
 */
public class DataWranglerTests {

  /**
   * Tests the AirportDW constructor and the getCityName() method
   */
  @Test
  public void test1() {
    AirportDW airport = new AirportDW("Madison");

    // test the constructor and city name getter by making sure the city name was
    // set to "Madison"
    assertEquals("Madison", airport.getCityName());
  }

  /**
   * Tests the getters for the adjacent airports and edge weights
   */
  @Test
  public void test2() {
    // create some airports for testing
    AirportDW airport1 = new AirportDW("A");
    AirportDW airport2 = new AirportDW("B");
    AirportDW airport3 = new AirportDW("C");

    // add some adjacent airports to airport1
    airport1.addDestinationAirport(airport2, 10);
    airport1.addDestinationAirport(airport3, 20);

    // get the list of adjacent airports and the edge weights associated with those
    // paths
    List<AirportInterface> airportsList = airport1.getAdjacentAirports();
    List<Integer> edgeWeights = airport1.getEdgeWeights();

    // make sure that the correct list of airports and edge weigths were returned
    assertEquals(airport2, airportsList.get(0));
    assertEquals(airport3, airportsList.get(1));
    assertEquals(Integer.valueOf(10), edgeWeights.get(0));
    assertEquals(Integer.valueOf(20), edgeWeights.get(1));
  }

  /**
   * Tests that reading a file that is not in proper DOT format will return null
   */
  @Test
  public void test3() {
    MapReaderDW reader = new MapReaderDW();
    List<AirportInterface> airports = null;

    // try reading a file that is not in DOT format
    try {
      airports = reader.readAirportsFromFile("InvalidFileFormat.txt");
    } catch (FileNotFoundException e) {
      fail(); // Fail if FileNotFoundException is thrown
    }

    // make sure that null was returned
    assertEquals(null, airports);
  }

  /**
   * Tests the readAirportsFromFile() method by using a file to get a list of
   * airports
   */
  @Test
  public void test4() {
    MapReaderDW reader = new MapReaderDW();
    List<AirportInterface> airports = null;

    try {
      // read the test file
      airports = reader.readAirportsFromFile("TestMap.txt");
    } catch (FileNotFoundException e) {
      fail();
    }

    // test all the names of the airports returned
    assertEquals(11, airports.size());
    assertEquals("Madison", airports.get(0).getCityName());
    assertEquals("Paris", airports.get(1).getCityName());
    assertEquals("London", airports.get(2).getCityName());
    assertEquals("Toronto", airports.get(3).getCityName());
    assertEquals("Tokyo", airports.get(4).getCityName());
    assertEquals("Berlin", airports.get(5).getCityName());
    assertEquals("Sacramento", airports.get(6).getCityName());
    assertEquals("Warsaw", airports.get(7).getCityName());
    assertEquals("Madrid", airports.get(8).getCityName());
    assertEquals("Boston", airports.get(9).getCityName());
    assertEquals("Cairo", airports.get(10).getCityName());

    // test the connecting airports in some of the airports
    // first adjacent airport to Madison should be Paris
    assertEquals(airports.get(1), airports.get(0).getAdjacentAirports().get(0));
    // second adjacent airport to Toronto should be Madrid
    assertEquals(airports.get(8), airports.get(3).getAdjacentAirports().get(1));
    // first adjacent airport to Cairo should be Toronto
    assertEquals(airports.get(3), airports.get(10).getAdjacentAirports().get(0));

    // test the connectign path edge weights in some of the airports
    // the edge weight of Madison to Paris should be 4145 miles
    assertEquals(Integer.valueOf(4145), airports.get(0).getEdgeWeights().get(0));
    // the edge weight of Toronto to Madrid should be 3748 miles
    assertEquals(Integer.valueOf(3748), airports.get(3).getEdgeWeights().get(1));
    // the edge weight of Cairo to Toronto should be 5718 miles
    assertEquals(Integer.valueOf(5718), airports.get(10).getEdgeWeights().get(0));

  }

  /**
   * Tests that an exception is thrown when the file does not exist, and does not
   * throw an exception when the file is valid
   */
  @Test
  public void test5() {
    MapReaderDW reader = new MapReaderDW();

    // try reading a file that doesn't exist
    try {
      reader.readAirportsFromFile("FakeFile.txt");
      fail(); // if an exception is not thrown, the test should fail
    } catch (FileNotFoundException e) {
      // the FileNotFoundException is expected to be caught
    }

    // try reading a file that is valid
    try {
      reader.readAirportsFromFile("TestMap.txt");
    } catch (FileNotFoundException e) {
      fail(); // if a FileNotFoundException is thrown, the test should fail
    }
  }

  /**
   * Tests the integration of the DW and BD by testing that the backend method
   * loadMap() loads the correct map by using the information it gets from the
   * DW's readAirportsFromFile() method
   */
  @Test
  public void Integration1() {
    // initialize all needed objects
    MapReaderDW reader = new MapReaderDW();
    DijkstraShortestPathAE<AirportInterface, Integer> path = new DijkstraShortestPathAE<>();
    AirlineOptimizationBackendBD backend = new AirlineOptimizationBackendBD(reader, path);

    // load the test file to create a map
    try {
      backend.loadMap("TestMap.txt");
    } catch (Exception e) {
      fail(); // FNF exception should not be thrown
    }

    // get the list of all airports added to the map
    List<AirportInterface> actualCities = backend.displayCities();

    // get the list of all airport names that should've been added
    List<String> expectedCities = new ArrayList<String>();
    expectedCities.add("Madison");
    expectedCities.add("Paris");
    expectedCities.add("London");
    expectedCities.add("Toronto");
    expectedCities.add("Tokyo");
    expectedCities.add("Berlin");
    expectedCities.add("Sacramento");
    expectedCities.add("Warsaw");
    expectedCities.add("Madrid");
    expectedCities.add("Boston");
    expectedCities.add("Cairo");

    // check to make sure that the list of airports that were added to the map are
    // correct
    assertEquals(11, actualCities.size());
    assertTrue(expectedCities.contains(actualCities.get(0).getCityName()));
    assertTrue(expectedCities.contains(actualCities.get(1).getCityName()));
    assertTrue(expectedCities.contains(actualCities.get(2).getCityName()));
    assertTrue(expectedCities.contains(actualCities.get(3).getCityName()));
    assertTrue(expectedCities.contains(actualCities.get(4).getCityName()));
    assertTrue(expectedCities.contains(actualCities.get(5).getCityName()));
    assertTrue(expectedCities.contains(actualCities.get(6).getCityName()));
    assertTrue(expectedCities.contains(actualCities.get(7).getCityName()));
    assertTrue(expectedCities.contains(actualCities.get(8).getCityName()));
    assertTrue(expectedCities.contains(actualCities.get(9).getCityName()));
    assertTrue(expectedCities.contains(actualCities.get(10).getCityName()));
  }

  /**
   * Tests the integration of the DW and BD by testing that the backend method
   * getStatistics() returns the correct statistics which further makes sure that
   * the backend loads the map correctly from the DW's readAirportsFromFile()
   * method
   */
  @Test
  public void Integration2() {
    // initialize all needed objects
    MapReaderDW reader = new MapReaderDW();
    DijkstraShortestPathAE<AirportInterface, Integer> path = new DijkstraShortestPathAE<>();
    AirlineOptimizationBackendBD backend = new AirlineOptimizationBackendBD(reader, path);

    // load the test file to create a map
    try {
      backend.loadMap("TestMap.txt");
    } catch (Exception e) {
      fail(); // FNF exception should not be thrown
    }

    // get the statistics from the map that was created
    String stats = backend.getStatistics();
    String expected = "Airport Map Contains:" + System.lineSeparator() + "      11 airports"
        + System.lineSeparator() + "       5381 average travel distance";

    // make sure that the correct string of statistics were returned
    assertEquals(expected, stats);
  }

  /**
   * Reviews the AE's code by testing getting the path with a certain stop along
   * the way by using the getPathWithNodeVisit() method
   */
  @Test
  public void CodeReviewOfAE1() {
    // make the graph for testing
    DijkstraShortestPathAE<String, Integer> graph = new DijkstraShortestPathAE<String, Integer>();
    graph.insertNode("Madison");
    graph.insertNode("Tokyo");
    graph.insertNode("Paris");
    graph.insertNode("Beijing");

    graph.insertEdge("Madison", "Tokyo", 1);
    graph.insertEdge("Madison", "Paris", 4);
    graph.insertEdge("Paris", "Beijing", 3);
    graph.insertEdge("Beijing", "Tokyo", 4);

    // create the list of cities that contains the starting, ending, and
    // intermediate cities
    List<String> cities = new ArrayList<String>();
    cities.add("Madison"); // starting node
    cities.add("Beijing"); // node that must be stopped at
    cities.add("Tokyo"); // ending node

    List<String> path = graph.getPathWithNodeVisit(cities);

    // make sure that the correct path of cities was returned
    assertEquals(4, path.size());
    assertEquals("Madison", path.get(0));
    assertEquals("Paris", path.get(1));
    assertEquals("Beijing", path.get(2));
    assertEquals("Tokyo", path.get(3));
  }

  /**
   * Reviews the AE's code by testing getting all the nodes in a graph by using
   * the getAllNodes() method
   */
  @Test
  public void CodeReviewOfAE2() {
    // make the graph for testing
    DijkstraShortestPathAE<String, Integer> graph = new DijkstraShortestPathAE<String, Integer>();
    graph.insertNode("Madison");
    graph.insertNode("Tokyo");
    graph.insertNode("Paris");
    graph.insertNode("Beijing");

    graph.insertEdge("Madison", "Tokyo", 1);
    graph.insertEdge("Madison", "Paris", 4);
    graph.insertEdge("Paris", "Beijing", 3);
    graph.insertEdge("Beijing", "Tokyo", 4);

    // get a list of all nodes that are expected to be in the graph
    List<String> expected = graph.getAllNodes();

    // check to make sure that the list of all nodes is what it is suppose to be
    assertEquals(4, expected.size());
    assertTrue(expected.contains("Madison"));
    assertTrue(expected.contains("Tokyo"));
    assertTrue(expected.contains("Paris"));
    assertTrue(expected.contains("Beijing"));
  }

}

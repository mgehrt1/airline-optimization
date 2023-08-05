import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class tests the implementation of the DijkstraShortestPathAE class
 * by checking the validity of certain paths for a given graph
 * 
 * @author Will Fitzgerald
 */
public class AlgorithmEngineerTests {

    /**
     * Test a round trip and path with Node Visit with a path that is valid to and
     * from the two destinations on the graph
     */
    @Test
    public void test1() {
        // Create a graph of Airport Names
        DijkstraShortestPathAE<String, Integer> graph = new DijkstraShortestPathAE<String, Integer>();
        graph.insertNode("Appleton");
        graph.insertNode("Brunswick");
        graph.insertNode("Delaware");
        graph.insertNode("Eugene");
        graph.insertNode("Fort Lauderdale");
        graph.insertNode("Greenville");
        graph.insertNode("Houston");
        graph.insertNode("Indianapolis");
        graph.insertNode("Lincoln");
        graph.insertNode("Melbourne");

        graph.insertEdge("Appleton", "Brunswick", 1000);
        graph.insertEdge("Appleton", "Houston", 8000);
        graph.insertEdge("Appleton", "Melbourne", 5000);
        graph.insertEdge("Brunswick", "Melbourne", 3000);
        graph.insertEdge("Delaware", "Appleton", 7000);
        graph.insertEdge("Delaware", "Greenville", 2000);
        graph.insertEdge("Fort Lauderdale", "Greenville", 9000);
        graph.insertEdge("Greenville", "Lincoln", 7000);
        graph.insertEdge("Houston", "Brunswick", 6000);
        graph.insertEdge("Houston", "Indianapolis", 2000);
        graph.insertEdge("Indianapolis", "Delaware", 1000);
        graph.insertEdge("Indianapolis", "Lincoln", 5000);
        graph.insertEdge("Indianapolis", "Houston", 2000);
        graph.insertEdge("Melbourne", "Eugene", 3000);
        graph.insertEdge("Melbourne", "Fort Lauderdale", 4000);

        // Create and test expected list of the shortest round trip path between
        // Appleton Airport -> Delaware Airport -> Appleton Airport
        List<String> list = new ArrayList<>();
        list.add("Appleton");
        list.add("Delaware");

        List<String> pathExpected = new ArrayList<>();
        pathExpected.add("Appleton");
        pathExpected.add("Houston");
        pathExpected.add("Indianapolis");
        pathExpected.add("Delaware");
        pathExpected.add("Appleton");

        // Create and Test expected list of the shortest path
        // with a visit specific Node Trip Trace
        // Appleton Airport -> Houston Airport -> Delaware Airport
        List<String> pathExpected2 = new ArrayList<>();
        pathExpected2.add("Appleton");
        pathExpected2.add("Houston");
        pathExpected2.add("Indianapolis");
        pathExpected2.add("Delaware");

        List<String> list2 = new ArrayList<>();
        list2.add("Appleton");
        list2.add("Houston");
        list2.add("Delaware");

        assertEquals(pathExpected, graph.getRoundTripPath(list));
        assertEquals(pathExpected2, graph.getPathWithNodeVisit(list2));

        // Test get all nodes for the graph
        List<String> totalAirportList = new ArrayList<>();
        totalAirportList.add("Eugene");
        totalAirportList.add("Delaware");
        totalAirportList.add("Fort Lauderdale");
        totalAirportList.add("Appleton");
        totalAirportList.add("Indianapolis");
        totalAirportList.add("Greenville");
        totalAirportList.add("Lincoln");
        totalAirportList.add("Houston");
        totalAirportList.add("Melbourne");
        totalAirportList.add("Brunswick");

        assertEquals(totalAirportList, graph.getAllNodes());
    }

    /**
     * Test Round Trip and Node with visit with only a valid path to the
     * destination, but not a valid path on the way back
     */
    @Test
    public void test2() {
        // Create a graph of Airport Names
        DijkstraShortestPathAE<String, Integer> graph = new DijkstraShortestPathAE<String, Integer>();
        graph.insertNode("Appleton");
        graph.insertNode("Brunswick");
        graph.insertNode("Delaware");
        graph.insertNode("Eugene");
        graph.insertNode("Fort Lauderdale");
        graph.insertNode("Greenville");
        graph.insertNode("Houston");
        graph.insertNode("Indianapolis");
        graph.insertNode("Lincoln");
        graph.insertNode("Melbourne");

        graph.insertEdge("Appleton", "Brunswick", 1000);
        graph.insertEdge("Appleton", "Houston", 8000);
        graph.insertEdge("Appleton", "Melbourne", 5000);
        graph.insertEdge("Brunswick", "Melbourne", 3000);
        graph.insertEdge("Delaware", "Appleton", 7000);
        graph.insertEdge("Delaware", "Greenville", 2000);
        graph.insertEdge("Fort Lauderdale", "Greenville", 9000);
        graph.insertEdge("Greenville", "Lincoln", 7000);
        graph.insertEdge("Houston", "Brunswick", 6000);
        graph.insertEdge("Houston", "Indianapolis", 2000);
        graph.insertEdge("Indianapolis", "Delaware", 1000);
        graph.insertEdge("Indianapolis", "Lincoln", 5000);
        graph.insertEdge("Indianapolis", "Houston", 2000);
        graph.insertEdge("Melbourne", "Eugene", 3000);
        graph.insertEdge("Melbourne", "Fort Lauderdale", 4000);

        // Create and test expected list of the shortest round trip path between
        // Appleton Airport -> Eugene Airport -> Not Possible
        List<String> list = new ArrayList<>();
        list.add("Appleton");
        list.add("Eugene");

        // Create and Test expected list of the shortest path
        // with a visit specific Node Trip Trace
        // Appleton Airport -> Brunswick Airport -> Delaware Airport
        List<String> pathExpected2 = new ArrayList<>();
        pathExpected2.add("Appleton");
        pathExpected2.add("Brunswick");
        pathExpected2.add("Melbourne");
        pathExpected2.add("Eugene");

        List<String> list2 = new ArrayList<>();
        list2.add("Appleton");
        list2.add("Brunswick");
        list2.add("Eugene");

        // Method should throw a NoSuchElementException since there is no path back
        assertEquals(null, graph.getRoundTripPath(list));
        assertEquals(pathExpected2, graph.getPathWithNodeVisit(list2));

        // Test get all nodes for the graph
        List<String> totalAirportList = new ArrayList<>();
        totalAirportList.add("Eugene");
        totalAirportList.add("Delaware");
        totalAirportList.add("Fort Lauderdale");
        totalAirportList.add("Appleton");
        totalAirportList.add("Indianapolis");
        totalAirportList.add("Greenville");
        totalAirportList.add("Lincoln");
        totalAirportList.add("Houston");
        totalAirportList.add("Melbourne");
        totalAirportList.add("Brunswick");

        assertEquals(totalAirportList, graph.getAllNodes());
    }

    /**
     * Test correct handling of an invalid path or no possible path between two
     * nodes on the graph
     */
    @Test
    public void test3() {
        // Create a graph of Airport Names
        DijkstraShortestPathAE<String, Integer> graph = new DijkstraShortestPathAE<String, Integer>();
        graph.insertNode("Appleton");
        graph.insertNode("Brunswick");
        graph.insertNode("Delaware");
        graph.insertNode("Eugene");
        graph.insertNode("Fort Lauderdale");
        graph.insertNode("Greenville");
        graph.insertNode("Houston");
        graph.insertNode("Indianapolis");
        graph.insertNode("Lincoln");
        graph.insertNode("Melbourne");

        graph.insertEdge("Appleton", "Brunswick", 1000);
        graph.insertEdge("Appleton", "Houston", 8000);
        graph.insertEdge("Appleton", "Melbourne", 5000);
        graph.insertEdge("Brunswick", "Melbourne", 3000);
        graph.insertEdge("Delaware", "Appleton", 7000);
        graph.insertEdge("Delaware", "Greenville", 2000);
        graph.insertEdge("Fort Lauderdale", "Greenville", 9000);
        graph.insertEdge("Greenville", "Lincoln", 7000);
        graph.insertEdge("Houston", "Brunswick", 6000);
        graph.insertEdge("Houston", "Indianapolis", 2000);
        graph.insertEdge("Indianapolis", "Delaware", 1000);
        graph.insertEdge("Indianapolis", "Lincoln", 5000);
        graph.insertEdge("Indianapolis", "Houston", 2000);
        graph.insertEdge("Melbourne", "Eugene", 3000);
        graph.insertEdge("Melbourne", "Fort Lauderdale", 4000);

        // Create and test expected list of the shortest round trip path between
        // Brunswick Airport -> Houston Airport (Not Possible)
        List<String> list = new ArrayList<>();
        list.add("Brunswick");
        list.add("Houston");

        assertEquals(null, graph.getPathWithNodeVisit(list));
        assertEquals(null, graph.getRoundTripPath(list));

        // Create and test expected list of the shortest round trip path between
        // Brunswick Airport -> Houston Airport (Not Possible)
        List<String> list2 = new ArrayList<>();
        list.add("Lincoln");
        list.add("Eugene");

        assertEquals(null, graph.getPathWithNodeVisit(list2));
        assertEquals(null, graph.getRoundTripPath(list2));

        // Test get all nodes for the graph
        List<String> totalAirportList = new ArrayList<>();
        totalAirportList.add("Eugene");
        totalAirportList.add("Delaware");
        totalAirportList.add("Fort Lauderdale");
        totalAirportList.add("Appleton");
        totalAirportList.add("Indianapolis");
        totalAirportList.add("Greenville");
        totalAirportList.add("Lincoln");
        totalAirportList.add("Houston");
        totalAirportList.add("Melbourne");
        totalAirportList.add("Brunswick");

        assertEquals(totalAirportList, graph.getAllNodes());
    }

    /**
     * Test Round Trip and Node with visit with a valid round trip path,
     * but not a valid possible path for the specified node to be visited
     * on the way to the destination
     */
    @Test
    public void test4() {
        // Create a graph of Airport Names
        DijkstraShortestPathAE<String, Integer> graph = new DijkstraShortestPathAE<String, Integer>();
        graph.insertNode("Appleton");
        graph.insertNode("Brunswick");
        graph.insertNode("Delaware");
        graph.insertNode("Eugene");
        graph.insertNode("Fort Lauderdale");
        graph.insertNode("Greenville");
        graph.insertNode("Houston");
        graph.insertNode("Indianapolis");
        graph.insertNode("Lincoln");
        graph.insertNode("Melbourne");

        graph.insertEdge("Appleton", "Brunswick", 1000);
        graph.insertEdge("Appleton", "Houston", 8000);
        graph.insertEdge("Appleton", "Melbourne", 5000);
        graph.insertEdge("Brunswick", "Melbourne", 3000);
        graph.insertEdge("Delaware", "Appleton", 7000);
        graph.insertEdge("Delaware", "Greenville", 2000);
        graph.insertEdge("Fort Lauderdale", "Greenville", 9000);
        graph.insertEdge("Greenville", "Lincoln", 7000);
        graph.insertEdge("Houston", "Brunswick", 6000);
        graph.insertEdge("Houston", "Indianapolis", 2000);
        graph.insertEdge("Indianapolis", "Delaware", 1000);
        graph.insertEdge("Indianapolis", "Lincoln", 5000);
        graph.insertEdge("Indianapolis", "Houston", 2000);
        graph.insertEdge("Melbourne", "Eugene", 3000);
        graph.insertEdge("Melbourne", "Fort Lauderdale", 4000);

        // Create and test expected list of the shortest round trip path between
        // Appleton Airport -> Indianapolis Airport -> Appleton Airport
        // and test expected list of the shortest path between with a
        // specifc visit on the way Appleton -> Melbourne -> Indianapolis (Not Possible)
        List<String> list = new ArrayList<>();
        list.add("Appleton");
        list.add("Indianapolis");

        List<String> expectedList = new ArrayList<>();
        expectedList.add("Appleton");
        expectedList.add("Houston");
        expectedList.add("Indianapolis");
        expectedList.add("Delaware");
        expectedList.add("Appleton");

        // Create and test expected list of the shortest round trip path between
        // Appleton Airport -> Melbourne Airport -> Indianapolis Airport (Not Possible)
        List<String> expectedList2 = new ArrayList<>();
        expectedList2.add("Appleton");
        expectedList2.add("Melbourne");
        expectedList2.add("Indianapolis");

        assertEquals(expectedList, graph.getRoundTripPath(list));
        assertEquals(null, graph.getPathWithNodeVisit(list));

        // Test get all nodes for the graph
        List<String> totalAirportList = new ArrayList<>();
        totalAirportList.add("Eugene");
        totalAirportList.add("Delaware");
        totalAirportList.add("Fort Lauderdale");
        totalAirportList.add("Appleton");
        totalAirportList.add("Indianapolis");
        totalAirportList.add("Greenville");
        totalAirportList.add("Lincoln");
        totalAirportList.add("Houston");
        totalAirportList.add("Melbourne");
        totalAirportList.add("Brunswick");

    }

    /**
     * Tests edge case handling of invalid inputs, invalid airport names,
     * and an empty graph
     */
    @Test
    public void test5() {
        // Create a graph of Airport Names
        DijkstraShortestPathAE<String, Integer> graph = new DijkstraShortestPathAE<String, Integer>();
        graph.insertNode("Appleton");
        graph.insertNode("Brunswick");
        graph.insertNode("Delaware");
        graph.insertNode("Eugene");
        graph.insertNode("Fort Lauderdale");
        graph.insertNode("Greenville");
        graph.insertNode("Houston");
        graph.insertNode("Indianapolis");
        graph.insertNode("Lincoln");
        graph.insertNode("Melbourne");

        graph.insertEdge("Appleton", "Brunswick", 1000);
        graph.insertEdge("Appleton", "Houston", 8000);
        graph.insertEdge("Appleton", "Melbourne", 5000);
        graph.insertEdge("Brunswick", "Melbourne", 3000);
        graph.insertEdge("Delaware", "Appleton", 7000);
        graph.insertEdge("Delaware", "Greenville", 2000);
        graph.insertEdge("Fort Lauderdale", "Greenville", 9000);
        graph.insertEdge("Greenville", "Lincoln", 7000);
        graph.insertEdge("Houston", "Brunswick", 6000);
        graph.insertEdge("Houston", "Indianapolis", 2000);
        graph.insertEdge("Indianapolis", "Delaware", 1000);
        graph.insertEdge("Indianapolis", "Lincoln", 5000);
        graph.insertEdge("Indianapolis", "Houston", 2000);
        graph.insertEdge("Melbourne", "Eugene", 3000);
        graph.insertEdge("Melbourne", "Fort Lauderdale", 4000);

        // Test get all nodes for the graph
        List<String> totalAirportList = new ArrayList<>();
        totalAirportList.add("Eugene");
        totalAirportList.add("Delaware");
        totalAirportList.add("Fort Lauderdale");
        totalAirportList.add("Appleton");
        totalAirportList.add("Indianapolis");
        totalAirportList.add("Greenville");
        totalAirportList.add("Lincoln");
        totalAirportList.add("Houston");
        totalAirportList.add("Melbourne");
        totalAirportList.add("Brunswick");

        assertEquals(totalAirportList, graph.getAllNodes());

        // Test not enough/too many params for each method

        // Too few args
        List<String> list = new ArrayList<>();
        list.add("Brunswick");
        list.add("Houston");
        assertEquals(null, graph.getPathWithNodeVisit(list));

        // Too many args
        List<String> list2 = new ArrayList<>();
        list2.add("Indianapolis");
        list2.add("Houston");
        list2.add("Appleton");
        assertEquals(null, graph.getRoundTripPath(list2));

        // Invalid and Empty Names
        List<String> list3 = new ArrayList<>();
        list2.add("Not an Airport");
        list2.add("Houston");
        list2.add("Appleton");

        assertEquals(null, graph.getPathWithNodeVisit(list3));
        assertEquals(null, graph.getRoundTripPath(list3));

        List<String> list4 = new ArrayList<>();
        assertEquals(null, graph.getPathWithNodeVisit(list4));
        assertEquals(null, graph.getRoundTripPath(list4));

        // Create an empty graph
        DijkstraShortestPathAE<String, Integer> graph2 = new DijkstraShortestPathAE<String, Integer>();

        // Empty graph should return an empty array when asked for all nodes
        List<String> list5 = new ArrayList<>();
        assertEquals(list5, graph2.getAllNodes());
    }

    /**
     * Tests for correctness of loading data from DW into BD, and finding a round
     * trip path using AE
     */
    @Test
    public void integration1() {
        // Get instances of DW BD AE classes
        MapReaderDW mapReader = new MapReaderDW();
        DijkstraShortestPathInterface<AirportInterface, Integer> graph = new DijkstraShortestPathAE<AirportInterface, Integer>();
        AirlineOptimizationBackendBD backend = new AirlineOptimizationBackendBD(mapReader, graph);

        // BD communicates with DW and loads/creates this graph
        try {
            backend.loadMap("TestMap.txt");
        } catch (FileNotFoundException e) {
            fail();
        }

        // BD calls AE to get a round trip using AE methods
        List<AirportInterface> listActual = backend.roundTrip("Madison", "Tokyo");

        // Compare each city from the airport list
        assertEquals(4, listActual.size());
        assertEquals("Madison", listActual.get(0).getCityName());
        assertEquals("Paris", listActual.get(1).getCityName());
        assertEquals("Tokyo", listActual.get(2).getCityName());
        assertEquals("Madison", listActual.get(3).getCityName());

    }

    /**
     * Tests for correctness of loading data from DW into BD, displaying all
     * airports from AE, and Displaying correct statistics and list of cities
     */
    @Test
    public void integration2() {
        // Get instances of DW BD AE classes
        MapReaderDW mapReader = new MapReaderDW();
        DijkstraShortestPathInterface<AirportInterface, Integer> graph = new DijkstraShortestPathAE<AirportInterface, Integer>();
        AirlineOptimizationBackendBD backend = new AirlineOptimizationBackendBD(mapReader, graph);

        // BD communicates with DW and loads/creates this graph
        try {
            backend.loadMap("TestMap.txt");
        } catch (FileNotFoundException e) {
            fail();
        }

        // BD calls AE to get all nodes in the graph
        List<AirportInterface> listActual = backend.displayCities();
        List<String> listExpected = new ArrayList<String>();
        listExpected.add("Boston");
        listExpected.add("Cairo");
        listExpected.add("Berlin");
        listExpected.add("London");
        listExpected.add("Madison");
        listExpected.add("Sacramento");
        listExpected.add("Tokyo");
        listExpected.add("Toronto");
        listExpected.add("Warsaw");
        listExpected.add("Paris");
        listExpected.add("Madrid");

        // Check size correctness and that each city was loaded
        assertEquals(listActual.size(), 11);
        assertTrue(listExpected.contains(listActual.get(0).getCityName()));
        assertTrue(listExpected.contains(listActual.get(1).getCityName()));
        assertTrue(listExpected.contains(listActual.get(2).getCityName()));
        assertTrue(listExpected.contains(listActual.get(3).getCityName()));
        assertTrue(listExpected.contains(listActual.get(4).getCityName()));
        assertTrue(listExpected.contains(listActual.get(5).getCityName()));
        assertTrue(listExpected.contains(listActual.get(6).getCityName()));
        assertTrue(listExpected.contains(listActual.get(7).getCityName()));
        assertTrue(listExpected.contains(listActual.get(8).getCityName()));
        assertTrue(listExpected.contains(listActual.get(9).getCityName()));
        assertTrue(listExpected.contains(listActual.get(10).getCityName()));

        // BD uses AE and DW to get and make graph, and displays the respective stats
        String statActual = backend.getStatistics();

        // Expected Stat info
        String statExpected = "Airport Map Contains:" + System.lineSeparator() + "      " + 11 + " airports"
                + System.lineSeparator() +
                "       " + 5381 + " average travel distance";

        assertEquals(statActual, statExpected);
    }

    /**
     * This method will test the correctness of the DW class's
     * handling of files in the method readAirportFromFile()
     */
    @Test
    public void CodeReviewDataWrangler1() {
        // Create an instance of the DW reader class
        MapReaderDW reader = new MapReaderDW();

        // Check correct handling of non-existing file
        assertThrows(FileNotFoundException.class, () -> {
            reader.readAirportsFromFile("notAFile.txt");

        });

        // Check correct handling of null
        assertThrows(FileNotFoundException.class, () -> {
            reader.readAirportsFromFile(null);
        });

        // Check correct handling of an existing file
        assertDoesNotThrow(() -> {
            reader.readAirportsFromFile("TestMap.txt");
        });

        // Test correct handling of an existing java file but not a text file
        assertDoesNotThrow(() -> {
            reader.readAirportsFromFile("AlgorithmEngineerTests.java");
        });
    }

    /**
     * This method will test the correct parsing through a file in the DW method
     * readAirportsFromFile()
     */
    @Test
    public void CodeReviewDataWrangler2() {
        // Create instance of the DW reader class
        MapReaderDW reader = new MapReaderDW();
        List<AirportInterface> airports = null;

        // Load data from existing file
        try {
            airports = reader.readAirportsFromFile("TestMapAE.txt");
        } catch (FileNotFoundException e) {
            fail();
        }

        // Test the ordering and existance of each airport from the file
        assertEquals("Appleton", airports.get(0).getCityName());
        assertEquals("Brunswick", airports.get(1).getCityName());
        assertEquals("Houston", airports.get(2).getCityName());
        assertEquals("Melbourne", airports.get(3).getCityName());
        assertEquals("Delaware", airports.get(4).getCityName());

        // Test airport connections
        // First adjacent airport to Appleton should be Brunsiwck
        assertEquals(airports.get(1), airports.get(0).getAdjacentAirports().get(0));
        // Second adjacent airport to Appleton should be Houston
        assertEquals(airports.get(2), airports.get(0).getAdjacentAirports().get(1));
        // First adjacent airport to Houston should be Delaware
        assertEquals(airports.get(4), airports.get(2).getAdjacentAirports().get(0));

        // Test edge weights for airport connections
        // Edge weight of Appleton to Brunswick should be 4
        assertEquals(Integer.valueOf(4), airports.get(0).getEdgeWeights().get(0));
        // Edge weight of Brunswick to Melbourne should be 9
        assertEquals(Integer.valueOf(9), airports.get(1).getEdgeWeights().get(0));
        // Edge weight of Houston to Delaware should be 5
        assertEquals(Integer.valueOf(5), airports.get(2).getEdgeWeights().get(0));
    }
}

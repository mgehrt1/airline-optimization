// --== CS400 File Header Information ==--
// Name: Praneet Gudladana
// Email: gudladana@wisc.edu
// Group and Team: AI Blue
// Group TA: Rachit Tibdewal
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import static org.junit.jupiter.api.Assertions.*;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
/**
 * tester class for frontend methods
 * @author praneet
 *
 */
class FrontendDeveloperTests {
	
	/**
	 * testing the main menu prompt
	 */
	@Test
	void test1() {
		// use DW placeholder code to load the map
		MapReaderInterface reader = new MapReaderFD();
		// use AE placeholder code to make a graph
		DijkstraShortestPathInterface<AirportInterface, Integer> map = new DijkstraShortestPathFD<AirportInterface, Integer>();
		// use BD placeholder code for the backend
		AirlineOptimizationBackendInterface backend = new AirlineOptimizationBackendFD(reader, map);
		// make a TextUITester to test system output
		TextUITester tester = new TextUITester("Q\n");
		Scanner scnr = new Scanner(System.in);
		// Create a frontend instance to test its methods on
		AirlineOptimizationFrontendInterface frontend = new AirlineOptimizationFrontend(scnr, backend, reader);
		frontend.runCommandLoop();
		String output = tester.checkOutput();
		scnr.close();

		//test that the command loop and main menu prompt display the correct text
		assertEquals(output, "-------------------------------------------------------------------------------"
				+ System.lineSeparator() + "Welcome to the Airplane Optimization Application!" + System.lineSeparator()
				+ "-------------------------------------------------------------------------------" + System.lineSeparator()
				+ "Choose a command by entering in the corresponding letter in brackets:" + System.lineSeparator()
				+ "    [L]oad Map" + System.lineSeparator() + "    Find [O]ne-way trip" + System.lineSeparator()
				+ "    Find [R]ound-trip" + System.lineSeparator() + "    Find [M]ulti-city trip" + System.lineSeparator()
				+ "    Display [C]ities" + System.lineSeparator() + "    [D]isplay Statistics" + System.lineSeparator()
				+ "    [Q]uit" + System.lineSeparator() + "Choose command: "
				+ "-------------------------------------------------------------------------------" + System.lineSeparator()
				+ "Thank you for using the Airplane Optimization Application!" + System.lineSeparator()
				+ "-------------------------------------------------------------------------------"
				+ System.lineSeparator());
	}
	
	/**
	 * testing loading a map into the app
	 */
	@Test
	void test2() {
		// use DW placeholder code to load the map
		MapReaderInterface reader = new MapReaderFD();
		// use AE placeholder code to make a graph
		DijkstraShortestPathInterface<AirportInterface, Integer> map = new DijkstraShortestPathFD<AirportInterface, Integer>();
		// use BD placeholder code for the backend
		AirlineOptimizationBackendInterface backend = new AirlineOptimizationBackendFD(reader, map);
		// make a TextUITester to test system output
		TextUITester tester = new TextUITester("L\ntest.txt\nQ\n");
		Scanner scnr = new Scanner(System.in);
		// Create a frontend instance to test its methods on
		AirlineOptimizationFrontendInterface frontend = new AirlineOptimizationFrontend(scnr, backend, reader);
		frontend.runCommandLoop();
		String output = tester.checkOutput();
		scnr.close();

		assertTrue(output.contains("Airline data loaded successfully!"));
	}
	
	/**
	 * Testing the one way trip method
	 */
	@Test
	void test3() {
		 // use DW placeholder code to load the map
	    MapReaderInterface reader = new MapReaderFD();
	    // use AE placeholder code to make a graph
	    DijkstraShortestPathInterface<AirportInterface, Integer> map = new DijkstraShortestPathFD<AirportInterface, Integer>();
	    // use BD placeholder code for the backend
	    AirlineOptimizationBackendInterface backend = new AirlineOptimizationBackendFD(reader, map);
	    // make a TextUITester to test system output
	    TextUITester tester = new TextUITester("O\nheathrow\nJFK\nQ\n");
	    Scanner scnr = new Scanner(System.in);
	    // Create a frontend instance to test its methods on
	    AirlineOptimizationFrontendInterface frontend = new AirlineOptimizationFrontend(scnr, backend, reader);
	    frontend.runCommandLoop();
	    String output = tester.checkOutput();
	    scnr.close();

		assertTrue(output.contains("your one way trip is: Airport 1 -> Airport 2 -> Airport 3"));
	}
	
	/**
	 * Testing the round trip method
	 */
	@Test
	void test4() {
		 // use DW placeholder code to load the map
	    MapReaderInterface reader = new MapReaderFD();
	    // use AE placeholder code to make a graph
	    DijkstraShortestPathInterface<AirportInterface, Integer> map = new DijkstraShortestPathFD<AirportInterface, Integer>();
	    // use BD placeholder code for the backend
	    AirlineOptimizationBackendInterface backend = new AirlineOptimizationBackendFD(reader, map);
	    // make a TextUITester to test system output
	    TextUITester tester = new TextUITester("R\nHeathrow\nJFK\nQ\n");
	    Scanner scnr = new Scanner(System.in);
	    // Create a frontend instance to test its methods on
	    AirlineOptimizationFrontendInterface frontend = new AirlineOptimizationFrontend(scnr, backend, reader);
	    frontend.runCommandLoop();
	    String output = tester.checkOutput();
	    scnr.close();

		assertTrue(output.contains("your round trip is: Airport 1 -> Airport 2 -> Airport 3 -> Airport 1"));
	}
	
	/**
	 * Testing the Statistics and display cities methods
	 */
	@Test
	void test5() {
		 // use DW placeholder code to load the map
	    MapReaderInterface reader = new MapReaderFD();
	    // use AE placeholder code to make a graph
	    DijkstraShortestPathInterface<AirportInterface, Integer> map = new DijkstraShortestPathFD<AirportInterface, Integer>();
	    // use BD placeholder code for the backend
	    AirlineOptimizationBackendInterface backend = new AirlineOptimizationBackendFD(reader, map);
	    // make a TextUITester to test system output
	    TextUITester tester = new TextUITester("C\nQ\n");
	    Scanner scnr = new Scanner(System.in);
	    // Create a frontend instance to test its methods on
	    AirlineOptimizationFrontendInterface frontend = new AirlineOptimizationFrontend(scnr, backend, reader);
	    frontend.runCommandLoop();
	    String output = tester.checkOutput();
	    scnr.close();

		assertTrue(output.contains("Cities: Airport 1, Airport 2, Airport 3"));


		// use DW placeholder code to load the map
		MapReaderInterface reader2 = new MapReaderFD();
		// use AE placeholder code to make a graph
		DijkstraShortestPathInterface<AirportInterface, Integer> map2 = new DijkstraShortestPathFD<AirportInterface, Integer>();
		// use BD placeholder code for the backend
		AirlineOptimizationBackendInterface backend2 = new AirlineOptimizationBackendFD(reader2, map2);
		// make a TextUITester to test system output
		TextUITester tester2 = new TextUITester("D\nHeathrow\nKempegowda\nA\nQ\n");
		Scanner scnr2 = new Scanner(System.in);
		// Create a frontend instance to test its methods on
		AirlineOptimizationFrontendInterface frontend2 = new AirlineOptimizationFrontend(scnr2, backend2, reader2);
		frontend2.runCommandLoop();
		String output2 = tester2.checkOutput();
		scnr2.close();

		assertTrue(output2.contains("5 total airports, 300 miles average distance"));
	}
	
	/**
	 * Testing the One way trip and display cities methods
	 */
	@Test
	void integrationTest1() {
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

		// checking if the correct number of cities are displayed
		assertEquals(11, backend.displayCities().size());
		

		// creating path for testing
		List<String> testPath1 = new ArrayList<String>();
		testPath1.add("Madison");
		testPath1.add("Paris");
		testPath1.add("Tokyo");
		
		List<AirportInterface> actualPath1 = backend.oneWayTrip("Madison", "Tokyo");

		// testing one way trips
		assertEquals(actualPath1.get(0).getCityName(), testPath1.get(0));
		assertEquals(actualPath1.get(1).getCityName(), testPath1.get(1));
		assertEquals(actualPath1.get(2).getCityName(), testPath1.get(2));

	}
	

	@Test
	void integrationTest2() {
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


		// creating path for testing
		List<String> testPath1 = new ArrayList<String>();
		testPath1.add("Madison");
		testPath1.add("London");
		testPath1.add("Boston");
		testPath1.add("Cairo");
		
		List<String> testInputPath1 = new ArrayList<String>();
		testInputPath1.add("Madison");
		testInputPath1.add("London");
		testInputPath1.add("Cairo");
		
		
		List<AirportInterface> actualPath1 = backend.multiCityTrip(testInputPath1);
		
		// testing one way trips
		assertEquals(actualPath1.get(0).getCityName(), testPath1.get(0));
		assertEquals(actualPath1.get(1).getCityName(), testPath1.get(1));
		assertEquals(actualPath1.get(2).getCityName(), testPath1.get(2));
		assertEquals(actualPath1.get(3).getCityName(), testPath1.get(3));
		
		// testing an invalid multi city trip
		
		
		List<String> testInputPath2 = new ArrayList<String>();
		testInputPath2.add("Madison");
		testInputPath2.add("Berlin");
		testInputPath2.add("Cairo");

		List<AirportInterface> actualPath2 = backend.multiCityTrip(testInputPath2);

		assertEquals(actualPath2, null);
	}

	/**
	 * testing the get statistics method 
	 */
	@Test
	void testCodeReviewOfBackendDeveloper1() {
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

		String output = backend.getStatistics();
		assertEquals(output, "Airport Map Contains:\n"
				+ "      11 airports\n"
				+ "       5381 average travel distance");
	}


	 /**
         * testing the display cities method
         */
        @Test
        void testCodeReviewOfBackendDeveloper2() {
                // initialize all objects
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
                List<AirportInterface> displayCities = backend.displayCities();

                // get the list of all airport names that should've been added
		List<String> cities = new ArrayList<String>();
		cities.add("Toronto");
		cities.add("Madison");
		cities.add("Tokyo");
		cities.add("Cairo");
		cities.add("Warsaw");

                // checking if the display cities functions outputs the correct cities
                assertTrue(cities.contains(displayCities.get(0).getCityName()));
                assertTrue(cities.contains(displayCities.get(1).getCityName()));
                assertTrue(cities.contains(displayCities.get(2).getCityName()));
                assertTrue(cities.contains(displayCities.get(3).getCityName()));
                assertTrue(cities.contains(displayCities.get(4).getCityName()));
		
        }
}

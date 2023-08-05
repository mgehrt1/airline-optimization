// --== CS400 File Header Information ==--
// Name: Sampreeth Immidisetty
// Email: immidisetty@wisc.edu
// Group and Team: AI Blue
// Group TA: Rachit Tibdewal
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Testing class for backend methods
 * @author sampreethimmidisetty
 *
 */
class BackendDeveloperTests {

  /**
   * Testing one way trip
   */
  @Test
  void test1() {

    // initializing AE and DW objects


    MapReaderInterface testReader = new MapReaderBD();
    DijkstraShortestPathInterface<AirportInterface, Integer> testPath = new DijkstraShortestPathBD();
    
    AirlineOptimizationBackendBD testBackend = new AirlineOptimizationBackendBD(testReader, testPath);
    
    ArrayList<String> testList = new ArrayList<String>();

//    AirportInterface airport1 = new AirportBD("airport 1");
//    AirportInterface airport2 = new AirportBD("airport 2");
//    AirportInterface airport3 = new AirportBD("airport 3");
    
    testList.add("airport 1");
    testList.add("airport 2");
    testList.add("airport 3");
   
    // testing whether path is output correctly
    assertEquals(testBackend.oneWayTrip("airport 1", "airport 2").get(0).getCityName(), testList.get(0));
    assertEquals(testBackend.oneWayTrip("airport 1", "airport 2").get(1).getCityName(), testList.get(1));
    assertEquals(testBackend.oneWayTrip("airport 1", "airport 2").get(2).getCityName(), testList.get(2));
  }
  
  /**
   * Testing round trip
   */
  @Test
  void test2() {
    
    // initializing AE and DW objects
 
    MapReaderInterface testReader = new MapReaderBD();
    DijkstraShortestPathInterface<AirportInterface, Integer> testPath = new DijkstraShortestPathBD();
    
    AirlineOptimizationBackendBD testBackend = new AirlineOptimizationBackendBD(testReader, testPath);
    
    ArrayList<String> testList = new ArrayList<String>();

//    AirportInterface airport1 = new AirportBD("airport 1");
//    AirportInterface airport2 = new AirportBD("airport 2");
//    AirportInterface airport3 = new AirportBD("airport 3");
    
    
    testList.add("airport 1");
    testList.add("airport 3");
    testList.add("airport 2");
    testList.add("airport 3");
    testList.add("airport 1");
    
    
    // testing whether path is output correctly
    assertEquals(testBackend.roundTrip("airport 1", "airport 2").get(0).getCityName(), testList.get(0));
    assertEquals(testBackend.roundTrip("airport 1", "airport 2").get(1).getCityName(), testList.get(1));
    assertEquals(testBackend.roundTrip("airport 1", "airport 2").get(2).getCityName(), testList.get(2));
    assertEquals(testBackend.roundTrip("airport 1", "airport 2").get(3).getCityName(), testList.get(3));
    assertEquals(testBackend.roundTrip("airport 1", "airport 2").get(4).getCityName(), testList.get(4));
    
  }   
  
  /**
   * Testing multi city trip
   */
  @Test
  void test3() {
    
    // initializing AE and DW objects

    MapReaderInterface testReader = new MapReaderBD();
    DijkstraShortestPathInterface<AirportInterface, Integer> testPath = new DijkstraShortestPathBD();
    
    AirlineOptimizationBackendBD testBackend = new AirlineOptimizationBackendBD(testReader, testPath);
    
    ArrayList<AirportInterface> testList = new ArrayList<AirportInterface>();

    // creating and adding airport objects for testing
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
    
    ArrayList<String> multiCity = new ArrayList<String>();
    multiCity.add("airport 1");
    multiCity.add("airport 3");
    multiCity.add("airport 5");
    
    // testing whether path is output correctly
    assertEquals(testBackend.multiCityTrip(multiCity).get(0).getCityName(), testList.get(0).getCityName());
    assertEquals(testBackend.multiCityTrip(multiCity).get(1).getCityName(), testList.get(1).getCityName());
    assertEquals(testBackend.multiCityTrip(multiCity).get(2).getCityName(), testList.get(2).getCityName());
    assertEquals(testBackend.multiCityTrip(multiCity).get(3).getCityName(), testList.get(3).getCityName());
    assertEquals(testBackend.multiCityTrip(multiCity).get(4).getCityName(), testList.get(4).getCityName());
  }
  
  /**
   * Testing display cities 
   */
  @Test
  void test4() {
    
    // initializing AE and DW objects
    MapReaderInterface testReader = new MapReaderBD();
    DijkstraShortestPathInterface<AirportInterface, Integer> testPath = new DijkstraShortestPathBD();
    
    AirlineOptimizationBackendBD testBackend = new AirlineOptimizationBackendBD(testReader, testPath);
    
    ArrayList<AirportInterface> testList = new ArrayList<AirportInterface>();

    // creating and adding airport objects for testing
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
    
    // testing whether cities are output correctly
    assertEquals(testBackend.displayCities().get(0).getCityName(), testList.get(0).getCityName());
    assertEquals(testBackend.displayCities().get(1).getCityName(), testList.get(1).getCityName());
    assertEquals(testBackend.displayCities().get(2).getCityName(), testList.get(2).getCityName());
    assertEquals(testBackend.displayCities().get(3).getCityName(), testList.get(3).getCityName());
    assertEquals(testBackend.displayCities().get(4).getCityName(), testList.get(4).getCityName());
  }
  
  /**
   * Testing getStatistics
   */
  @Test
  void test5() {
    
    // initializing AE and DW objects
    MapReaderInterface testReader = new MapReaderBD();
    DijkstraShortestPathInterface<AirportInterface, Integer> testPath = new DijkstraShortestPathBD();
    
    AirlineOptimizationBackendBD testBackend = new AirlineOptimizationBackendBD(testReader, testPath);
    testBackend.totalDistance = 100;
    
    assertEquals(testBackend.getStatistics(), "Airport Map Contains:\n"
        + "      5 airports\n"
        + "       20 average travel distance");
    
    
  }

  /**
   * Testing integrated one way trip method and number of airports
   */
  @Test
  public void testIntegration1() {
    
    // initializing required role objects

    MapReaderDW testReader = new MapReaderDW();
    DijkstraShortestPathAE<AirportInterface, Integer> testMap = new DijkstraShortestPathAE<>();
    AirlineOptimizationBackendBD testBackend = new AirlineOptimizationBackendBD(testReader, testMap);

    // loading in test map
    try {
      testBackend.loadMap("TestMap.txt");
    } catch (Exception e) {
      fail(); 
    }

    // creating paths for testing
    List<String> testPath1 = new ArrayList<String>();
    testPath1.add("Madison");
    testPath1.add("Paris");
    testPath1.add("Berlin");

    List<String> testPath2 = new ArrayList<String>();
    testPath2.add("Boston");
    testPath2.add("Berlin");
    testPath2.add("Sacramento");


    // testing one way trips
    assertEquals(testBackend.oneWayTrip("Madison", "Berlin").get(0).getCityName(), testPath1.get(0));
    assertEquals(testBackend.oneWayTrip("Madison", "Berlin").get(1).getCityName(), testPath1.get(1));
    assertEquals(testBackend.oneWayTrip("Madison", "Berlin").get(2).getCityName(), testPath1.get(2));
    
    assertEquals(testBackend.oneWayTrip("Boston", "Sacramento").get(0).getCityName(), testPath2.get(0));
    assertEquals(testBackend.oneWayTrip("Boston", "Sacramento").get(1).getCityName(), testPath2.get(1));
    assertEquals(testBackend.oneWayTrip("Boston", "Sacramento").get(2).getCityName(), testPath2.get(2));

    // testing number of airports in dataset (display cities)
    assertEquals(11, testBackend.displayCities().size());


      }

  /**
   * Testing integrated round trip and multi city method
   */
  @Test
  public void testIntegration2() {

    // initializing required role objects

    MapReaderDW testReader = new MapReaderDW();
    DijkstraShortestPathAE<AirportInterface, Integer> testMap = new DijkstraShortestPathAE<>();
    AirlineOptimizationBackendBD testBackend = new AirlineOptimizationBackendBD(testReader, testMap);

    // loading 
    try {
      testBackend.loadMap("TestMap.txt");
    } catch (Exception e) {
      fail();
    }

    // creating paths for testing
    List<String> testPath1 = new ArrayList<String>();
    testPath1.add("Paris");
    testPath1.add("Tokyo");
    testPath1.add("Madison");
    testPath1.add("Paris");

    List<String> testPath2 = new ArrayList<String>();
    testPath2.add("London");
    testPath2.add("Boston");
    testPath2.add("Cairo");
    testPath2.add("Toronto");

    // creating multicity string input
    List<String> multiCityInput = new ArrayList<String>();
    multiCityInput.add("London");
    multiCityInput.add("Boston");
    multiCityInput.add("Toronto");

    // testing round trip
    assertEquals(testBackend.roundTrip("Paris", "Madison").get(0).getCityName(), testPath1.get(0));
    assertEquals(testBackend.roundTrip("Paris", "Madison").get(1).getCityName(), testPath1.get(1));
    assertEquals(testBackend.roundTrip("Paris", "Madison").get(2).getCityName(), testPath1.get(2));
    assertEquals(testBackend.roundTrip("Paris", "Madison").get(3).getCityName(), testPath1.get(3));

    // testing multicity trip
    assertEquals(testBackend.multiCityTrip(multiCityInput).get(0).getCityName(), testPath2.get(0));
    assertEquals(testBackend.multiCityTrip(multiCityInput).get(1).getCityName(), testPath2.get(1));
    assertEquals(testBackend.multiCityTrip(multiCityInput).get(2).getCityName(), testPath2.get(2));
    assertEquals(testBackend.multiCityTrip(multiCityInput).get(3).getCityName(), testPath2.get(3));


    }
  

  /**
   * Testing frontend implementation of one way trips used in integration tester 1
   */
  @Test
  public void testCodeReviewOfFrontendDeveloper1(){
    
    // initializing required role objects
    MapReaderInterface testReader = new MapReaderDW();
    DijkstraShortestPathInterface<AirportInterface, Integer> testMap = new DijkstraShortestPathAE<AirportInterface, Integer>();
    AirlineOptimizationBackendInterface testBackend = new AirlineOptimizationBackendBD(testReader, testMap);
    TextUITester uiTester = new TextUITester("L\nTestMap.txt\nO\nMadison\nBerlin\nO\nBoston\nSacramento\nQ\n"); // inputting map and outputting one way trip from Madison to Berlin and then from Boston to Sacramento
    Scanner testScanner = new Scanner(System.in);
    AirlineOptimizationFrontendInterface testFrontend = new AirlineOptimizationFrontend(testScanner, testBackend, testReader);

    // running command loop and saving and testing output 
    testFrontend.runCommandLoop();
    String testOutput = uiTester.checkOutput();
    testScanner.close();

    assertTrue(testOutput.contains("your one way trip is: Madison -> Paris -> Berlin"));
 

    //Scanner testScanner2 = new Scanner(System.in);
    //AirlineOptimizationFrontendInterface testFrontend2 = new AirlineOptimizationFrontend(testScanner2, testBackend, testReader);
    //TextUITester uiTester2 = new TextUITester("L\nTestMap.txt\nO\nBoston\nSacramento\nQ\n"); // inputting map and outputting one way trip from Boston to Sacramento 
    
    //testFrontend2.runCommandLoop();
    //testOutput = uiTester2.checkOutput();
    //testScanner2.close();

    assertTrue(testOutput.contains("your one way trip is: Boston -> Berlin -> Sacramento"));

  }

  /**
   * Testing frontend implementation of round trip and multi city trip used in integration tester 2 
   */
  @Test
  public void testCodeReviewOfFrontendDeveloper2(){
    
    // initializing required role objects
    MapReaderInterface testReader = new MapReaderDW();
    DijkstraShortestPathInterface<AirportInterface, Integer> testMap = new DijkstraShortestPathAE<AirportInterface, Integer>();
    AirlineOptimizationBackendInterface testBackend = new AirlineOptimizationBackendBD(testReader, testMap);
    TextUITester uiTester = new TextUITester("L\nTestMap.txt\nR\nParis\nMadison\nM\nLondon\nBoston\nSacramento\nQ\n"); // inputting map and outputting round trip from Paris to Madison and then multicity from London to Sacramento with a stop at Boston
    Scanner testScanner = new Scanner(System.in);
    AirlineOptimizationFrontendInterface testFrontend = new AirlineOptimizationFrontend(testScanner, testBackend, testReader);

    // running command loop and saving and testing output
    testFrontend.runCommandLoop();
    String testOutput = uiTester.checkOutput();
    testScanner.close();
    
    assertTrue(testOutput.contains("your round trip is: Paris -> Tokyo -> Madison -> Paris"));
    assertTrue(testOutput.contains("your multi city trip is: London -> Boston -> Berlin -> Sacramento"));

  }
	
	
}

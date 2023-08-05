// --== CS400 File Header Information ==--
// Name: Sampreeth Immidisetty
// Email: immidisetty@wisc.edu
// Group and Team: AI Blue
// Group TA: Rachit Tibdewal
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Backend class for taking in frontend inputs and processing using DW and AE
 * @author sampreethimmidisetty
 *
 */
public class AirlineOptimizationBackendBD implements AirlineOptimizationBackendInterface {

  // initializing DW and AE objects
  protected int totalDistance = 0;
  private MapReaderInterface mapReader;
  private DijkstraShortestPathInterface<AirportInterface, Integer> map;

  /**
   * Constructor for initializing DW and AE objects
   */
  public AirlineOptimizationBackendBD(MapReaderInterface reader,
      DijkstraShortestPathInterface<AirportInterface, Integer> path) {
    mapReader = reader;
    map = path;
  }


  /**
   * Loads the map by inserting the airports and calling helper method
   * 
   * @param filename - name of file to read data from
   * @throws FileNotFoundException when filename is invalid
   */
  @Override
  public void loadMap(String filename) throws FileNotFoundException {

    List<AirportInterface> airports = mapReader.readAirportsFromFile(filename);
    addAirports(airports); // calling helper method

  }

  /**
   * Helper method to add airports to map and add all distances
   * @param airports
   */
  private void addAirports(List<AirportInterface> airports) {

    int weight;
    AirportInterface adjacent;

    // inserting each airport into map and adding all its surrounding weights as distances to total distance
    for (AirportInterface airport : airports) {
      map.insertNode(airport);
    }
//      for (Integer weight : airport.getEdgeWeights()) {
//        totalDistance += weight;
//      }

    for(AirportInterface airport: airports) {      
      for(int i = 0; i < airport.getAdjacentAirports().size(); i++) {
        weight = airport.getEdgeWeights().get(i);
        adjacent = airport.getAdjacentAirports().get(i);
        map.insertEdge(airport, adjacent, weight);
        totalDistance += weight;
      }
    }
  }



  /**
   * Returns path for one way trip with given parameters
   *
   * @param curr - initial airport
   * @param dest - destination airport
   * @return shortest path between curr and dest
   */
  @Override
  public List<AirportInterface> oneWayTrip(String curr, String dest) {
    AirportInterface currAirport = null;
    AirportInterface destAirport = null;
    
    for(AirportInterface airport: map.getAllNodes()) {
      if(airport.getCityName().equals(curr)) {
        currAirport = airport;
      }
      if(airport.getCityName().equals(dest)) {
        destAirport = airport;
      }
    }
    

    List<AirportInterface> path = map.shortestPathData(currAirport, destAirport);
    return path;
  }

  /**
   * Returns path for round trip with given parameters
   *
   * @param curr - initial airport
   * @param dest - destination airport
   * @return shortest path between curr and dest and back to curr
   */
  @Override
  public List<AirportInterface> roundTrip(String curr, String dest) {
    AirportInterface currAirport = null;
    AirportInterface destAirport = null;
    
    for(AirportInterface airport: map.getAllNodes()) {
      if(airport.getCityName().equals(curr)) {
        currAirport = airport;
      }
      if(airport.getCityName().equals(dest)) {
        destAirport = airport;
      }
    }
    
    ArrayList<AirportInterface> trip = new ArrayList<AirportInterface>();
    trip.add(currAirport);
    trip.add(destAirport);

    return map.getRoundTripPath(trip);
  }

  /**
   * Returns path for multicity trip with given parameters
   * 
   * @param list - list of airports in the order of (initial, stop, destination)
   * @return shortest path between initial and desintation including stop
   */
  @Override
  public List<AirportInterface> multiCityTrip(List<String> list) {

	  
    // creating airport objects for initial, stop and destination
    AirportInterface originAirport = null;
    AirportInterface stopAirport = null;
    AirportInterface destinationAirport = null;
    
    
    for(AirportInterface airport: map.getAllNodes()) {
      if(airport.getCityName().equals(list.get(0))) {
        originAirport = airport;
      }
      if(airport.getCityName().equals(list.get(1))) {
        stopAirport = airport;
      }
      if(airport.getCityName().equals(list.get(2))) {
        destinationAirport = airport;
      }
    }
    

    // creating airport list to pass to AE 
    ArrayList<AirportInterface> airportList = new ArrayList<AirportInterface>();
    airportList.add(originAirport);
    airportList.add(stopAirport);
    airportList.add(destinationAirport);

    return map.getPathWithNodeVisit(airportList);

  }

  /**
   * Displays all the cities on the map
   * 
   * @return list containing all the airports
   */
  @Override
  public List<AirportInterface> displayCities() {
    return map.getAllNodes();
  }

  /**
   * Displays statistics of the map
   * 
   * @return text containing the number of airports and average distance of all the map's paths
   */
  @Override
  public String getStatistics() {
    int averageDistance = 0;
    // avoiding divide by 0 error
    if (map.getNodeCount() != 0) {
      averageDistance = totalDistance / map.getNodeCount();
    }

    // creating string of statistics output
    String str = "Airport Map Contains:\n" + "      " + map.getNodeCount() + " airports\n"
            + "       " + averageDistance + " average travel distance";
    return str;

  }


}

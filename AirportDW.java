import java.util.ArrayList;
import java.util.List;

/**
 * This class models an airport used in creating a map of airports in the
 * Airline Optimization App
 * 
 * @author Matthew Gehrt
 */
public class AirportDW implements AirportInterface {

  private String cityName;
  private List<AirportInterface> adjAirports = new ArrayList<AirportInterface>();
  private List<Integer> edgeWeights = new ArrayList<Integer>();;

  /**
   * Constructor for each airport
   * 
   * @param cityName - the name of the city the airport is in
   */
  public AirportDW(String cityName) {
    this.cityName = cityName;
  }

  /**
   * Gets the name of the city the airport is in
   * 
   * @return the name of the airport's city
   */
  @Override
  public String getCityName() {
    return cityName;
  }

  /**
   * Gets all the airports that can be traveled to from this airport
   * 
   * @return a list of airports that can be traveled to
   */
  @Override
  public List<AirportInterface> getAdjacentAirports() {
    return adjAirports;
  }

  /**
   * Gets the mileage weights of all the possible flights to each destination
   * airport
   * 
   * @return a list of mileage weights to each destination airport
   */
  @Override
  public List<Integer> getEdgeWeights() {
    return edgeWeights;
  }

  /**
   * Adds an airport to the adjacent airports list, and adds a mileage weight to
   * the edgeWeights list to the same corresponding index in each list
   * 
   * @param airport - the destination airport of a certain path
   * @param mileage - the mile distance between the current and destination
   *                airports
   */
  @Override
  public void addDestinationAirport(AirportInterface airport, Integer mileage) {
    adjAirports.add(airport);
    edgeWeights.add(mileage);
  }

}

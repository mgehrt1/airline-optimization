import java.util.List;

public interface AirportInterface {
    // public AirportInterface(String cityName);
    public String getCityName();

    public List<AirportInterface> getAdjacentAirports();

    public List<Integer> getEdgeWeights();

    public void addDestinationAirport(AirportInterface airport, Integer mileage);
}

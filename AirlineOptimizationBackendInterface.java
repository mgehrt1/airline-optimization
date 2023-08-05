import java.io.FileNotFoundException;
import java.util.List;

public interface AirlineOptimizationBackendInterface {
	// public AirlineOptimizationBackendInterface(mapReaderInterface reader, DijkstraShortestPathInterface path);
	public void loadMap(String filename) throws FileNotFoundException;
	public List<AirportInterface> oneWayTrip(String curr, String dest);
	public List<AirportInterface> roundTrip(String curr, String dest);
	public List<AirportInterface> multiCityTrip(List<String> list);
	public List<AirportInterface> displayCities();
	public String getStatistics();
}


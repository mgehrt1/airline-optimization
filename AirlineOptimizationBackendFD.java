import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Placeholder class for the backend AirlineOptimizationBackend class 
 * @author prane
 *
 */
public class AirlineOptimizationBackendFD implements AirlineOptimizationBackendInterface{
	
	MapReaderInterface reader;
	DijkstraShortestPathInterface path;
	
	public AirlineOptimizationBackendFD(MapReaderInterface reader, DijkstraShortestPathInterface path) {
		this.reader = reader;
		this.path = path; 
	}

	@Override
	public void loadMap(String filename) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AirportInterface> oneWayTrip(String curr, String dest) {
		List<AirportInterface> airports = new ArrayList<>();
	    airports.add(new AirportFD("Airport 1"));
	    airports.add(new AirportFD("Airport 2"));
	    airports.add(new AirportFD("Airport 3"));
	    return airports;
	}

	@Override
	public List<AirportInterface> roundTrip(String curr, String dest) {
		List<AirportInterface> airports = new ArrayList<>();
		airports.add(new AirportFD("Airport 1"));
	    airports.add(new AirportFD("Airport 2"));
	    airports.add(new AirportFD("Airport 3"));
		airports.add(new AirportFD("Airport 1"));
	    return airports;
	}

	@Override
	public List<AirportInterface> multiCityTrip(List<String> list) {
		List<AirportInterface> airports = new ArrayList<>();
		airports.add(new AirportFD("Airport 1"));
	    airports.add(new AirportFD("Airport 2"));
	    airports.add(new AirportFD("Airport 3"));
		airports.add(new AirportFD("Airport 1"));
	    return airports;
	}

	@Override
	public List<AirportInterface> displayCities() {
		List<AirportInterface> airports = new ArrayList<>();
	    airports.add(new AirportFD("Airport 1"));
	    airports.add(new AirportFD("Airport 2"));
	    airports.add(new AirportFD("Airport 3"));
	    return airports;
	}

	public String getStatistics() {
		// TODO Auto-generated method stub
		String statistics = "5 total airports, 300 miles average distance";
		return statistics;
	}

}

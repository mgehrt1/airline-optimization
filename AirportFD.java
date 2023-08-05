import java.util.List;

/**
 * Placeholder class for the Data Wrangler's Airport class
 * @author prane
 *
 */
public class AirportFD implements AirportInterface{
	
	private String name; 
	
	AirportFD(String name) {
		this.name = name;
	}
	
	@Override
	public String getCityName() {
		return this.name;
	}

	@Override
	public List<AirportInterface> getAdjacentAirports() {
		return null;
	}

	@Override
	public List<Integer> getEdgeWeights() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addDestinationAirport(AirportInterface airport, Integer mileage) {
		// TODO Auto-generated method stub
		
	}

}

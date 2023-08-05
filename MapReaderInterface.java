import java.io.FileNotFoundException;
import java.util.List;

public interface MapReaderInterface {
    // public MapReaderInterface();
    public List<AirportInterface> readAirportsFromFile(String filename) throws FileNotFoundException;
}

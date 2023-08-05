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

public class MapReaderBD implements MapReaderInterface{
  
  public MapReaderBD() {
    
  }

  @Override
  public List<AirportInterface> readAirportsFromFile(String filename) throws FileNotFoundException {
    // TODO Auto-generated method stub
    ArrayList<AirportInterface> testList = new ArrayList<AirportInterface>();
    
    AirportInterface airport1 = new AirportBD("airport 1");
    testList.add(airport1);
    
    AirportInterface airport2 = new AirportBD("airport 2");
    testList.add(airport2);
    
    AirportInterface airport3 = new AirportBD("airport 3");
    testList.add(airport3);
    
    AirportInterface airport4 = new AirportBD("airport 4");
    testList.add(airport4);
    
    return testList;
  }
  
  

}

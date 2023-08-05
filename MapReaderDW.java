import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class Allows the user to create a map of airports from a file
 * 
 * @author Matthew Gehrt
 */
public class MapReaderDW implements MapReaderInterface {

  /**
   * Reads from a file and turns it into a map of airports
   * 
   * @param filename - file to read from to create the map of airports
   * @return a list of airports with all the proper connections based on the file
   *         passed
   * @throws FileNotFoundException - if filename does not exist
   */
  @Override
  public List<AirportInterface> readAirportsFromFile(String filename) throws FileNotFoundException {
    File file;
    try {
      file = new File(filename);
    } catch (Exception e) {
      throw new FileNotFoundException("Invalid File Name");
    }
    Scanner scnr = new Scanner(file);

    String currLine;
    String locationName;
    String destinationName;
    AirportInterface destinationAirport;
    int locationIndex;
    int destinationIndex;
    int pathWeight;
    List<String> airportNames = new ArrayList<String>();
    List<AirportInterface> airports = new ArrayList<AirportInterface>();

    // create an initial instance of all airports
    while (scnr.hasNextLine()) {
      currLine = scnr.nextLine();

      // put all "split" methods in a try catch to make sure the file is in DOT format
      try {
        // get the name of location and destination airports of the current line
        locationName = currLine.split("->")[0].trim();
        destinationName = currLine.split("->")[1].split("\\[")[0].trim();
      } catch (ArrayIndexOutOfBoundsException e) {
        scnr.close();
        return null;
      }

      // if either the location or destination airports have not been instantiated,
      // add them to the list of names and airports
      if (!airportNames.contains(locationName)) {
        airportNames.add(locationName);
        airports.add(new AirportDW(locationName));
      }
      if (!airportNames.contains(destinationName)) {
        airportNames.add(destinationName);
        airports.add(new AirportDW(destinationName));
      }
    }
    scnr.close();

    // run through the file again and add all connections
    scnr = new Scanner(file);
    while (scnr.hasNextLine()) {
      currLine = scnr.nextLine();

      // put all "split" methods in a try catch to make sure the file is in DOT format
      try {
        // get info about the current city
        locationName = currLine.split("->")[0].trim();
        locationIndex = airportNames.indexOf(locationName);

        // get info about the destination city
        destinationName = currLine.split("->")[1].split("\\[")[0].trim();
        destinationIndex = airportNames.indexOf(destinationName);
        destinationAirport = airports.get(destinationIndex);

        // get the mileage weight of the path
        pathWeight = Integer.parseInt(currLine.split("=")[1].split("\\]")[0].trim());
      } catch (ArrayIndexOutOfBoundsException e) {
        scnr.close();
        return null;
      }

      // add the destination airport and weight to the current airport
      airports.get(locationIndex).addDestinationAirport(destinationAirport, pathWeight);

    }
    scnr.close();

    return airports; // return the list of airports with all connections added
  }

}

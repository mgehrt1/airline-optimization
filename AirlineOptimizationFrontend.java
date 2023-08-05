// --== CS400 File Header Information ==--
// Name: Praneet Gudladana
// Email: gudladana@wisc.edu
// Group and Team: AI Blue
// Group TA: Rachit Tibdewal
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * class which contains frontend methods for the class
 * @author praneet 
 *
 */
public class AirlineOptimizationFrontend implements AirlineOptimizationFrontendInterface {

	private Scanner userInput;
	private AirlineOptimizationBackendInterface backend;
	private MapReaderInterface reader;

	/**
	 * Constructor for the frontend of the Airline Optimization App
	 * 
	 * @param userInput - input from the user 
	 * @param backend - the backend component of the Airline Optimization App
	 */
	public AirlineOptimizationFrontend(Scanner userInput, AirlineOptimizationBackendInterface backend,
			MapReaderInterface reader) {
		this.userInput = userInput;
		this.backend = backend;
		this.reader = reader;
	}

	/**
	 * Helper method to display a a horizontal rule.
	 */
	private void horizontalRule() {
		System.out.println("-------------------------------------------------------------------------------");
	}

	/**
	 * method to run the command loop
	 */
	@Override
	public void runCommandLoop() {
		// TODO Auto-generated method stub
		// display initial message upon launching the app
		horizontalRule();
		System.out.println("Welcome to the Airplane Optimization Application!");
		horizontalRule();

		char command = '\0';
		while (command != 'Q') { // main loop continues until user chooses to quit
			command = this.mainMenuPrompt();
			switch (command) {
			case 'L': // Load a business
				loadMapCommand();
				break;
			case 'O': // Add employee(s)
				getOneWayTripCommand();
				break;
			case 'R': // Remove Employee(s)
				getRoundTripCommand();
				break;
			case 'M': // Change Job
				getMultiCityTripCommand();
				break;
			case 'C': // Change Salary
				displayCitiesCommand();
				break;
			case 'D': // Display Employee
				displayStatisticsCommand();
				break;
			case 'Q': // Quit the program
				// do nothing, containing loop condition will fail
				horizontalRule();
				System.out.println("Thank you for using the Airplane Optimization Application!");
				horizontalRule();

				break;
			default:
				System.out.println(
						"Didn't recognize that command.  Please type one of the letters presented "
								+ "within []s to identify the command you would like to choose.");
				break;
			}
		}
	}

	/**
	 * method to print out the main menu 
	 */
	@Override
	public char mainMenuPrompt() {
		// display menu of choices
		System.out.println("Choose a command by entering in the corresponding letter in brackets:");
		System.out.println("    [L]oad Map");
		System.out.println("    Find [O]ne-way trip");
		System.out.println("    Find [R]ound-trip");
		System.out.println("    Find [M]ulti-city trip");
		System.out.println("    Display [C]ities");
		System.out.println("    [D]isplay Statistics");
		System.out.println("    [Q]uit");

		// read in user's choice, and trim away any leading or trailing whitespace
		System.out.print("Choose command: ");
		String input = userInput.nextLine().trim();
		if (input.length() == 0) // if user's choice is blank, return null character
			return '\0';
		// otherwise, return an uppercase version of the first character in input
		return Character.toUpperCase(input.charAt(0));
	}

	/**
	 * method which loads a map file
	 */
	@Override
	public void loadMapCommand() {
		// prompt to enter file name
		System.out.print("Enter the Airline's file name to load: ");
		String filename = userInput.nextLine().trim();
		try {
			backend.loadMap(filename); // calling backend method
			System.out.println("Airline data loaded successfully!");
		} catch (FileNotFoundException e) { // catching exception
			System.out.println("Error: Could not find or load file: " + filename);
		}

	}

	/**
	 * method to get a one way trip between two airports
	 */
	@Override
	public void getOneWayTripCommand() {

		System.out.println("Enter the name of the first city to depart from: "); // prompt for city
		String originCityString = userInput.nextLine().trim();


		System.out.println("Enter the name of the Second city to arrive at: "); // prompt for city
		String DestinationCityString = userInput.nextLine().trim();

		// calling the backend functions
		List<AirportInterface> airports = backend.oneWayTrip(originCityString, DestinationCityString);
		String output = "";
		int i= 0;
		// checking if there is a path between cities
		if(airports == null) {
			System.out.println("No trip found");
			return;
		}
		while(!airports.isEmpty() && i < airports.size()) { // formatting the output string
			if(i != airports.size() -1) {
				output = output + airports.get(i).getCityName() + " -> ";
			} else {
				output = output + airports.get(i).getCityName();
			}
			i++;
		}

		if(airports != null) {
			System.out.println("your one way trip is: " + output); // outputting the string
		} 

	}

	/**
	 * method to get a round drip between two cities
	 */
	@Override
	public void getRoundTripCommand() {

		System.out.println("Enter the name of the first city to depart from: "); // prompt for city
		String originCityString = userInput.nextLine().trim();


		System.out.println("Enter the name of the Second city to arrive at: "); // prompt for city
		String DestinationCityString = userInput.nextLine().trim();

		// calling the backend function to create a round trip
		List<AirportInterface> airports = backend.roundTrip(originCityString, DestinationCityString);

		// checking if there is a path between cities
		if(airports == null) {
			System.out.println("No trip found");
			return;
		}

		String output = "";
		int i= 0;
		// loop to print out the cities in a formatted string 
		while(!airports.isEmpty() && i < airports.size()) {
			if(i != airports.size() -1) {
				output = output + airports.get(i).getCityName() + " -> ";
			} else {
				output = output + airports.get(i).getCityName();
			}
			i++;
		}

		if(airports != null) { // outputting the trip
			System.out.println("your round trip is: " + output);
		} 

	}

	/**
	 * method to get a trip between two cities with a stop
	 */
	@Override
	public void getMultiCityTripCommand() {


		System.out.println("Enter the name of the city to depart from: "); // prompt for city
		String originCityString = userInput.nextLine().trim();


		System.out.println("Enter the name of the city to stop at: "); // prompt for city
		String stopString = userInput.nextLine().trim();

		System.out.println("Enter the name of the  city to arrive at: "); // prompt for city
		String  DestinationCityString= userInput.nextLine().trim();

		List<String> trip = new ArrayList<String>();
		trip.add(originCityString);
		trip.add(stopString);
		trip.add(DestinationCityString);

		// calling the backend function to make a multi city trip
		List<AirportInterface> airports = backend.multiCityTrip(trip);

		// checking if there is a path between cities
		if(airports == null) {
			System.out.println("No trip found");
			return;
		}
		
		String output = "";
		int i= 0;
		while(!airports.isEmpty() && i < airports.size()) {
			if(i != airports.size() -1) {
				output = output + airports.get(i).getCityName() + " -> ";
			} else {
				output = output + airports.get(i).getCityName();
			}
			i++;
		}
		if(airports != null) { // outputting the trip
			System.out.println("your multi city trip is: " + output);
		} 
	}

	/**
	 * method to display all cities in the map
	 */
	@Override
	public void displayCitiesCommand() {		
		// calling backend method
		List<AirportInterface> airports = backend.displayCities();
		String output = "";
		int i= 0;
		// using a loop to output the strings
		while(!airports.isEmpty() && i < airports.size()) {
			if(i != airports.size() -1) {
				output = output + airports.get(i).getCityName() + ", ";
			} else {
				output = output + airports.get(i).getCityName();
			}
			i++;
		}
		System.out.println("Cities: " + output);
	}



	/**
	 * method to display statistics about the map
	 */
	@Override
	public void displayStatisticsCommand() {
		// print map statistics retrieved from the backend		
		String output = backend.getStatistics();
		System.out.println("Statistics: " + output);



	}

}


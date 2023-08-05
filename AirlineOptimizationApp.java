import java.util.Scanner;

/**
 * Main entry point for starting and running the Employee App.
 * 
 * @author Algorithm Engineer, Will Fitzgerald
 */
public class AirlineOptimizationApp {
    public static void main(String[] args) {
        // Use data wrangler's code to load employee data
        MapReaderInterface reader = new MapReaderDW();

        // Use algorithm engineer's code to store and search for data
        DijkstraShortestPathInterface<AirportInterface, Integer> graph;
        graph = new DijkstraShortestPathAE<AirportInterface, Integer>();

        // Use the backend developer's code to manage all app specific processing
        AirlineOptimizationBackendInterface backend = new AirlineOptimizationBackendBD(reader, graph);

        // Use the frontend developer's code to drive the text-base user interface
        Scanner scanner = new Scanner(System.in);

        AirlineOptimizationFrontendInterface frontend = new AirlineOptimizationFrontend(scanner, backend, reader);

        frontend.runCommandLoop();
    }
}

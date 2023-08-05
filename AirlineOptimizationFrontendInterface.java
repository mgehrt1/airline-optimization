public interface AirlineOptimizationFrontendInterface {
    // public AirlineOptimizationFrontendInterfaceXX(Scanner userInput,
    // AirlineOptimizationBackendInterface backend, MapReaderInterface reader);
    public void runCommandLoop();

    public char mainMenuPrompt();

    public void loadMapCommand();

    public void getOneWayTripCommand();

    public void getRoundTripCommand();

    public void getMultiCityTripCommand();

    public void displayCitiesCommand();

    public void displayStatisticsCommand();
}

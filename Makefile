run: AirlineOptimizationApp.class
	java AirlineOptimizationApp.java

AirlineOptimizationApp.class: AirlineOptimizationApp.java
	javac AirlineOptimizationApp.java

runTests: runAlgorithmEngineerTests runDataWranglerTests runBackendDeveloperTests runFrontendDeveloperTests

runAlgorithmEngineerTests: AlgorithmEngineerTests.class
	java -jar junit5.jar -cp . --select-class=AlgorithmEngineerTests

AlgorithmEngineerTests.class: AlgorithmEngineerTests.java DijkstraShortestPathAE.class MapReaderDW.class
	javac -cp .:junit5.jar AlgorithmEngineerTests.java

DijkstraShortestPathAE.class: DijkstraShortestPathInterface.class DijkstraShortestPathAE.java DijkstraGraph.class
	javac DijkstraShortestPathAE.java

DijkstraGraph.class: BaseGraph.class DijkstraGraph.java GraphADT.class
	javac -cp .:junit5.jar DijkstraGraph.java

BaseGraph.class: BaseGraph.java
	javac BaseGraph.java

GraphADT.class: GraphADT.java
	javac GraphADT.java

DijkstraShortestPathInterface.class: DijkstraShortestPathInterface.java
	javac DijkstraShortestPathInterface.java

runDataWranglerTests: DataWranglerTests.class
	java -jar junit5.jar -cp . --select-class=DataWranglerTests
	
DataWranglerTests.class: DataWranglerTests.java AirportDW.class MapReaderDW.class DijkstraShortestPathAE.class
	javac -cp .:junit5.jar DataWranglerTests.java

AirportDW.class: AirportDW.java AirportInterface.class
	javac AirportDW.java

MapReaderDW.class: MapReaderDW.java MapReaderInterface.class
	javac MapReaderDW.java

AirportInterface.class: AirportInterface.java
	javac AirportInterface.java

MapReaderInterface.class: MapReaderInterface.java
	javac MapReaderInterface.java

runBackendDeveloperTests: BackendDeveloperTests.class
	java -jar junit5.jar -cp . --select-class=BackendDeveloperTests

BackendDeveloperTests.class: BackendDeveloperTests.java AirportBD.class AirlineOptimizationBackendBD.class MapReaderBD.class DijkstraShortestPathBD.class 
	javac -cp .:junit5.jar BackendDeveloperTests.java	

AirportBD.class: AirportBD.java AirportInterface.java
	javac AirportBD.java AirportInterface.java

AirlineOptimizationBackendBD.class: AirlineOptimizationBackendBD.java AirlineOptimizationBackendInterface.java
	javac AirlineOptimizationBackendBD.java AirlineOptimizationBackendInterface.java

MapReaderBD.class: MapReaderBD.java MapReaderInterface.java
	javac MapReaderBD.java MapReaderInterface.java 	

DijkstraShortestPathBD.class: DijkstraShortestPathBD.java DijkstraShortestPathInterface.java 
	javac DijkstraShortestPathBD.java DijkstraShortestPathInterface.java

# target to run tests
runFrontendDeveloperTests: FrontendDeveloperTests.class
	java -jar junit5.jar -cp . --select-class=FrontendDeveloperTests

# compiles tests and placeholders
FrontendDeveloperTests.class: FrontendDeveloperTests.java AirlineOptimizationFrontend.class AirlineOptimizationBackendFD.class MapReaderFD.class AirportFD.class DijkstraShortestPathFD.class
	javac -cp .:junit5.jar FrontendDeveloperTests.java

AirlineOptimizationFrontend.class: AirlineOptimizationFrontend.java AirlineOptimizationFrontendInterface.java
	javac AirlineOptimizationFrontend.java AirlineOptimizationFrontendInterface.java

AirlineOptimizationBackendFD.class: AirlineOptimizationBackendFD.java AirlineOptimizationBackendInterface.java
	javac AirlineOptimizationBackendFD.java AirlineOptimizationBackendInterface.java

MapReaderFD.class: MapReaderFD.java MapReaderInterface.java
	javac MapReaderFD.java MapReaderInterface.java

AirportFD.class: AirportFD.java AirportInterface.java
	javac AirportFD.java AirportInterface.java

DijkstraShortestPathFD.class: DijkstraShortestPathFD.java
	javac DijkstraShortestPathFD.java

clean:
	rm -f *.class
	rm -f *~


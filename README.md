# Airline Optimization

This project allows for a user to get the shortest (least mileage) travel plan to get from their departure city to their arrival destination. We will use Dijkstra’s shortest path algorithm to do so, using a graph to represent the cities with airports to go to. Each Node on a graph will be an airport, and will have pointers that have a mileage weight associated with them that point to another airport. The App will have the capability to find the shortest one way trip route, shortest round trip route, and shortest route with a certain airport stop route along the way from the initial airport to the destination.

## How to use

### 1) Clone the repository (Must have git installed)

```
git clone https://github.com/mgehrt1/airline-optimization.git
```

### 2) Must be able to run make files.

Linux: Should be able to run by default

Windows: https://stackoverflow.com/questions/2532234/how-to-run-a-makefile-in-windows

### 3) Run the project

```
make run
```

### 4) Load a map

You can use TestMap.txt for an example file

Select the "L" command, and enter TestMap.txt

### 5) Use the app!

Run any commands and try different features using the CLI

### 4) (Optional) Run jUnit tests

```
make runTests
```

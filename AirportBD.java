// --== CS400 File Header Information ==--
// Name: Sampreeth Immidisetty
// Email: immidisetty@wisc.edu
// Group and Team: AI Blue
// Group TA: Rachit Tibdewal
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.List;

public class AirportBD implements AirportInterface{
  
  private String name;
  
  AirportBD(String name){
    this.name = name;
  }

  @Override
  public String getCityName() {
    // TODO Auto-generated method stub
    return name;
  }

  @Override
  public List<AirportInterface> getAdjacentAirports() {
    // TODO Auto-generated method stub
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

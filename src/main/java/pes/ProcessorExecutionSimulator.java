package pes;

import org.apache.commons.cli.ParseException;
import pes.simulation.Simulation;

import java.io.IOException;

public class ProcessorExecutionSimulator {
  public static void main(String[] args) {
    try {
      SimulationArguments arguments = new SimulationArguments(args);
      SimulationConfiguration configuration = new SimulationConfiguration(arguments);
      Simulation simulation = new Simulation(configuration);
      simulation.run();
      simulation.writeOutput();
    } catch (IOException | ParseException exception) {
      System.out.println(exception.getMessage());
    }
  }
}

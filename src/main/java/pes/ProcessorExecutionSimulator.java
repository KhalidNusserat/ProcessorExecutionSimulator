package pes;

import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;
import pes.input.SimulationArguments;
import pes.input.SimulationConfiguration;
import pes.simulation.Simulation;

import java.io.IOException;
import java.util.Objects;

public class ProcessorExecutionSimulator {
  public static void main(String[] args) {
    try {
      SimulationArguments arguments = new SimulationArguments(args);
      SimulationConfiguration configuration = new SimulationConfiguration(arguments);

      Simulation simulation = new Simulation(configuration);
      simulation.run();

      simulation.writeOutput();
    } catch (UnrecognizedOptionException unrecognizedOptionException) {
      if (unrecognizedOptionException.getOption().equals("--help")) {
        System.out.println("--help must be called alone");
      }
    } catch (ParseException | IOException exception) {
      System.out.println(exception.getMessage());
    }
  }
}

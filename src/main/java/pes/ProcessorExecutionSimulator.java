package pes;

import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;
import pes.input.SimulationConfiguration;
import pes.simulation.Simulation;

import java.io.IOException;
import java.util.Objects;

public class ProcessorExecutionSimulator {
  public static void main(String[] args) {
    try {
      SimulationConfiguration simulationConfiguration = new SimulationConfiguration();
      if (args.length == 1 && Objects.equals(args[0], "--help")) {
        simulationConfiguration.printHelp();
        System.exit(0);
      }
      simulationConfiguration.parse(args);

      Simulation simulation = new Simulation(simulationConfiguration);
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

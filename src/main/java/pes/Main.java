package pes;

import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;
import pes.input.CommandLineInterface;
import pes.input.TasksReader;
import pes.output.OutputFile;
import pes.output.formatters.Formatter;
import pes.output.summarisers.Summariser;
import pes.output.writers.RecordWriter;
import pes.recorders.Recorder;
import pes.simulation.Simulation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Main {
  public static void main(String[] args) {
    try {
      CommandLineInterface commandLineInterface = new CommandLineInterface();
      if (args.length == 1 && Objects.equals(args[0], "--help")) {
        commandLineInterface.printHelp();
        System.exit(0);
      }
      TasksReader tasksReader = new TasksReader();
      commandLineInterface.parse(args);
      File inputFile = new File(commandLineInterface.getInputPath());
      if (!inputFile.exists() || inputFile.isDirectory()) {
        System.out.println("Invalid input file specified.");
        System.exit(-1);
      }

      Simulation simulation = new Simulation();
      simulation.createProcessors(commandLineInterface.getNumberOfProcessors());
      simulation.setScheduler(commandLineInterface.getScheduler());
      simulation.addTasks(tasksReader.readTasksFromFile(commandLineInterface.getInputPath()));
      simulation.run();

      ArrayList<Recorder> recorders = simulation.getRecorders();
      Formatter formatter = commandLineInterface.getFormatter();
      ArrayList<OutputFile> outputFiles =
          (ArrayList<OutputFile>) formatter.formatAllRecords(recorders);
      outputFiles.forEach(
          outputFile -> outputFile.setOutputDirectory(commandLineInterface.getOutputDirectory()));

      RecordWriter writer = commandLineInterface.getWriter();
      writer.writeAll(outputFiles);

      Summariser summariser = new Summariser();
      OutputFile summaryOutputFile = summariser.summarise(simulation.getRecorders());
      summaryOutputFile.setOutputDirectory(commandLineInterface.getOutputDirectory());
      writer.write(summaryOutputFile);
    } catch (UnrecognizedOptionException unrecognizedOptionException) {
      if (unrecognizedOptionException.getOption().equals("--help")) {
        System.out.println("--help must be called alone");
      }
    } catch (ParseException | IOException exception) {
      System.out.println(exception.getMessage());
    }
  }
}

package pes;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

public class SimulationConfiguration {

  private String inputPath;

  private String schedulerType = "srtf";

  private String formatterType = "concise";

  private String writerType = "csv";

  private String outputDirectory = "./output";

  private int numberOfProcessors = 1;

  public SimulationConfiguration(SimulationArguments arguments) throws ParseException {
    CommandLine commandLine = arguments.getCommandLine();
    ;
    if (commandLine.hasOption("input")) {
      inputPath = commandLine.getOptionValue("input");
    }
    if (commandLine.hasOption("output")) {
      outputDirectory = commandLine.getOptionValue("output");
    }
    if (commandLine.hasOption("scheduler")) {
      schedulerType = commandLine.getOptionValue("scheduler");
    }
    if (commandLine.hasOption("extension")) {
      writerType = commandLine.getOptionValue("extension");
    }
    if (commandLine.hasOption("format")) {
      formatterType = commandLine.getOptionValue("format");
    }
    if (commandLine.hasOption("processors")) {
      numberOfProcessors = Integer.parseInt(commandLine.getOptionValue("processors"));
    }
  }

  public String getInputPath() {
    return inputPath;
  }

  public String getSchedulerType() {
    return schedulerType;
  }

  public String getFormatterType() {
    return formatterType;
  }

  public String getWriterType() {
    return writerType;
  }

  public String getOutputDirectory() {
    return outputDirectory;
  }

  public int getNumberOfProcessors() {
    return numberOfProcessors;
  }
}

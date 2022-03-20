package pes;

import org.apache.commons.cli.*;

public class SimulationArguments {

  private final CommandLine commandLine;

  public SimulationArguments(String[] args) throws ParseException {
    Options options = new Options();
    options.addOption(
        Option.builder()
            .option("i")
            .longOpt("input")
            .desc("Set input file path")
            .hasArg(true)
            .numberOfArgs(1)
            .required()
            .argName("path")
            .build());
    options.addOption(
        Option.builder()
            .option("o")
            .longOpt("output")
            .desc("Set output directory path")
            .hasArg(true)
            .numberOfArgs(1)
            .argName("path")
            .build());
    options.addOption(
        Option.builder()
            .option("s")
            .longOpt("scheduler")
            .desc("Select scheduler")
            .hasArg(true)
            .numberOfArgs(1)
            .argName("scheduler")
            .build());
    options.addOption(
        Option.builder()
            .option("f")
            .longOpt("format")
            .desc("Set output format")
            .hasArg(true)
            .numberOfArgs(1)
            .argName("format")
            .build());
    options.addOption(
        Option.builder()
            .option("e")
            .longOpt("extension")
            .desc("Set output files extension")
            .hasArg(true)
            .numberOfArgs(1)
            .argName("extension")
            .build());
    options.addOption(
        Option.builder()
            .option("p")
            .longOpt("processors")
            .desc("Set number of processors")
            .hasArg(true)
            .numberOfArgs(1)
            .argName("numberOfProcessors")
            .build());
    try{
      CommandLineParser parser = new DefaultParser();
      commandLine = parser.parse(options, args);
    } catch (ParseException parseException) {
      HelpFormatter helpFormatter = new HelpFormatter();
      helpFormatter.printHelp("pes -i,--input <path> [OPTIONS]", options);
      throw parseException;
    }
  }

  public CommandLine getCommandLine() {
    return commandLine;
  }
}

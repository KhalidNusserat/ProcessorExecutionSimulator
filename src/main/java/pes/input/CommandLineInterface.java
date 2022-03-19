package pes.input;

import org.apache.commons.cli.*;
import pes.output.formatters.Formatter;
import pes.output.formatters.FormatterFactory;
import pes.output.formatters.concise.ConciseFormatter;
import pes.output.writers.CSVWriter;
import pes.output.writers.RecordWriter;
import pes.output.writers.RecordWriterFactory;
import pes.schedulers.Scheduler;
import pes.schedulers.SchedulerFactory;
import pes.schedulers.ShortestRemainingTimeFirst;

import java.util.ArrayList;

public class CommandLineInterface {
  private final Options options = new Options();

  private String inputPath;

  private Scheduler scheduler = new ShortestRemainingTimeFirst();

  private String outputPath = "./output";

  private Formatter formatter = new ConciseFormatter();

  private RecordWriter writer = new CSVWriter();

  private int numberOfProcessors = 1;

  private HelpFormatter helpFormatter = new HelpFormatter();

  public CommandLineInterface() {
    options.addOption(
            Option.builder()
                    .option("i")
                    .longOpt("input")
                    .desc("Set input file path")
                    .hasArg(true)
                    .numberOfArgs(1)
                    .required()
                    .argName("path")
                    .build()
    );
    options.addOption(
            Option.builder()
                    .option("o")
                    .longOpt("output")
                    .desc("Set output directory path")
                    .hasArg(true)
                    .numberOfArgs(1)
                    .argName("path")
                    .build()
    );
    options.addOption(
            Option.builder()
                    .option("s")
                    .longOpt("scheduler")
                    .desc("Select scheduler")
                    .hasArg(true)
                    .numberOfArgs(1)
                    .argName("scheduler")
                    .build()
    );
    options.addOption(
            Option.builder()
                    .option("f")
                    .longOpt("format")
                    .desc("Set output format")
                    .hasArg(true)
                    .numberOfArgs(1)
                    .argName("format")
                    .build()
    );
    options.addOption(
            Option.builder()
                    .option("e")
                    .longOpt("extension")
                    .desc("Set output files extension")
                    .hasArg(true)
                    .numberOfArgs(1)
                    .argName("extension")
                    .build()
    );
    options.addOption(
            Option.builder()
                    .option("p")
                    .longOpt("processors")
                    .desc("Set number of processors")
                    .hasArg(true)
                    .numberOfArgs(1)
                    .required()
                    .argName("numberOfProcessors")
                    .build()
    );
  }

  public void printHelp() {
    helpFormatter.printHelp(
            "pes -i, --input <path> -p, --processors <numberOfProcessors> [OPTIONS]", options);
  }

  public void parse(String[] args) throws ParseException {
    CommandLineParser parser = new DefaultParser();
    CommandLine commandLine = parser.parse(options, args);
    if (commandLine.hasOption("input")) {
      inputPath = commandLine.getOptionValue("input");
    }
    if (commandLine.hasOption("output")) {
      outputPath = commandLine.getOptionValue("output");
    }
    if (commandLine.hasOption("scheduler")) {
      SchedulerFactory factory = new SchedulerFactory();
      scheduler = factory.createScheduler(commandLine.getOptionValue("scheduler"));
    }
    if (commandLine.hasOption("extension")) {
      RecordWriterFactory factory = new RecordWriterFactory();
      writer = factory.createRecordWriter(commandLine.getOptionValue("extension"));
    }
    if (commandLine.hasOption("format")) {
      FormatterFactory factory = new FormatterFactory();
      formatter = factory.createRecordFormatter(commandLine.getOptionValue("format"));
    }
    if (commandLine.hasOption("processors")) {
      numberOfProcessors = Integer.parseInt(commandLine.getOptionValue("processors"));
    }
  }

  public String getInputPath() {
    return inputPath;
  }

  public Scheduler getScheduler() {
    return scheduler;
  }

  public String getOutputDirectory() {
    return outputPath;
  }

  public Formatter getFormatter() {
    return formatter;
  }

  public RecordWriter getWriter() {
    return writer;
  }

  public int getNumberOfProcessors() {
    return numberOfProcessors;
  }
}

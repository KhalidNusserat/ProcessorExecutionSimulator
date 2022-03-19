package pes.input;

import org.apache.commons.cli.*;
import pes.output.formatters.RecordFormatter;
import pes.output.formatters.RecordFormatterFactory;
import pes.output.formatters.concise.ConciseFormatter;
import pes.output.writers.CsvRecordWriter;
import pes.output.writers.RecordWriter;
import pes.output.writers.RecordWriterFactory;
import pes.schedulers.Scheduler;
import pes.schedulers.SchedulerFactory;
import pes.schedulers.ShortestRemainingTimeFirst;

public class CommandLineInterface {
  private final Options options = new Options();
  private String inputPath;
  private Scheduler scheduler = new ShortestRemainingTimeFirst();
  private String outputPath = ".";
  private RecordFormatter formatter = new ConciseFormatter();
  private RecordWriter writer = new CsvRecordWriter();
  private int numberOfProcessors = 1;

  public CommandLineInterface() {
    Option help = new Option("h", "help", false, "Display help");
    Option input = new Option("i", "input", true, "Set input path");
    input.setArgName("path");
    input.setRequired(true);
    Option scheduler = new Option("s", "scheduler", true, "Select scheduler");
    scheduler.setArgName("scheduler");
    Option output = new Option("o", "output", true, "Set output path");
    output.setArgName("path");
    Option formatter = new Option("f", "formatter", true, "Set output format");
    formatter.setArgName("format");
    Option extension = new Option("e", "extension", true, "Set output extension");
    extension.setArgName("extension");
    Option numberOfProcessors = new Option("p", "processors", true, "Set number of processors");
    numberOfProcessors.setArgName("numberOfProcessors");
    numberOfProcessors.setRequired(true);
    options.addOption(help);
    options.addOption(input);
    options.addOption(scheduler);
    options.addOption(formatter);
    options.addOption(output);
    options.addOption(extension);
    options.addOption(numberOfProcessors);
  }

  public void parse(String[] args) throws ParseException {
    CommandLineParser parser = new DefaultParser();
    CommandLine commandLine = parser.parse(options, args);
    if (commandLine.hasOption("help")) {
      HelpFormatter helpFormatter = new HelpFormatter();
      helpFormatter.printHelp(
          "pes <-i, --input <path>> <-p, --processors <numberOfProcessors>> [OPTIONS]", options);
    }
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
      RecordFormatterFactory factory = new RecordFormatterFactory();
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

  public String getOutputPath() {
    return outputPath;
  }

  public RecordFormatter getFormatter() {
    return formatter;
  }

  public RecordWriter getWriter() {
    return writer;
  }

  public int getNumberOfProcessors() {
    return numberOfProcessors;
  }
}

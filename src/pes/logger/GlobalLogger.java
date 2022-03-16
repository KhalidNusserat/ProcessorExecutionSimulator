package pes.logger;

import pes.Clock;
import pes.logger.state.Stateful;

import java.util.ArrayList;
import java.util.HashSet;

public class GlobalLogger {
  private final HashSet<Logger> loggers = new HashSet<>();

  private final Clock clock;

  public GlobalLogger(Clock clock) {
    this.clock = clock;
  }

  public void watch(Stateful stateful) {
    Logger logger = new Logger(stateful, clock);
    loggers.add(logger);
  }

  public void captureAll() {
    loggers.forEach(Logger::capture);
  }

  public ArrayList<Logger> getLoggersArray() {
    return new ArrayList<>(loggers);
  }
}

package pes.logger.state;

public class StateBuilder<E> {
  private final String type;

  private final String[] properties;

  public StateBuilder(String type, String... properties) {
    this.type = type;
    this.properties = properties;
  }

  public State getNewState(String... values) {
    return new State(properties, values, type);
  }
}

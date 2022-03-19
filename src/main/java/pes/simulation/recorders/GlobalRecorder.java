package pes.simulation.recorders;

import pes.state.Stateful;
import pes.state.StatefulType;

import java.util.ArrayList;
import java.util.HashSet;

public class GlobalRecorder {
  private final HashSet<Recorder> recorders = new HashSet<>();

  public void watch(Stateful statefulObject, StatefulType type) {
    recorders.add(new Recorder(type, statefulObject));
  }

  public void watchAll(ArrayList<Stateful> statefulObjects, StatefulType type) {
    for (Stateful statefulObject : statefulObjects) {
      watch(statefulObject, type);
    }
  }

  public void recordAll(int clockCycle) {
    recorders.forEach(recorder -> recorder.record(clockCycle));
  }

  public ArrayList<Recorder> getRecorders() {
    return new ArrayList<>(recorders);
  }
}

package pes.output.summarisers;

import pes.output.OutputFile;
import pes.simulation.processor.Processor;
import pes.simulation.recorders.Record;
import pes.simulation.recorders.Recorder;
import pes.simulation.task.Task;
import pes.simulation.task.TaskPriority;
import pes.state.StatefulType;

import java.util.ArrayList;

public class Summariser {
  public OutputFile summarise(ArrayList<Recorder> recorders) {
    OutputFile outputFile =
        new OutputFile(
            "Summary",
            "Total Idle Time",
            "Total Execution Time",
            "Total Waiting Time (LOW)",
            "Total Waiting Time (HIGH)",
            "Average Time Waiting (LOW)",
            "Average Time Waiting (HIGH)");
    outputFile.setFileName("summary");

    int totalIdleTime = 0;
    int totalExecutionTime = 0;
    int totalWaitingTimeHigh = 0;
    int totalWaitingTimeLow = 0;
    int numberOfHighPriorityTasks = 0;
    int numberOfLowPriorityTasks = 0;

    for (Recorder recorder : recorders) {
      if (recorder.getType().equals(StatefulType.TASK)) {
        Task task = (Task) recorder.getSubject();
        if (task.getPriority().equals(TaskPriority.LOW)) {
          numberOfLowPriorityTasks++;
        } else {
          numberOfHighPriorityTasks++;
        }
      }
      for (Record record : recorder) {
        totalExecutionTime = Math.max(totalExecutionTime, record.getEnd());
        if (recorder.getType().equals(StatefulType.PROCESSOR)) {
          Processor processState = (Processor) record.getState();
          if (processState.isIdle()) {
            totalIdleTime += record.getDuration();
          }
        } else {
          Task taskState = (Task) record.getState();
          if (taskState.getPriority().equals(TaskPriority.LOW)) {
            if (taskState.isCreated() && !taskState.isRunning() && !taskState.isDone()) {
              totalWaitingTimeLow += record.getDuration();
            }
          } else if (taskState.isCreated() && !taskState.isRunning() && !taskState.isDone()) {
            totalWaitingTimeHigh += record.getDuration();
          }
        }
      }
    }

    outputFile.append(totalIdleTime);
    outputFile.append(totalExecutionTime);
    outputFile.append(totalWaitingTimeLow);
    outputFile.append(totalWaitingTimeHigh);
    outputFile.append(1.f * totalWaitingTimeLow / numberOfLowPriorityTasks);
    outputFile.append(1.f * totalWaitingTimeHigh / numberOfHighPriorityTasks);

    return outputFile;
  }
}

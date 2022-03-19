package pes.output.summarisers;

import pes.output.OutputFile;
import pes.processor.ProcessorState;
import pes.recorders.Record;
import pes.recorders.Recorder;

import java.util.AbstractCollection;

public class Summariser {
    public OutputFile summarise(AbstractCollection<Recorder> recorders) {
        OutputFile outputFile = new OutputFile(
                "Summary",
                "Total Idle Time",
                "Total Execution Time",
                "Total Waiting Time (LOW)",
                "Total Waiting Time (HIGH)",
                "Average Time Waiting (LOW)",
                "Average Time Waiting (HIGH)"
        );
        int totalIdleTime = 0;
        int totalExecutionTime = 0;
        int totalWaitingTimeHigh = 0;
        int totalWaitingTimeLow = 0;
        int numberOfHighPriorityTasks = 0;
        int numberOfLowPriorityTasks = 0;
        for (Recorder recorder : recorders) {
            for (Record record : recorder) {
                if (recorder.getType().equalsIgnoreCase("Processor")) {
                    ProcessorState state = (ProcessorState) record.getState();
                    if (state.getRunningTask() == null) {
                        totalExecutionTime += record.getDuration();
                    }
                }
            }
        }
    }
}

package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import duke.exceptions.DukeException;


/**
 * Class for deadline tasks
 */
public class DeadlineTask extends Task {

    protected LocalDateTime endTime;
    /**
     * Constructor for DeadlineTask
     *
     * @param name Name of the DeadlineTask
     * @param endTime Deadline for the task
     * @param file_format File format for the task
     */
    public DeadlineTask(String name, LocalDateTime endTime, String file_format) {
        super(name, Task.Type.D, file_format);
        this.endTime = endTime;
    }

    /**
     * String representation of DeadlineTask
     *
     * @return String representation of DeadlineTask
     */
    @Override
    public String toString() {
        String output;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if (done) {
            output = "[" + this.type + "]" + "[X] " + this.name + " (by: " + this.endTime.format(formatter) + ")\n";
        } else {
            output = "[" + this.type + "]" + "[ ] " + this.name + " (by: " + this.endTime.format(formatter) + ")\n";
        }
        return output;
    }
}

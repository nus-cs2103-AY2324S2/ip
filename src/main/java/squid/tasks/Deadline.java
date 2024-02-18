package squid.tasks;

import java.util.Objects;

import squid.constants.Messages;
import squid.constants.Regex;

/**
 * Class encapsulating Deadline tasks.s
 */
public class Deadline extends Task {
    private DateTime deadline;


    /**
     * The constructor of an Event.
     * @param taskName Name of task.
     * @param deadline The DateTime of the deadline.
     */
    public Deadline(String taskName, DateTime deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    /**
     * @return The representation of a Deadline.
     */
    @Override
    public String getType() {
        return "[D]";
    }

    /**
     * @return The string representation of the deadline.
     */
    @Override
    public String getAdditionalInfo() {
        return String.format(Messages.DEADLINE_TO_STRING, deadline);
    }

    /**
     * @return The string representation of a Deadline task.
     */
    @Override
    public String toString() {
        return String.format("%s[%s]: %s%s", getType(), completedIcon(), getTaskName(), getAdditionalInfo());
    }

    /**
     * @return The string representation of a Deadline task to be stored into hard disk.
     */
    @Override
    public String parseStr() {
        return String.format(
                "%s%s%s%s%s%s%s\n",
                getType(),
                Regex.TASK_SPLIT,
                Objects.equals(completedIcon(), "X") ? "X" : "-",
                Regex.TASK_SPLIT,
                getTaskName(),
                Regex.TASK_SPLIT,
                deadline);
    }
}

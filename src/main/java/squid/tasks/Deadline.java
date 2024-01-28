package squid.tasks;

import squid.constants.MESSAGES;
import squid.constants.REGEX;

import java.util.Objects;

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
        return String.format(MESSAGES.DEADLINE_TO_STRING, deadline);
    }

    /**
     * @return The string representation of a Deadline task.
     */
    @Override
    public String toString() {
        return String.format("%s[%s]: %s%s", getType(), completedIcon(), taskName, getAdditionalInfo());
    }

    /**
     * @return The string representation of a Deadline task to be stored into hard disk.
     */
    @Override
    public String parseStr() {
        return String.format(
                "%s%s%s%s%s%s%s\n",
                getType(),
                REGEX.TASK_SPLIT,
                Objects.equals(completedIcon(), "X") ? "X" : "-",
                REGEX.TASK_SPLIT,
                taskName,
                REGEX.TASK_SPLIT,
                deadline);
    }
}
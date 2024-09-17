package squid.tasks;

import java.util.Objects;

import squid.constants.Messages;
import squid.constants.Regex;

/**
 * Class encapsulating Deadline tasks.
 */
public class Event extends Task {
    private DateTime from;
    private DateTime to;

    /**
     * The constructor of an Event.
     * @param taskName Name of task.
     * @param from When the event starts.
     * @param to When the event ends.
     */
    public Event(String taskName, DateTime from, DateTime to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    /**
     * @return The representation of an Event.
     */
    @Override
    public String getType() {
        return "[E]";
    }

    /**
     * @return The from/to DateTimes.
     */
    @Override
    public String getAdditionalInfo() {
        return String.format(Messages.EVENT_TO_STRING, from, to);
    }

    /**
     * @return The string representation of an Event task.
     */
    @Override
    public String toString() {
        return String.format("%s[%s]: %s%s", getType(), completedIcon(), getTaskName(), getAdditionalInfo());
    }

    /**
     * @return The string representation of an Event task to be stored into hard disk.
     */
    @Override
    public String parseStr() {
        return String.format(
                "%s%s%s%s%s%s%s%s%s\n", getType(),
                Regex.TASK_SPLIT,
                Objects.equals(completedIcon(), "X") ? "X" : "-",
                Regex.TASK_SPLIT,
                getTaskName(),
                Regex.TASK_SPLIT,
                from,
                Regex.TASK_SPLIT,
                to);
    }
}

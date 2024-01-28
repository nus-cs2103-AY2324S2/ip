package squid.tasks;

import squid.constants.MESSAGES;
import squid.constants.REGEX;

import java.util.Objects;

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
        return String.format(MESSAGES.EVENT_TO_STRING, from, to);
    }

    /**
     * @return The string representation of an Event task.
     */
    @Override
    public String toString() {
        return String.format("%s[%s]: %s%s", getType(), completedIcon(), taskName, getAdditionalInfo());
    }

    /**
     * @return The string representation of an Event task to be stored into hard disk.
     */
    @Override
    public String parseStr() {
        return String.format(
                "%s%s%s%s%s%s%s%s%s\n",getType(),
                REGEX.TASK_SPLIT,
                Objects.equals(completedIcon(), "X") ? "X" : "-",
                REGEX.TASK_SPLIT,
                taskName,
                REGEX.TASK_SPLIT,
                from,
                REGEX.TASK_SPLIT,
                to);
    }
}

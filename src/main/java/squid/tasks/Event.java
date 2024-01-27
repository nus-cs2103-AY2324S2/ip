package squid.tasks;

import squid.constants.MESSAGES;
import squid.constants.REGEX;

import java.util.Objects;

public class Event extends Task {
    private Date from;
    private Date to;

    public Event(String task, Date from, Date to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getType() {
        return "[E]";
    }

    @Override
    public String getAdditionalInfo() {
        return String.format(MESSAGES.EVENT_TO_STRING, from, to);
    }

    @Override
    public String toString() {
        return String.format("%s[%s]: %s%s", getType(), completedIcon(), task, getAdditionalInfo());
    }

    @Override
    public String parseStr() {
        return String.format(
                "%s%s%s%s%s%s%s%s%s\n",getType(),
                REGEX.TASK_SPLIT,
                Objects.equals(completedIcon(), "X") ? "X" : "-",
                REGEX.TASK_SPLIT,
                task,
                REGEX.TASK_SPLIT,
                from,
                REGEX.TASK_SPLIT,
                to);
    }
}

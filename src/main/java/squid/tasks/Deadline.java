package squid.tasks;

import squid.constants.MESSAGES;
import squid.constants.REGEX;

import java.util.Objects;

public class Deadline extends Task {
    private Date deadline;

    public Deadline(String task, Date deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String getType() {
        return "[D]";
    }

    @Override
    public String getAdditionalInfo() {
        return String.format(MESSAGES.DEADLINE_TO_STRING, deadline);
    }

    @Override
    public String toString() {
        return String.format("%s[%s]: %s%s", getType(), completedIcon(), task, getAdditionalInfo());
    }

    @Override
    public String parseStr() {
        return String.format(
                "%s%s%s%s%s%s%s\n",
                getType(),
                REGEX.TASK_SPLIT,
                Objects.equals(completedIcon(), "X") ? "X" : "-",
                REGEX.TASK_SPLIT,
                task,
                REGEX.TASK_SPLIT,
                deadline);
    }
}
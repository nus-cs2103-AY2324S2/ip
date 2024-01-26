package squid.Tasks;

import squid.CONSTANTS.MESSAGES;

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
                "%s%c%s%c%s%c%s\n",
                getType(),
                '\uFFFF',
                Objects.equals(completedIcon(), "X") ? "X" : "-",
                '\uFFFF',
                task,
                '\uFFFF',
                deadline);
    }
}
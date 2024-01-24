package Tasks;

import CONSTANTS.MESSAGES;

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
}